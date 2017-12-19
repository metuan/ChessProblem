package com.scalac.chessProblem

object Runner extends App{
  import System.{currentTimeMillis => clockStartStop}

  val begin = clockStartStop
  val numberOfSolutions = 0
  Thread.sleep(1000)
  val executionTimeInMilliseconds = (clockStartStop - begin) / 1000.0

  println("Number of solutions: " + numberOfSolutions)
  println("Elapsed time: " + executionTimeInMilliseconds + " ms")
}
