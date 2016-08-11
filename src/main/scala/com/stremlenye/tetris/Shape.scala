package com.stremlenye.tetris

case class Shape[A <: Line](matrix: Seq[A]) {
  import Shape._

  require(matrix.nonEmpty)
//  require(matrix.length == matrix.head.length)

  val size = matrix.size

  def ~>(next: Shape[A]): Shape[A] = new Shape(matrix ++ next.matrix)

  def apply(y: Int) = matrix(y)
}

object Shape {
  implicit def apply[A <: Line] (line: A): Shape[A] = Shape(Seq(line))

  private def transpose[A <: Line](shape: Shape[A])(implicit conversion: Seq[Cell] => A): Shape[A] =
    (for {
      x <- shape.matrix.indices
      y <- shape.matrix.indices
    } yield shape.matrix(y)(x)).grouped(shape.size)
      .toList match {
      case Nil => throw new RuntimeException("lines are empty")
      case head :: tail => tail.foldLeft(Shape[A](head))((prev, next) =>  prev ~> Shape[A](next))
    }

  private def mirror[A <: Line](shape: Shape[A])(implicit conversion: Seq[Cell] => A): Shape[A] =
    shape.matrix.map(l => Shape[A](l.cells.reverse)) match {
      case head :: tail => tail.foldLeft(head)(_ ~> _)
    }

  def rotateRight[A <: Line](shape: Shape[A])(implicit conversion: Seq[Cell] => A): Shape[A] = mirror(transpose(shape))
}
