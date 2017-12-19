package com.scalac.chessProblem.model

abstract class BoardToPrint
case object EmptyPosition extends BoardToPrint
case object AttackedPosition extends BoardToPrint
case class OccupiedPosition(piece: Piece) extends BoardToPrint
