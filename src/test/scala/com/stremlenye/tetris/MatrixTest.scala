package com.stremlenye.tetris.experimental

import org.scalatest.{FlatSpec, Matchers}

class MatrixTest extends FlatSpec with Matchers {

  "Matrix" should "be able to concatenate with Line" in {
    import Matrix._

    val l1 = Line[Int, One](1)
    val l2 = Line[Int, One](2)
    val l3 = Line[Int, One](3)

    val result: Matrix[Int, One, Three] = l1 :: l2 :: l3 :: Nil

    val expected = Matrix[Int, One, Three](l1, l2, l3)

    assertResult(expected)(result)
  }

  "Matrix" should "be able to transpose" in {
    import Matrix._

    val l1 = Line[Int, Two](1,2)
    val l2 = Line[Int, Two](3,4)
    val l3 = Line[Int, Two](5,6)
    val matrix: Matrix[Int, Two, Three] = l1 :: l2 :: l3 :: Nil

    val result = transpose(matrix)

    val expected = Matrix[Int, Three, One](Line[Int, Three](1,2,3))

    assertResult(expected)(result)
  }

  "Matrix" should "be able to rotate" in {
    import Matrix._

    val l1 = Line[Int, One](1)
    val l2 = Line[Int, One](2)
    val l3 = Line[Int, One](3)
    val matrix: Matrix[Int, One, Three] = l1 :: l2 :: l3 :: Nil

    val result = rotate(matrix)

    val expected = Matrix[Int, Three, One](Line[Int, Three](1,2,3))

    assertResult(expected)(result)
  }

}
