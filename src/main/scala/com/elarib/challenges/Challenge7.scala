package com.elarib.challenges

import scala.collection.AbstractSeq


sealed trait MaybeS[+A]
case class JustS[A](a: A) extends MaybeS[A]
case object NanS extends MaybeS[Nothing]



sealed trait ListS[+A] {


  def map[A,B](f: A => B)(implicit listS: ListS[A]): ListS[B] = listS match {
    case NilS =>  NilS
    case tails: TailS[A] => TailS(f(tails.head) , map(f)(tails.tail))

  }
}
case class TailS[A](head: A, tail: ListS[A]) extends ListS[A]
case object NilS extends ListS[Nothing]



object Challenge7 extends App {

  val f = (i: Int) => s"$i+"

  implicit val listS: ListS[Int] = TailS(1, TailS(2, NilS))
  val res = listS.map(f)

  println(res)




}
