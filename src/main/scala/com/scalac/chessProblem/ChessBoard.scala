package com.scalac.chessProblem

import com.scalac.chessProblem.model._

class ChessBoard(override val width: Int, override val height: Int) extends Board {
  implicit def getNumberOfPosition (position: Position) = new PositionEnvelope(width, height, position)

  def createFromIndex(i : Int) = Position(i % width, i / width)

  def positionsOnBoard = {
    for(x <- -width to width; y <- -height to height) yield Position(x,y)
  }

  class instanceOfChessBoardToPlay(val layout : Array[PositionToPrint], info : Option[InfoOfBoard]) {
    require(layout.size == width * height, "Board to print should have same size as chess board instance")

    def this() = this(Array.fill(width * height)(EmptyPosition), None)
    def this(layout : Array[PositionToPrint]) = this(layout, None)

    def movesOfPiece(pieceOnBoard: PieceOnBoard, position: Position) = {
      pieceOnBoard.possibleMovesOnBoard.map {
        piece => position.placeOnPosition(piece)
      }.filter(_.isPositionOnChessBoard)
    }

    def atPosition(position: Position) = {
      layout(position.convertPositionToNumber)
    }

    def listOfAvailablePositions = {
      layout.zipWithIndex.flatMap{
        case (piece, index) =>
          if (piece == EmptyPosition) {
            Some(createFromIndex(index))
          }
          else None
      }
    }

    def isPositionAvailable (piece: PieceOnBoard) = info match {
      case Some(i) if i.piece == piece => listOfAvailablePositions.filter {
        p => p.convertPositionToNumber > i.position.convertPositionToNumber
    }
      case _ => listOfAvailablePositions
    }

    override def equals(obj: scala.Any) = {
      obj match {
        case possibleInstance : instanceOfChessBoardToPlay => List(layout: _*) == List(possibleInstance.layout :_*)
        case _ => false
      }
    }

    override def toString = {
      (0 until height).map( yDir =>
        (0 until width).map( xDir =>
          atPosition(Position(xDir, yDir)) match {
            case EmptyPosition => "_ "
            case AttackedPosition => "* "
            case OccupiedPosition(p) => p.symbolOfPiece + " "
          }).mkString
      ).mkString("\n")
    }


  }
  case class InfoOfBoard(previousBoard : instanceOfChessBoardToPlay, piece: PieceOnBoard, position: Position)
  case class PieceOnBoard(piece: Piece) {
    val possibleMovesOnBoard = positionsOnBoard.filter {
      position => piece.isValidMove(position)
  }}
  case class EmptyBoard() extends instanceOfChessBoardToPlay

//  def solve (sequenceOfPieces : Piece*) : (Int, List[instanceOfChessBoardToPlay]) =
}
