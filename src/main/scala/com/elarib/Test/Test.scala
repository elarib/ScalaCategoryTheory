package com.elarib.Test

case class User(id: Int, name: String, creationDate: Long)

case class Job(id: Int, description: String,  creationDate: Long)


object Test extends App {


  import scala.reflect.runtime.universe._

  println(updateInstances(User))




  def updateInstances[T](obj: T) = {
    val className = obj.getClass.getName


    className


  }



}
