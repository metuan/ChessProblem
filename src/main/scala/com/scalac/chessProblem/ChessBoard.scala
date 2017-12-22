package com.scalac.chessProblem

import com.scalac.chessProblem.model._
import scala.language.implicitConversions

class ChessBoard(override val width : Int, override val height: Int) extends Board {

  implicit def getNumberOfPosition (position: Position) = new PositionEnvelope(width, height, position)

  def createFromIndex(i : Int) = Position(i % width, i / width)

  def positionsOnBoard = {
    for(x <- -width to width; y <- -height to height) yield Position(x,y)
  }

  class singleChessBoard(val layout : Array[PositionToPrint], boardInfo : Option[InfoOfBoard]) {

    def this() = this(Array.fill(width * height)(EmptyPosition), None)
    def this(layout : Array[PositionToPrint]) = this(layout, None)

    def getMovesOfPiece(pieceOnBoard: PieceOnBoard, position: Position) = {
      pieceOnBoard.possibleMovesOnBoard.map {
        piece => position.placeOnPosition(piece)
      }.filter(_.isPositionOnChessBoard)
    }

    def atPosition(position: Position) = {
      layout(position.getPositionID)
    }

    def getInstanceWithNewPiece(pieceOnBoard: PieceOnBoard, position: Position, movesOfAPiece: Iterable[Position]) = {
      val layoutWithNewPiece = layout.clone
      for (piece <- movesOfAPiece) {
        layoutWithNewPiece(piece.getPositionID) = AttackedPosition
      }
      layoutWithNewPiece.update(position.getPositionID, OccupiedPosition(pieceOnBoard.piece))
      new singleChessBoard(layoutWithNewPiece, Some(InfoOfBoard(this, pieceOnBoard, position)))
    }

    def isValidToPlace(pieceOnBoard: PieceOnBoard, position: Position, movesOfAPiece : Iterable[Position]) = {
      movesOfAPiece.forall{ piece =>
        val positionOnBoard = atPosition(piece)
        (positionOnBoard eq EmptyPosition) || (positionOnBoard eq AttackedPosition)
      }
    }

    def placePieceOnBoard(pieceOnBoard: PieceOnBoard) = {
      isPositionAvailable(pieceOnBoard).flatMap { piece =>
        val movesOfPiece = getMovesOfPiece(pieceOnBoard, piece)
        if (isValidToPlace(pieceOnBoard, piece, movesOfPiece)) Some(getInstanceWithNewPiece(pieceOnBoard, piece, movesOfPiece))
        else None
      }
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

    def isPositionAvailable (piece: PieceOnBoard) = boardInfo match {
      case Some(i) if i.piece == piece => listOfAvailablePositions.filter {
        p => p.getPositionID > i.position.getPositionID
      }
      case _ => listOfAvailablePositions
    }

    override def equals(obj: scala.Any) = {
      obj match {
        case possibleInstance : singleChessBoard => List(layout: _*) == List(possibleInstance.layout :_*)
        case _ => false
      }
    }

    override def toString = {
      (0 until height).map( yDir =>
        (0 until width).map( xDir =>
          atPosition(Position(xDir, yDir)) match {
            case AttackedPosition => "* "
            case EmptyPosition => "_ "
            case OccupiedPosition(p) => p.symbolOfPiece + " "
          }).mkString
      ).mkString("\n")
    }

  }

  case class InfoOfBoard(previousBoard : singleChessBoard, piece: PieceOnBoard, position: Position)

  case class PieceOnBoard(piece: Piece) extends {
    val possibleMovesOnBoard = positionsOnBoard.filter {
      position => piece.isValidMove(position)
    }}

  case class EmptyBoard() extends singleChessBoard

  def solve(piecesToPutOnBoard: List[Piece]) : (Int, List[singleChessBoard]) = {
    val pieces = piecesToPutOnBoard.map {
      piece => PieceOnBoard(piece)
    }
    var numberOfSolutions = 0
    var listOfBoards : List[singleChessBoard] = List()

    def findPlaces(level: Int, board: singleChessBoard) {
      if(level == pieces.length) {
        numberOfSolutions +=1
        if (numberOfSolutions < 3) {
          listOfBoards ::= board
        }
      } else {
        val curPiece = pieces(level)
        board.placePieceOnBoard(curPiece).foreach( newBoard => findPlaces(level+1, newBoard) )
      }
    }

    findPlaces(0, new EmptyBoard)
    (numberOfSolutions, listOfBoards)
  }
}
