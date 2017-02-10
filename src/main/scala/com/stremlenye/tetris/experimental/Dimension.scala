package com.stremlenye.tetris.experimental

trait Dimension {
  val size: Int

  val indexes: Iterable[Int] = 0 until size
}

case class One() extends Dimension {
  override val size: Int = 1
}

case class Two() extends Dimension {
  override val size: Int = 2
}

case class Three() extends Dimension {
  override val size: Int = 3
}

case class Four() extends Dimension {
  override val size: Int = 4
}

case class Line[A, X <: Dimension](items: A*)(implicit x: X) {
  require(items.size == x.size)

  def apply(i: Int): A =
    items(i)
}

case class Matrix[A, X <: Dimension, Y <: Dimension](lines: Line[A, X]*)(implicit val x: X, val y: Y) {
  require(lines.size == y.size, s"Matrix size does not conform requirements: ${lines.size} != ${y.size}")

  def apply(i: Int): Line[A, X] = lines(i)
}

object Matrix {
  implicit val one = One()
  implicit val two = Two()
  implicit val three = Three()
  implicit val four = Four()

  implicit def asMatrix[A, X <: Dimension, Y <: Dimension](lines: Seq[Line[A, X]])(implicit x: X, y: Y): Matrix[A, X, Y] =
    Matrix[A, X, Y](lines:_*)

  def transpose[A, X <: Dimension, Y <: Dimension](matrix: Matrix[A, X, Y])(implicit x: X, y: Y): Matrix[A, Y, X] =
    {
      println(s">>>> ${matrix.lines.indices.mkString(" ")}")


      /*
      S: 3 x 2

      123  1 4
      456  2 5
           3 6

    i: 0 1 2 3 4 5
       1 2 3 4 5 6
    i: 0     3
       1     4
    s: 3
    ______________
    i: 0 1 2 3
       2 3 5 6
    i: 0   2
       2   5
    s: 2
    ______________
       36

       I: 3
       _______

       S: 3 x 3
       1 2 3   1 4 7
       4 5 6   2 5 8
       7 8 9   3 6 9

    i: 0 1 2 3 4 5 6 7 8
       1 2 3 4 5 6 7 8 9
    i: 0     3     6
       1     4     7
       2 3 5 6 8 9
       2   5   8
       3 6 9

       I: 3

       1 2   1 3 5
       3 4   2 4 6
       5 6

       1 2 3 4 5 6
       1   3   5
       246
       */

      def extract(seq: Seq[A], indexes: Seq[Int]): (Seq[A], Seq[A]) = {
        val p = for {
          x <- indexes
        } yield seq(x)
        (p, seq.filterNot(p.contains))
      }

      val l = for {
        x <- x.indexes
        y <- y.indexes
      } yield matrix(x)(y)

      def split(step: Int, seq: Seq[A]) = {
        val indexes = 0.to(seq.size, step)
        val result = extract(seq, indexes)

      }

      asMatrix[A, Y, X]((for {
        x <- matrix.lines.indices
        y <- matrix.lines.indices
      } yield matrix(y)(x)).grouped(matrix.x.size).map(Line[A, Y]).toList)
    }

  def reflect[A, X <: Dimension, Y <: Dimension](matrix: Matrix[A, X, Y])(implicit x: X, y: Y): Matrix[A, X, Y] =
    asMatrix[A, X, Y](for {
      line <- matrix.lines
    } yield Line[A, X](line.items.reverse:_*))

  def rotate[A, X <: Dimension, Y <: Dimension](matrix: Matrix[A, X, Y])(implicit x: X, y: Y): Matrix[A, Y, X] =
    reflect(transpose(matrix))
}
