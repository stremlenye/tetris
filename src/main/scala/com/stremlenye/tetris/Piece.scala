package com.stremlenye.tetris

case class Point(horizontal: Int, vertical: Int)

case class Cell(empty: Boolean)

object Cell {
  def filled = Cell(false)
  def x = filled

  def empty  = Cell(true)
  def o = empty
}

trait Line {
  val cells: List[Cell]
  val length: Int

  require(cells.size == length)
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

object Line {
  def apply(cell: Cell): Line1 = Line1(cell)

  def apply(cell1: Cell, cell2: Cell): Line2 = Line2(cell1, cell2)

  def apply(cell1: Cell, cell2: Cell, cell3: Cell): Line3 = Line3(cell1, cell2, cell3)

  def apply(cell1: Cell, cell2: Cell, cell3: Cell, cell4: Cell): Line4 = Line4(cell1, cell2, cell3, cell4)
}


class Shape[A <: Line](val matrix: Seq[A]) {
  require(matrix.nonEmpty)
  require(matrix.map(_.length).forall(_ == matrix.head.length))

  def ~>(next: Shape[A]): Shape[A] = new Shape(matrix ++ next.matrix)
}

object Shape {
  implicit def apply[A <: Line] (lines: A*): Shape[A] = new Shape[A](lines)

  implicit def apply[A <: Line] (line: A): Shape[A] = Shape(line)
}

trait Piece {
  val shape: Shape[_]
}

import Shape._

case class Stick(center: Point) extends Piece {
  val shape = Line(Cell.x, Cell.x, Cell.x, Cell.x) ~>
              Line(Cell.x, Cell.x, Cell.x, Cell.x)
}

case class Square(center: Point) extends Piece {
  val shape = Line(Cell.x, Cell.x) ~>
              Line(Cell.x, Cell.x)
}

case class Tblock(center: Point) extends Piece {
  override val shape = Line(Cell.o, Cell.x, Cell.o) ~>
                       Line(Cell.x, Cell.x, Cell.x)
}

case class InverseSkew(center: Point) extends Piece {
  override val shape = Line(Cell.o, Cell.x, Cell.x) ~>
                       Line(Cell.x, Cell.x, Cell.o)
}

case class OutverseSkew(center: Point) extends Piece {
  override val shape = Line(Cell.x, Cell.x, Cell.o) ~>
                       Line(Cell.o, Cell.x, Cell.x)
}

case class InverseL(center: Point) extends Piece {
  override val shape = Line(Cell.x, Cell.o, Cell.o) ~>
                       Line(Cell.x, Cell.x, Cell.x)
}

case class OutverseJ(center: Point) extends Piece {
  override val shape = Line(Cell.o, Cell.o, Cell.x) ~>
                       Line(Cell.x, Cell.x, Cell.x)
}
