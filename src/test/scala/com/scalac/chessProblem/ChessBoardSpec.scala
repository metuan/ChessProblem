package com.scalac.chessProblem

import com.scalac.chessProblem.model._
import org.scalatest._



class ChessBoardSpec extends FlatSpec {

  val chessBoard = new ChessBoard(3,3)

  behavior of "A chess board"

  it should "create position from given index" in {
    assert (chessBoard.createFromIndex(4) == Position(1,1))
  }

  it should "list available position with Queen on chess board in left top corner" in {
      val instanceOfChessBoard = new chessBoard.singleChessBoard(Array(
        OccupiedPosition(Queen), AttackedPosition, AttackedPosition,
        AttackedPosition, AttackedPosition, EmptyPosition,
        AttackedPosition, EmptyPosition, AttackedPosition
      ))
      assert(instanceOfChessBoard.listOfAvailablePositions.toSet == Set(Position(2, 1), Position(1, 2)))
  }

  it should "list available position with King on chess board in the middle of it" in {
    val instanceOfChessBoard = new chessBoard.singleChessBoard(Array(
      AttackedPosition, AttackedPosition, AttackedPosition,
      AttackedPosition, OccupiedPosition(King), AttackedPosition,
      AttackedPosition, AttackedPosition, AttackedPosition
    ))
    assert(instanceOfChessBoard.listOfAvailablePositions.toSet == Set())
  }

  it should "list available position with Rook on chess board in the middle of it" in {
    val instanceOfChessBoard = new chessBoard.singleChessBoard(Array(
      EmptyPosition, AttackedPosition, EmptyPosition,
      AttackedPosition, OccupiedPosition(Rook), AttackedPosition,
      EmptyPosition, AttackedPosition, EmptyPosition
    ))
    assert(instanceOfChessBoard.listOfAvailablePositions.toSet == Set(Position(2,2), Position(2,0), Position(0,2), Position(0,0)))
  }

  it should "list available position with Bishop on chess board in the right top corner" in {
    val instanceOfChessBoard = new chessBoard.singleChessBoard(Array(
      EmptyPosition, EmptyPosition, OccupiedPosition(Bishop),
      EmptyPosition, AttackedPosition, EmptyPosition,
      EmptyPosition, EmptyPosition, EmptyPosition
    ))
    assert(instanceOfChessBoard.listOfAvailablePositions.toSet == Set(Position(0,2), Position(0,0), Position(2,2), Position(0,1), Position(1,2), Position(2,1), Position(1,0)))
  }

  it should "list available position with Knight on chess board in the right top corner" in {
    val instanceOfChessBoard = new chessBoard.singleChessBoard(Array(
      EmptyPosition, EmptyPosition, OccupiedPosition(Knight),
      AttackedPosition, EmptyPosition, EmptyPosition,
      EmptyPosition, AttackedPosition, EmptyPosition
    ))
    assert(instanceOfChessBoard.listOfAvailablePositions.toSet == Set(Position(0,2), Position(0,0), Position(1,1), Position(2,2), Position(2,1), Position(1,0)))
  }






}
