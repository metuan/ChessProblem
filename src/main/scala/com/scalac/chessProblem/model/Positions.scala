package com.scalac.chessProblem.model

case class Position(x: Int, y: Int) {
  def placeOnPosition(position: Position): Position = copy( x = position.x + x , y = position.y + y)
}

class PositionEnvelope(override val width : Int, override val height: Int, position: Position) extends Board {

  def getPositionID =  {
    position.y * width + position.x
  }

  def isPositionOnChessBoard = {
    (position.x < width && position.x >= 0) && (position.y < height && position.y >= 0)
  }
}


