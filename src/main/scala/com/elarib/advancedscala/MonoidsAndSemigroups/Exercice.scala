package com.elarib.advancedscala.MonoidsAndSemigroups

/**
  * 2.3 Exercice: The truth about monoids
  */
object BooleanMonoids {

  implicit val AND: MonoidSimplfied[Boolean] = new MonoidSimplfied[Boolean] {
    override def empty: Boolean                           = true
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val OR: MonoidSimplfied[Boolean] = new MonoidSimplfied[Boolean] {
    override def empty: Boolean                           = false
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val XOR: MonoidSimplfied[Boolean] = new MonoidSimplfied[Boolean] {
    override def empty: Boolean                           = false
    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }

  implicit val XNOR: MonoidSimplfied[Boolean] = new MonoidSimplfied[Boolean] {
    override def empty: Boolean                           = true
    override def combine(x: Boolean, y: Boolean): Boolean = (x || !y) && (!x || y)
  }

}

object Exercice extends App {

  import BooleanMonoids._
  assert(LawsImpl.associativeLaw(true, false, true)(AND), true)
  assert(LawsImpl.associativeLaw(true, false, true)(OR), true)
  assert(LawsImpl.associativeLaw(true, false, true)(XOR), true)
  assert(LawsImpl.associativeLaw(true, false, true)(XNOR), true)
  assert(LawsImpl.identityLaw(true)(AND), true)
  assert(LawsImpl.identityLaw(false)(OR), true)
  assert(LawsImpl.identityLaw(false)(XOR), true)
  assert(LawsImpl.identityLaw(true)(XNOR), true)

}
