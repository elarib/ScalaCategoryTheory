package com.elarib.advancedscala.introduction

/**
  * Chapter 1 : Introduction
  */
/**
  * 1.1 Type class anatomy
  */
/**
  * Type class componentes are :
  *  1. The type class itself
  *  2. instances for particular types
  *  3. Interface methods that we expose to users
  */
// 1. The type class itself
sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

final case object JsNull extends Json

// This is the type class for our example, with Json and its subtypes providing supporting code
trait JsonWriter[A] {

  def write(value: A): Json

}

// 2. instances for particular types

final case class Person(name: String, email: String)

/**
  * We define instances by creating concrete implementations of the type class and tagging them with the `implicit` keyword
  */
object JsonWriterInstances {

  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person): Json = JsObject(
      Map(
        "name"  -> JsString(value.name),
        "email" -> JsString(value.email)
      )
    )
  }

}

// 3. Interface methods that we expose to users

/**
  * A type class interface is any functionality we expose to users.
  * Interfaces are generic methods that accept instances of the type class as IMPLICIT parameters, and return the type class
  * The are two ways of specifying an interface : Interface Objects & Interface Syntax
  */
/**
  * 1. Interface Objects
  * Just place methods in a singleton object
  */
object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

object JsonInterefaceObjectTest extends App {
  // 1. Import any type instances we want
  import JsonWriterInstances._
  // 2. Call the relevant method

  Json.toJson(Person("abdel", "emaril"))
}

/**
  *
  */
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }
}

object JsonSyntaxTest extends App {
  // 1. Import any type instances we want
  import JsonWriterInstances._
  // 2. Import any JswonSyntax
  import JsonSyntax._
  // 3. Call the relevant method

  Person("abdel", "emaril").toJson

}

/**
  * 1.2 Working with Implicits
  */
object JsonForOptions {

  /***
    * Implicits Conversions
    * When you create a type class instance constructor using an `implicit def` be sure to mark the parameters to the method as implicit !
    */
  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    override def write(value: Option[A]): Json = value match {
      case Some(a) => writer.write(a)
      case None    => JsNull
    }
  }
}

/**
  * 1.4. Meet Cats
  *
  * Cats sth similar to Printable
  *  1. The type class itself : cats.Show
  *  2. instances for particular types import cats.instances.all._
  *  3. Interface methods that we expose to users import cats.syntax.show._
  */
/**
  * 1.6. Variance, Covariance ... : Relationship between an instance defined on a type and its subtypes
  */
/**
  * the + means covariant, means:
  * if B is a subtype of A => F[B] is a subtype of F[A]
  */
trait F[+A]

// Example

trait List[+A]
sealed trait Shape
case class Circle(radius: Double) extends Shape

object Covariance {

  val circles: List[Circle] = ???
  val shapes: List[Shape]   = circles

}

/**
  * the - means Contravariant, means:
  * if B is a subtype of A => F[A] is a subtype of F[B]
  */
trait E[-A]

// Example

trait ContraList[-A]

object Contavariance {

  val shapes: ContraList[Shape]   = ???
  val circles: ContraList[Circle] = shapes

}

/**
  * Ivariant, means:
  * if there is a relations between A & B => We don't have any relations between F[A] & F[B]
  */
trait I[A]
