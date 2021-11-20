package com.restium.service.utils

import com.google.common.util.concurrent.ListenableFuture

import java.util.concurrent.Executor
import scala.concurrent.{Future, Promise}
import scala.util.Try

object Implicits {

  implicit class ListenableFutureDecorator[T](val f: ListenableFuture[T]) extends AnyVal {
    def asScala(implicit e: Executor): Future[T] = {
      val p = Promise[T]()
      f.addListener(() => p.complete(Try(f.get())), e)
      p.future
    }
  }

}
