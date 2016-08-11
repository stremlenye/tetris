package com.stremlenye.tetris

trait Line {
  val cells: List[Cell]
  val length: Int

  require(cells.size == length)

  def apply(x: Int): Cell = cells(x)
}

object Line {
  def apply(cell: Cell): Line1 = Line1(cell)

  def apply(cell1: Cell, cell2: Cell): Line2 = Line2(cell1, cell2)

  def apply(cell1: Cell, cell2: Cell, cell3: Cell): Line3 = Line3(cell1, cell2, cell3)

  def apply(cell1: Cell, cell2: Cell, cell3: Cell, cell4: Cell): Line4 = Line4(cell1, cell2, cell3, cell4)

  def build[A <: Line](appl: PartialFunction[Seq[Cell], A])(a: Seq[Cell]): A =
    if (appl.isDefinedAt(a.toList))
      appl(a.toList)
    else
      throw new RuntimeException(s"Wrong line size: ${a.size}")

  implicit val l1: (Seq[Cell]) => Line1 = build({
    case cell :: Nil => Line1(cell)
  })

  implicit val l2: (Seq[Cell]) => Line2 = build({
    case cell1 :: cell2 :: Nil => Line2(cell1, cell2)
  })

  implicit val l3: (Seq[Cell]) => Line3 = build({
    case cell1 :: cell2 :: cell3 :: Nil => Line3(cell1, cell2, cell3)
  })

  implicit val l4: (Seq[Cell]) => Line4 = build({
    case cell1 :: cell2 :: cell3 :: cell4 :: Nil => Line4(cell1, cell2, cell3, cell4)
  })

}

case class Line1(cell: Cell) extends {
  val length = 1
  val cells: List[Cell] = List(cell)
} with Line

case class Line2(cell1: Cell, cell2: Cell) extends {
  val length = 2
  val cells: List[Cell] = List(cell1, cell2)
} with Line

case class Line3(cell1: Cell, cell2: Cell, cell3: Cell) extends {
  val length: Int = 3
  val cells: List[Cell] = List(cell1, cell2, cell3)
} with Line

case class Line4(cell1: Cell, cell2: Cell, cell3: Cell, cell4: Cell) extends {
  val length: Int = 4
  val cells: List[Cell] = List(cell1, cell2, cell3, cell4)
} with Line
