package com.stremlenye.tetris

class Shape[A <: Line](val matrix: Seq[A]) {
  require(matrix.nonEmpty)
  require(matrix.map(_.length).forall(_ == matrix.head.length))

  def ~>(next: Shape[A]): Shape[A] = new Shape(matrix ++ next.matrix)
}

object Shape {
  implicit def apply[A <: Line] (lines: A*): Shape[A] = new Shape[A](lines)

  implicit def apply[A <: Line] (line: A): Shape[A] = Shape(line)
}
