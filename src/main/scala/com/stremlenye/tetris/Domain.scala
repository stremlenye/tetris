package com.stremlenye.tetris

import FixedSizeList._

case class Cell(value: Boolean) extends AnyVal {
  def || (cell: Cell) =
    value || cell.value
}

object Cell {
  val X = Cell(true)
  val Y = Cell(false)
}

case class Matrix[X <: Int, Y <: Int](cells: FixedSizeList[Y, FixedSizeList[X, Cell]])(implicit x: ValueOf[X], y: ValueOf[Y]) {
  def rotateClockwise: Matrix[Y, X] = {
    def split(clx: List[List[Cell]]): (List[Cell], List[List[Cell]]) =
      clx.foldLeft((List.empty[Cell], List.empty[List[Cell]]))((a, i) => i match {
        case head :: _ :: _ => (head :: a._1, i.tail :: a._2)
        case head :: Nil    => (head :: a._1, a._2)
        case Nil            => a
      })

    def go(clx: List[List[Cell]]): (List[List[Cell]], List[List[Cell]]) =
      split(clx) match {
        case (leftColumn, rightTail) => (leftColumn :: (rightTail match {
          case _ if rightTail.forall(_.isEmpty) => Nil
          case _                                => go(rightTail)._1
        }), rightTail)
      }

    Matrix[Y, X](go(cells.toList.map(_.toList))._1.map(_.fix[Y]).fix[X])
  }

  def rotateCounterclockwise: Matrix[Y, X] =
    this.rotateClockwise.rotateClockwise.rotateClockwise // TODO: normal implementation

  def <+> (mx: Matrix[X, Y]): Matrix[X, Y] =
    Matrix[X, Y](cells.zip(mx.cells).map { tpl =>
      tpl._1.zip(tpl._2).map(t => Cell(t._1 || t._2)).fix[X]
    }.fix[Y])
}