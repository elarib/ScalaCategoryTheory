package com.elarib.challenges


/**
  * 4. Implement Either
  */
sealed trait EitherS[+A,+B]
case class LeftS[A](a: A) extends EitherS[A, Nothing]
case class RightS[B](b: B) extends EitherS[Nothing, B]

object Challenge5 extends App {


  val left = LeftS(1)

  val right = RightS("aaa")



  println(left)
  println(right)

}




