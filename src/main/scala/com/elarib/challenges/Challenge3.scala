package com.elarib.challenges



trait Monoid[A] {

  def empty: A
  def append(x: A, y: A): A

}


object Challenge3 extends App{

  /**
    * // 3 - Considering that Bool is a set of two values True and False, show that it forms two (set-theoretical)
    * // monoids with respect to, respectively, operator && (AND) and || (OR).
    */

  val boolAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true
    override def append(x: Boolean, y: Boolean): Boolean = x && y
  }

  val boolOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = false
    override def append(x: Boolean, y: Boolean): Boolean = x || y

  }
}
