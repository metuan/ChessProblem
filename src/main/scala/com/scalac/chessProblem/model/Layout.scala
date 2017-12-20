package com.scalac.chessProblem.model

abstract class PositionToPrint
case object EmptyPosition extends PositionToPrint
case object AttackedPosition extends PositionToPrint
case class OccupiedPosition(piece: Piece) extends PositionToPrint
