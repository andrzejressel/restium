package com.andrzejressel.browserapi.service

import cats.effect._
import cats.effect.implicits._
import cats.implicits._
import com.andrzejressel.browserapi.api.WebsiteImplementation
import com.andrzejressel.browserapi.impl.fastmail.{EntryPoint, UseCaseType}
import com.andrzejressel.browserapi.service.model.BrowserHolder
import rs.ltt.jmap.client.Services

import scala.jdk.FutureConverters.CompletionStageOps

object Main extends IOApp.Simple {

  override def run: IO[Unit] = (for {
    _ <- Resource.onFinalize(cleanupJmap())
    websiteImpl = new EntryPoint().createWebsiteImplementation
    holder <- BrowserHolder(websiteImpl)
  } yield (holder, websiteImpl)).use { case (a, websiteImpl) => useBrowser(a, websiteImpl) }

  def useBrowser[T](
      browserHolder: BrowserHolder,
      websiteImplementation: WebsiteImplementation[T]
  ): IO[Unit] = for {
    configuration <- getConfiguration(websiteImplementation)
    _      <- IO.fromFuture(IO(websiteImplementation.setConfiguration(configuration).asScala))
    result <- browserHolder.invokeMethod(UseCaseType.GET_MASKED_EMAIL, Array[Byte]())
    _      <- IO.println(result)
  } yield ()

  def cleanupJmap(): IO[Unit] = List(
    IO(Services.OK_HTTP_CLIENT.dispatcher().executorService().shutdown()),
    IO(Services.OK_HTTP_CLIENT_LOGGING.dispatcher().executorService().shutdown()),
    IO(Services.OK_HTTP_CLIENT.connectionPool().evictAll()),
    IO(Services.OK_HTTP_CLIENT_LOGGING.connectionPool().evictAll()),
    IO(Services.SCHEDULED_EXECUTOR_SERVICE.shutdown())
  ).parSequence.void

  def getConfiguration[T](websiteImplementation: WebsiteImplementation[T]): IO[T] = for {
    username <- IO(System.getenv("FASTMAIL_USERNAME"))
    password <- IO(System.getenv("FASTMAIL_PASSWORD"))
    constructor = websiteImplementation.getConfigurationClass.getConstructors.toList.head
    obj <- IO(constructor.newInstance(username, password).asInstanceOf[T])
  } yield obj

}
