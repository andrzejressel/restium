package com.andrzejressel.browserapi.service.model

import cats.effect.kernel.Resource
import cats.effect.std.Queue
import cats.effect.{Deferred, FiberIO, IO, Ref}
import com.andrzejressel.browserapi.api.WebsiteImplementation
import com.andrzejressel.browserapi.service.model.BrowserHolder.Browser
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

import scala.jdk.FutureConverters._

class BrowserHolder private (
    private val browserHolder: Queue[IO, Browser],
    private val browserDef: Queue[IO, ChromeDriver],
    private val websiteImplementation: WebsiteImplementation[_]
) {

  private var initFiber: FiberIO[Unit] = _

  private def init(): IO[Unit] = for {
    initFiber <- init0().map(Browser).flatMap(browserHolder.offer).start
  } yield { this.initFiber = initFiber }

  private def init0(): IO[ChromeDriver] = for {
    browser <- IO(generateBrowser())
    _       <- browserDef.offer(browser)
    _       <- IO.fromFuture(IO(websiteImplementation.prepare(BrowserImpl(browser)).asScala))
  } yield browser

  private def generateBrowser(): ChromeDriver = { new ChromeDriver(createChromeOptions()) }

  private def createChromeOptions(): ChromeOptions = {
    import org.openqa.selenium.chrome.ChromeOptions
    val options = new ChromeOptions
    options.addArguments("--lang=en")
  }

  private def verifyInitEnded(): IO[Unit] = initFiber.join.flatMap(_.embedNever)

  def accessBrowser(): Resource[IO, ChromeDriver] = Resource.eval(verifyInitEnded())
    .flatMap(_ => Resource.make(browserHolder.take)(browserHolder.offer)).map(_.driver)
    .evalTap(_ => verifyInitEnded())

  def invokeMethod(methodId: Any, body: Array[Byte]): IO[Any] = accessBrowser().map(BrowserImpl)
    .use(b => IO.fromFuture(IO(websiteImplementation.invokeMethod(b, methodId, body).asScala)))

  def close(): IO[Unit] = for {
    op <- browserDef.take
    _  <- IO.blocking(op.close())
  } yield ()

}

object BrowserHolder {
  def apply(websiteImplementation: WebsiteImplementation[_]): Resource[IO, BrowserHolder] = Resource
    .make(for {
      queue <- Queue.bounded[IO, Browser](1)
      d     <- Queue.bounded[IO, ChromeDriver](1)
      holder = new BrowserHolder(queue, d, websiteImplementation)
      _ <- holder.init()
    } yield holder)(_.close())

  case class Browser(driver: ChromeDriver)

}
