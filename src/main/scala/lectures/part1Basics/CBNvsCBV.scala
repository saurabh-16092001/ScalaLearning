package lectures.part1Basics

object CBNvsCBV extends App {
  def callByValue(i :Long): Unit = {
    println("By value : "+i)
    println("By value : "+i)
  }
  def callByName(i: => Long): Unit = {
    println("By Name : "+i)    // println(println("By Name : "+System.nanoTime())
    println("By Name : "+i)    // println(println("By Name : "+System.nanoTime())   => delay's the time to resolve the function time
  }
  callByValue(System.nanoTime())
  callByName(System.nanoTime())
}
