package com.elarib.challenges

import scala.collection.mutable

object Challenge2 extends App{

  /**
    *  1. Define a higher-order function (or a function object) memoize in your favorite language. This function takes
    *  a pure function f as an argument and returns a function that behaves almost exactly like f, except that it only
    *  calls the original function once for every argument, stores the result internally, and subsequently returns this
    *  stored result every time it’s called with the same argument. You can tell the memoized function from the original
    *  by watching its performance. For instance, try to memoize a function that takes a long time to evaluate. You’ll
    *  have to wait for the result the first time you call it, but on subsequent calls, with the same argument, you should
    *  get the result immediately.
    */


  def memoize[A,B](f: A => B): A => B =  {
    val m = mutable.Map.empty[A, B]
    a =>
      m.getOrElse(a, {
        val b = f(a)
        m.put(a, b)
        b
      })
  }


  /***
    * 2. Try to memoize a function from your standard library that you normally use to produce random numbers. Does it work?
    *
    * Yes it works
    */

  val memoizeRandom = memoize[Int, String](scala.util.Random.nextString)

  assert(memoizeRandom(1) == memoizeRandom(1))

  /**
    * 3. Most random number generators can be initialized with a seed.
    * Implement a function that takes a seed, calls the random number generator with that seed,
    * and returns the result. Memoize that function. Does it work?
    *
    * See the memoizeRandom
    */



  /**
    * 5 - How many different functions are there from Bool to Bool? Can you implement them all?
    * We're not taking bottom into the account
    */

  def idBool(x: Boolean) = x

  def invert(x: Boolean): Boolean = !x

  def alwaysTrue(x: Boolean): Boolean = true

  def alwaysFalse(x: Boolean): Boolean = false

//  def bottomBool(x: Boolean): Boolean = ???








}
