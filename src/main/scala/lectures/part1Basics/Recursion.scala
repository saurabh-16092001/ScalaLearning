package lectures.part1Basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n:Int):Int= {
    if(n<=1) 1
    else {
      n * factorial(n - 1) // for large input => stackOverFlow error
    }
  }
  // sol
  def anotherFactorial(n:Int):BigInt={
    @tailrec
    def factHelper(x:Int,accumulator : BigInt):BigInt={
      if(x<=1) accumulator
      else factHelper(x-1,x * accumulator) // TAIL RECURSION = use recursive call as the last expression
    }
    factHelper(n,1)
  }

//  println(anotherFactorial(1000))
  // when you need loops use tail_recursion
  @tailrec
  def stringNTimesTailRec(str : String,n :Int,accumulator:String):String={
    if(n <= 0 ) accumulator
    else stringNTimesTailRec(str,n-1,str + accumulator)
  }

  def isPrime(n:Int):Boolean={
    @tailrec
    def isPrimeTailRec(t:Int,isStillPrime:Boolean):Boolean={
      if(!isStillPrime) false
      else if(t<=1) true
      else isPrimeTailRec(t-1,n%t != 0 && isStillPrime)
    }
    isPrimeTailRec(n / 2,true)
  }
  def fibbonacciUsingTaiRec(num: Int): Int = {
    @tailrec
    def fibHelper(i:Int,accumulator1:Int,accumulator2:Int):Int= {
      if (i >= num) accumulator1
      else fibHelper(i + 1,accumulator1+accumulator2,accumulator1)
    }
    if(num <= 2) 1
    else fibHelper(2,1,1)
  }
//  println(stringNTimesTailRec("Hello!",4,""))
  println(fibbonacciUsingTaiRec(8))
  println(isPrime(12))
}
