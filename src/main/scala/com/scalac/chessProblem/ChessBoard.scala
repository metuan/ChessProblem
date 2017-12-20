package com.scalac.chessProblem

import com.scalac.chessProblem.model._

class ChessBoard(override val width: Int, override val height: Int) extends Board {

  implicit def getNumberOfPosition (position: Position) = new UniquePosition(width, height, position)

  def createFromIndex(i : Int) = Position(i % width, i / width)

  def positionsOnBoard = {
    for(x <- -width to width; y <- -height to height) yield Position(x,y)
  }

  class instanceOfChessBoardToPlay(val layout : Array[BoardToPrint], info : Option[InfoOfBoard]) {

    def this() = this(Array.fill(width * height)(EmptyPosition), None)
    def this(layout : Array[BoardToPrint]) = this(layout, None)

  }
  case class InfoOfBoard(previousBoard : instanceOfChessBoardToPlay, piece: PieceOnBoard, position: Position)
  case class PieceOnBoard(piece: Piece) {
    val possibleMovesOnBoard = positionsOnBoard.filter {
      position => piece.isValidMove(position)
  }}
  case class EmptyBoard() extends instanceOfChessBoardToPlay

//  def solve (sequenceOfPieces : Piece*) : (Int, List[instanceOfChessBoardToPlay]) =
}
