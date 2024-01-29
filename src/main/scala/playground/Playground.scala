package playground

import scala.{+:, ::}

object Playground {

  def main(args: Array[String]): Unit = {
   val arr:List[Int] = List(1,2,3,4,5)
    val newElement = 6 :: arr
    println(newElement)

  }
}
