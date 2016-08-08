package com.stremlenye.tetris

trait Line {
  val cells: List[Cell]
  val length: Int

  require(cells.size == length)
}

object Line {
  def apply(cell: Cell): Line1 = Line1(cell)

  def apply(cell1: Cell, cell2: Cell): Line2 = Line2(cell1, cell2)

  def apply(cell1: Cell, cell2: Cell, cell3: Cell): Line3 = Line3(cell1, cell2, cell3)

  def apply(cell1: Cell, cell2: Cell, cell3: Cell, cell4: Cell): Line4 = Line4(cell1, cell2, cell3, cell4)
}

case class Line1(cell: Cell) extends Line {
  override val length = 1
  override val cells: List[Cell] = List(cell)
}

case class Line2(cell1: Cell, cell2: Cell) extends Line {
  val length = 2
  override val cells: List[Cell] = List(cell1, cell2)
}

case class Line3(cell1: Cell, cell2: Cell, cell3: Cell) extends Line {
  override val length: Int = 3
  override val cells: List[Cell] = List(cell1, cell2, cell3)
}

case class Line4(cell1: Cell, cell2: Cell, cell3: Cell, cell4: Cell) extends Line {
  override val length: Int = 4
  override val cells: List[Cell] = List(cell1, cell2, cell3, cell4)
}
