package com.elarib.advancedscala.introduction
//1
sealed trait Printable[A] {
  def format(a: A): String
}

//2
object PrintableInstances {

  implicit val printableString: Printable[String] = new Printable[String] {
    override def format(a: String): String = a
  }

  implicit val printableInt: Printable[Int] = new Printable[Int] {
    override def format(a: Int): String = s"$a"
  }

}

//3
object Printable {

  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)

  def write[A](a: A)(implicit printable: Printable[A]): Unit = println(format(a))

}

final case class Cat(name: String, age: Int, color: String)

object PrintableCat {
  implicit def printableCat(implicit stringPrintable: Printable[String], intPrintable: Printable[Int]): Printable[Cat] =
    new Printable[Cat] {
      override def format(cat: Cat): String =
        s"${Printable.format(cat.name)} is ${Printable.format(cat.age)} year-old and ${Printable.format(cat.color)} cat"
    }
}

//4 Better syntax

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit printable: Printable[A]): String = printable.format(a)

    def print(implicit printable: Printable[A]): Unit = println(format)
  }
}

// 1.4.6 Exercice Cat Show

import cats.Show

object CatShow {

//  implicit def catShow(implicit stringCatShow: Show[String], intCatShow: Show[Int]): Show[Cat] = new Show[Cat] {
//    override def show(cat: Cat): String =
//      s"NAME is ${Show.fromToString.show(cat.name)} AGE ${Show.fromToString
//        .show(cat.age)} yearold COLOR ${Show.fromToString.show(cat.color)}"
//  }
  import cats.instances.all._
  import cats.syntax.show._

  implicit val show = Show.show[Cat] { cat =>
    s"${cat.name.show} is ${cat.age.show} year-old and ${cat.color.show} cat"

  }
}

// 1.5.5 Equality
import cats.Eq
object CatEq {
  import cats.instances.all._
  import cats.syntax.eq._
  implicit val catEqual: Eq[Cat] = Eq.instance[Cat]((cat1, cat2) => {
    cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
  })

}

object Exercice extends App {

  // Test the first exercice

  import PrintableInstances._
  assert(Printable.format("abcd") == "abcd")
  Printable.write("abcd")
  assert(Printable.format(123) == "123")
  Printable.write(123)
  // Test Second exercice
  import PrintableCat._
  val cat = Cat("Xi", 12, "black")

  assert(Printable.format(cat) == "Xi is 12 year-old and black cat")
  Printable.write(cat)

  import PrintableSyntax._

  assert(cat.format == "Xi is 12 year-old and black cat")
  cat.print

  // Test 1.4.6 exercice
  import cats.syntax.show._
  import CatShow._
  assert(cat.show == "Xi is 12 year-old and black cat")

  // Test 1.5.5 Equality
//  import cats.Eq
//  import CatEq._
//  import cats.syntax.eq._
//  assert(cat === cat, true)
//  println(cat === cat.copy(name = "cat2"))

}
