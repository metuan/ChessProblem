package com.scalac.chessProblem
import com.scalac.chessProblem.model._
import org.scalatest._

class PositionsSpec extends FlatSpec{
  val chessBoard = new ChessBoard(3,3)
  import chessBoard._
  val instanceOfChessBoard = new instanceOfChessBoardToPlay()

  behavior of "A King"
  it should "list possible moves" in {
    val possibleMoves = instanceOfChessBoard.movesOfPiece(PieceOnBoard(King), Position(0,0))
    assert(possibleMoves.size == 3)
    assert(possibleMoves.toSet == Set(Position(0, 1), Position(1, 1), Position(1, 0)))
  }

  behavior of "A Rook"
  it should "list possible moves" in {
    val possibleMoves = instanceOfChessBoard.movesOfPiece(PieceOnBoard(Rook), Position(0,0))
    assert(possibleMoves.size == 5)
    assert(possibleMoves.toSet == Set(Position(0,0), Position(0,1), Position(0,2), Position(1,0), Position(2,0)))
  }

  behavior of "A Bishop"
  it should "list possible moves" in {
    val possibleMoves = instanceOfChessBoard.movesOfPiece(PieceOnBoard(Bishop), Position(0,0))
    assert(possibleMoves.size == 3)
    assert(possibleMoves.toSet == Set(Position(0,0), Position(1,1), Position(2,2)))
  }

  behavior of "A Queen"
  it should "list possible moves" in {
    val possibleMoves = instanceOfChessBoard.movesOfPiece(PieceOnBoard(Queen), Position(0,0))
    assert(possibleMoves.size == 7)
    assert(possibleMoves.toSet == Set(Position(0,0),
      Position(0,1), Position(0,2), Position(1,0),
      Position(1,1), Position(2,0), Position(2,2)))
  }

  behavior of "A Knight"
  it should "list possible moves" in {
    val possibleMoves = instanceOfChessBoard.movesOfPiece(PieceOnBoard(Knight), Position(0,0))
    assert(possibleMoves.size == 2)
    assert(possibleMoves.toSet == Set(Position(1,2), Position(2,1)))
  }
}
