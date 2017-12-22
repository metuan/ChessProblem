name := "scalac-chess-problem"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test

scalacOptions ++= Seq("-optimise")

fork in run := true
javaOptions in run  ++= Seq( "-Xms4g", "-Xmx4g", "-XX:+UseParallelGC", "-XX:+UseParallelOldGC", "-XX:MaxNewSize=3g")