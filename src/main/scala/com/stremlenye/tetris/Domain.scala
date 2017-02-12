package com.stremlenye.tetris

case class Cell(value: Boolean) extends AnyVal {
  def || (cell: Cell) =
    value || cell.value
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

    Matrix[Y, X](FixedSizeList[X, FixedSizeList[Y, Cell]](go(cells.toList.map(_.toList))._1.map(FixedSizeList[Y, Cell](_))))
  }

  def <+> (mx: Matrix[X, Y]): Matrix[X, Y] =
    Matrix[X, Y](FixedSizeList[Y, FixedSizeList[X, Cell]](cells.zip(mx.cells).map { tpl =>
      FixedSizeList[X, Cell](tpl._1.zip(tpl._2).map(t => Cell(t._1 || t._2)))
    }))
}