package com.stremlenye.tetris

import org.scalatest.{FlatSpec, Matchers}

class MatrixTest extends FlatSpec with Matchers {

  import Cell._
  import FixedSizeList._
  
  it should "be matrix" in {

    val r: Matrix[3, 2] = Matrix((
      (X, Y).fix[2],
      (X, Y).fix[2],
      (X, Y).fix[2]
    ).fix[3]).rotateClockwise

    val expected = Matrix((
      (X, X, X).fix[3],
      (Y, Y, Y).fix[3]
    ).fix[2])
    assert(r == expected)
  }

  it should "be able to rotate for 360 degrees" in {
    val expected = Matrix((
      (X,Y).fix[2],
      (X,Y).fix[2],
      (X,Y).fix[2]
    ).fix[3])

    val r = expected.rotateClockwise.rotateClockwise.rotateClockwise.rotateClockwise

    assert(r == expected)
  }

  it should "merge two matrixes" in {
    val l = Matrix((
      (X, Y).fix[2],
      (X, Y).fix[2]
    ).fix[2])
    val r = Matrix((
      (X, X).fix[2],
      (X, Y).fix[2]
    ).fix[2])

    val result = l <+> r

    val expected = Matrix((
      (X, X).fix[2],
      (X, Y).fix[2]
    ).fix[2])

    assert(result == expected)
  }

  it should "blah" in {
    trait Executor {
      type Out
      def execute: Out
    }

    def run[E <: Executor](implicit p: E): p.Out = {
      p.execute
    }

    def run2[E <: Executor](implicit p: E): p.Out = {
      run
    }

    case object Stringer extends Executor {
      override type Out = String

      override def execute: this.Out = "test"
    }

    assert(run2(Stringer) == "test")
  }
}
