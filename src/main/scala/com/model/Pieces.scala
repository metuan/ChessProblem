package com.model

abstract class Piece(val symbolOfPiece: String) {
  def isValidMove(dir: Position): Boolean
}

object King extends Piece("K") {
  def isValidMove(dir: Position): Boolean = (dir.x.abs max dir.y.abs) == 1
}

object Rook extends Piece("R") {
  def isValidMove(dir: Position): Boolean = dir.x == 0 || dir.y == 0
}

object Bishop extends Piece("B") {
  def isValidMove(dir: Position): Boolean = dir.x.abs == dir.y.abs
}

object Queen extends Piece("Q") {
  def isValidMove(dir: Position): Boolean = dir.x.abs == dir.y.abs || dir.x == 0 || dir.y == 0
}

object Knight extends Piece("N") {
  def isValidMove(dir: Position): Boolean = dir.x.abs * dir.y.abs == 2
}
