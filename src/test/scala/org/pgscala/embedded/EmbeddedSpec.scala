package org.pgscala.embedded

import java.io.File
import java.util.concurrent.Executors

import com.typesafe.scalalogging.StrictLogging
import org.specs2.Specification

import scala.concurrent.ExecutionContext

object EmbeddedSpec {
  val projectRoot: File = {
    val classLocation = getClass.getProtectionDomain.getCodeSource.getLocation.getPath
    val isWin = OS.Name.resolved == Some(OS.Name.Windows)
    val path = if (isWin) classLocation.tail else classLocation
    new File(path.replaceFirst("/target/.*?$", "")).getAbsoluteFile
  }
}

trait EmbeddedSpec extends Specification with StrictLogging {
  implicit val executionContext = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())
}
