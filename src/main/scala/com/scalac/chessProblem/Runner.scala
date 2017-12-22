package com.scalac.chessProblem

import com.scalac.chessProblem.model._

object Runner {

  def exampleRun() = {

    def setNumberOfPiece(tuple2: Tuple2[Piece, Int]) = {
      var list : List[Piece] = List()
      for (i <- 1 to tuple2._2) {
        list ::= tuple2._1
      }
      list
    }

    val board = new ChessBoard(7,7)
    val setupOfPieces = List(setNumberOfPiece((King,2)), setNumberOfPiece(Queen, 2), setNumberOfPiece(Bishop, 2), setNumberOfPiece(Knight, 1)).flatten
    import System.{currentTimeMillis => clockStartStop}
    val begin = clockStartStop()
    val (number, list) = board.solve(setupOfPieces)
    val end = clockStartStop()
    val dur = (end - begin) / 1000.0
    list.take(3).foreach(e => println(e + "\n"))
    println("Number of solutions: " + number)
    println("\n" + "Elapsed time: "+ dur +" ms")
  }

  def main(args: Array[String]): Unit = {
    exampleRun()
  }
}
