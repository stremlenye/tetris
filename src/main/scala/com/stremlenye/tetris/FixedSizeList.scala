package com.stremlenye.tetris

case class FixedSizeList[X <: Int, +A](s: A*)(implicit x: ValueOf[X]) extends Seq[A] {
  require(s.length == valueOf[X], s"collection length should be ${valueOf[X]}")

  override def length: Int = valueOf[X]

  override def apply(idx: Int): A = s(idx)

  override def iterator: Iterator[A] = s.iterator
}

object FixedSizeList {
  def apply(s: Seq[A])(implicit x: ValueOf[X]): FixedSizeList[X, A] = FixedSizeList[X, A](s: _*)
}