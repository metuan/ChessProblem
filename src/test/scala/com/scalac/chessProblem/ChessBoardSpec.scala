package com.scalac.chessProblem

import com.scalac.chessProblem.model._
import org.scalatest._



class ChessBoardSpec extends FlatSpec {

  val chessBoard = new ChessBoard(3,3)

  behavior of "A chess board"

  it should "yield all positions" in {
    assert (chessBoard.positionsOnBoard.size == 49)
  }

  it should "create position from given index" in {
    assert (chessBoard.createFromIndex(4) == Position(1,1))
  }

  it should "aaa" in {
      val i = new chessBoard.instanceOfChessBoardToPlay(Array(
        OccupiedPosition(King), AttackedPosition, EmptyPosition,
        AttackedPosition, AttackedPosition, EmptyPosition,
        EmptyPosition, EmptyPosition, EmptyPosition
      ))
      assert( i.listOfAvailablePositions.toSet == Set(Position(2, 0), Position(2, 1), Position(0, 2), Position(1, 2), Position(2, 2)))
  }
}
