package com.elarib.challenges



/**
  * 1. Construct the Kleisli category for partial functions (define composition and identity).
  */


sealed trait Partial[+A]

case class Defined[A](a: A) extends Partial[A]
case object Undefined       extends Partial[Nothing]


object Partial{

  def identity[A](a: A): Partial[A] = Defined(a)

  def compose[A,B,C](f: A => Partial[B], g: B => Partial[C]) : A => Partial[C] =
    a => {
      f(a) match {
        case Defined(b) => g(b)
        case Undefined => Undefined
      }
    }
}



object SafeFunctions {

  /***
    * 2. Implement the embellished function safe_reciprocal that returns a valid reciprocal of its argument, if
    * it’s different from zero.
    */

  def safeReciporcal(x: Double) =
    if(x == 0)
      Undefined
    else Defined(1/x)

  /**
    * 3. Compose safe_root and safe_reciprocal to implement safe_root_reciprocal that calculates sqrt(1/x)
    * whenever possible.
    */
  def safeRoot(x: Double): Partial[Double] =
    if (x > 0)
      Defined(Math.sqrt(x))
    else
      Undefined

  def safeRootReciprocal(x: Double): Double => Partial[Double] = Partial.compose(safeReciporcal, safeRoot)


}




