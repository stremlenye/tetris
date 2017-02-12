package com.stremlenye.tetris

import org.scalatest.{FlatSpec, Matchers}

class PlaygroundTest extends FlatSpec with Matchers {

  it should "be matrix" in {
    val r: Matrix[3, 2] = Matrix[2, 3](FixedSizeList[3, FixedSizeList[2, Cell]](
      FixedSizeList[2, Cell](Cell(true), Cell(false)),
      FixedSizeList[2, Cell](Cell(true), Cell(false)),
      FixedSizeList[2, Cell](Cell(true), Cell(false))
    )).rotateClockwise

    val expected = Matrix[3,2](FixedSizeList[2,  FixedSizeList[3, Cell]](
      FixedSizeList[3, Cell](Cell(true), Cell(true), Cell(true)),
      FixedSizeList[3, Cell](Cell(false), Cell(false), Cell(false))
    ))
    assert(r == expected)
  }

  it should "be able to rotate for 360 degrees" in {
    val expected = Matrix[2, 3](FixedSizeList[3,  FixedSizeList[2, Cell]](
      FixedSizeList[2, Cell](Cell(true),Cell(false)),
      FixedSizeList[2, Cell](Cell(true),Cell(false)),
      FixedSizeList[2, Cell](Cell(true),Cell(false))
    ))

    val r = expected.rotateClockwise.rotateClockwise.rotateClockwise.rotateClockwise

    assert(r == expected)
  }

  it should "merge two matrixes" in {
    val l = Matrix[2,2](FixedSizeList[2,  FixedSizeList[2, Cell]](
      FixedSizeList[2, Cell](Cell(true), Cell(false)),
      FixedSizeList[2, Cell](Cell(true), Cell(false))
    ))
    val r = Matrix[2,2](FixedSizeList[2,  FixedSizeList[2, Cell]](
      FixedSizeList[2, Cell](Cell(true), Cell(true)),
      FixedSizeList[2, Cell](Cell(true), Cell(false))
    ))

    val result = l <+> r

    val expected = Matrix[2,2](FixedSizeList[2,  FixedSizeList[2, Cell]](
      FixedSizeList[2, Cell](Cell(true), Cell(true)),
      FixedSizeList[2, Cell](Cell(true), Cell(false))
    ))

    assert(result == expected)
  }
}
