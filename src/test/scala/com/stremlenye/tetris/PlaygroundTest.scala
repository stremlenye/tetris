package com.stremlenye.tetris

import org.scalatest.{FlatSpec, Matchers}

case class Residue[M <: Int](n: Int) extends AnyVal {
  def +(rhs: Residue[M])(implicit m: ValueOf[M]): Residue[M] =
    Residue((this.n + rhs.n) % valueOf[M])
}

class PlaygroundTest extends FlatSpec with Matchers {

  it should "be fun" in {
    val fiveModTen = Residue[10](5)
    val nineModTen = Residue[10](9)

    fiveModTen + nineModTen      // OK == Residue[10](4)

    val fourModEleven = Residue[11](4)
  }

}
