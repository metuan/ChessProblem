package com.scalac.chessProblem

import com.scalac.chessProblem.model._

class ChessBoard(override val width: Int, override val height: Int) extends Board {
  implicit def getNumberOfPosition (position: Position) = new PositionEnvelope(width, height, position)

  def createFromIndex(i : Int) = Position(i % width, i / width)

  def positionsOnBoard = {
    for(x <- -width to width; y <- -height to height) yield Position(x,y)
  }

  class singleChessBoard(val layout : Array[PositionToPrint], info : Option[InfoOfBoard]) {

    def this() = this(Array.fill(width * height)(EmptyPosition), None)
    def this(layout : Array[PositionToPrint]) = this(layout, None)

    def getMovesOfPiece(pieceOnBoard: PieceOnBoard, position: Position) = {
      pieceOnBoard.possibleMovesOnBoard.map {
        piece => position.placeOnPosition(piece)
      }.filter(_.isPositionOnChessBoard)
    }

    def atPosition(position: Position) = {
      layout(position.convertPositionToNumber)
    }

    def getInstanceWithNewPiece(pieceOnBoard: PieceOnBoard, position: Position, movesOfAPiece: Iterable[Position]) = {
      val layoutWithNewPiece = layout.clone
      for (piece <- movesOfAPiece) {
        layoutWithNewPiece(piece.convertPositionToNumber) = AttackedPosition
      }
      layoutWithNewPiece.update(position.convertPositionToNumber, OccupiedPosition(pieceOnBoard.piece))
      new singleChessBoard(layoutWithNewPiece, Some(InfoOfBoard(this, pieceOnBoard, position)))
    }

    def isValidToPlace(pieceOnBoard: PieceOnBoard, position: Position, movesOfAPiece : Iterable[Position]) = {
      movesOfAPiece.forall{ piece =>
        val positionOnBoard = atPosition(piece)
        ((positionOnBoard eq EmptyPosition) || (positionOnBoard eq AttackedPosition))
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

    def isPositionAvailable (piece: PieceOnBoard) = info match {
      case Some(i) if i.piece == piece => listOfAvailablePositions.filter {
        p => p.convertPositionToNumber > i.position.convertPositionToNumber
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
            case EmptyPosition => "_ "
            case AttackedPosition => "* "
            case OccupiedPosition(p) => p.symbolOfPiece + " "
          }).mkString
      ).mkString("\n")
    }

  }
  case class InfoOfBoard(previousBoard : singleChessBoard, piece: PieceOnBoard, position: Position)
  case class PieceOnBoard(piece: Piece) {
    val possibleMovesOnBoard = positionsOnBoard.filter {
      position => piece.isValidMove(position)
  }}
  case class EmptyBoard() extends singleChessBoard

  def solve(_pieces: Piece*) : (Int, List[singleChessBoard]) = {
    val pieces = Vector( _pieces : _*).map { p => PieceOnBoard(p) }
    var numberOfSolutions = 0
    var listOfBoards : List[singleChessBoard] = List()

    def findPlaces(level: Int, board: singleChessBoard) {
      if(level == pieces.size) {
        numberOfSolutions +=1
        if (numberOfSolutions < 5) {
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
