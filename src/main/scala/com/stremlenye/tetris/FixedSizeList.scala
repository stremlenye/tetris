package com.stremlenye.tetris
import shapeless.syntax.std.tuple._

class FixedSizeList[X <: Int, +A](s: Seq[A])(implicit x: ValueOf[X]) extends Seq[A] {
  require(s.length == valueOf[X], s"collection length should be ${valueOf[X]}")

  override def length: Int = valueOf[X]

  override def apply(idx: Int): A = s(idx)

  override def iterator: Iterator[A] = s.iterator
}

object FixedSizeList {
  def apply[X <: Int, A](s: A*)(implicit x: ValueOf[X]): FixedSizeList[X, A] = new FixedSizeList[X, A](s)

  implicit class AugmentedFixedSizeList[A](s: Seq[A]) {
    def fix[X <: Int](implicit x: ValueOf[X]) =
      new FixedSizeList[X, A](s)
  }

  implicit def fromTuple[A](t: Tuple1[A]): AugmentedFixedSizeList[A] =
    new AugmentedFixedSizeList[A](Seq(t._1))

  implicit def fromTuple[A](t: Tuple2[A, A]): AugmentedFixedSizeList[A] =
    new AugmentedFixedSizeList[A](Seq(t._1, t._2))

  implicit def fromTuple[A](t: (A, A, A)): AugmentedFixedSizeList[A] =
    new AugmentedFixedSizeList[A](Seq(t._1, t._2, t._3))
}

