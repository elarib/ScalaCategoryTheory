package com.elarib.advancedscala.MonoidsAndSemigroups

/**
  * Monoids and Semigroups
  */
/**
  * Monoid for a type A must :
  *   . have an operation `combine` with type `(A, A) => A`
  *   . have an element `empty` of type `A`
  *   . the operation `combine` must be associative
  *   . the operation `empty` must be an identity element
  */
trait Monoid[A] {
  def combine(a: A, b: A): A
  def empty: A
}
// just to illustrate the two Laws
object LawsImpl {

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: MonoidSimplfied[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: MonoidSimplfied[A]): Boolean = {
    (m.combine(x, m.empty) == x) && m.combine(m.empty, x) == x
  }
}

/***
  * Semigroup is just the `combine` part of monoid
  */
trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait MonoidSimplfied[A] extends Semigroup[A] {
  def empty: A
}

class Course {}
