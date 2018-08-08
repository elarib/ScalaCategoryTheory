package com.elarib.challenges

object Challenge1 extends App {

  /**
    * 1. Implement, as best as you can, the identity function in your fa- vorite language (or the second favorite, if your favorite language happens to be Haskell).
    */
  def id[A](x: A) = x

  /**
    * 2. Implement the composition function in your favorite language. It takes two functions as arguments and returns a function that is their composition.
    */
  def compose[A, B, C](f: A => B, g: B => C): A => C = f.andThen(g)


  def compose3[A, B, C]: ((A => B), (B => C)) => (A => C) =
                            (f,g) => a => (g(f(a)))


  /**
    * 3. Write a program that tries to test that your composition function respects identity.
    */

  assert(compose((x: Int) => x + 0.5, (l: Double) => l.toString)(1) == "1.5")



  /**
    * 4. Write a program that tries to test that your composition function respects identity.
    */


  /**
    * 5. Is Facebook a category, with people as objects and friendships as morphisms?
    *
    * Id OK : Everyone is a friend with himself
    * Composition Nop : If A is a friend with B, and B is a friend with C, A is not friend with C automatically
    *
    * But if morphisime is accessibility
    *  Id is OK : Everyone is accessible by himeself
    *  Composition is OK : A is accessible from B, and B is accessible from C, so A is accessible from C
    *
    */


  /**
    * 6. When is a directed graph a category?
    * if :
    *      each node is linked to itself
    *      if for a link A -> B and B -> C, there is a link between A -> C
    */
}