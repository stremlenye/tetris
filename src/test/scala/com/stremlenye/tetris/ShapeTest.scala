package com.stremlenye.tetris

import org.scalatest.{FlatSpec, Matchers}

class ShapeTest extends FlatSpec with Matchers {
  "Shape" should  "be rotate right" in {
    import Shape._

    val shape = Line(O, O, O) ~>
                Line(O, X, O) ~>
                Line(X, X, X)

    val expected = Line(X, O, O) ~>
                   Line(X, X, O) ~>
                   Line(X, O, O)

    assertResult(expected)(rotateRight(shape))
  }
}
