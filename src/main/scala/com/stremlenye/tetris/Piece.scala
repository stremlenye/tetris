//package com.stremlenye.tetris
//
//import Shape._
//
//case class Point(horizontal: Int, vertical: Int)
//
//trait Piece {
//  val shape: Shape[_]
//}
//
///**
//  * ▦▦▦▦
//  * @param center
//  */
//case class Stick(center: Point) extends Piece {
//  val shape = Shape(Line(X, X, X, X))
//}
//
///**
//  * ▦▦
//  * ▦▦
//  * @param center
//  */
//case class Square(center: Point) extends Piece {
//  val shape = Line(X, X) ~>
//              Line(X, X)
//}
//
///**
//  *  ▦
//  * ▦▦▦
//  * @param center
//  */
//case class Tblock(center: Point) extends Piece {
//  override val shape = Line(O, X, O) ~>
//                       Line(X, X, X)
//}
//
///**
//  *  ▦▦
//  * ▦▦
//  * @param center
//  */
//case class InverseSkew(center: Point) extends Piece {
//  override val shape = Line(O, X, X) ~>
//                       Line(X, X, O)
//}
//
///**
//  * ▦▦
//  *  ▦▦
//  * @param center
//  */
//case class OutverseSkew(center: Point) extends Piece {
//  override val shape = Line(X, X, O) ~>
//                       Line(O, X, X)
//}
//
///**
//  * ▦
//  * ▦▦▦
//  * @param center
//  */
//case class InverseL(center: Point) extends Piece {
//  override val shape = Line(X, O, O) ~>
//                       Line(X, X, X)
//}
//
///**
//  *   ▦
//  * ▦▦▦
//  * @param center
//  */
//case class OutverseJ(center: Point) extends Piece {
//  override val shape = Line(O, O, X) ~>
//                       Line(X, X, X)
//}
