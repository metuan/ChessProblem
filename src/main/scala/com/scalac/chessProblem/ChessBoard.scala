package com.scalac.chessProblem

import com.scalac.chessProblem.model.{Board, Position}

class ChessBoard(override val width: Int, override val height: Int) extends Board {

  def possibleMoves = {
    for(x <- -width to width; y <- -height to height) yield Position(x,y)
  }

}
