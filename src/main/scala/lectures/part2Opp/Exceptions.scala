package lectures.part2Opp

object Exceptions extends App {
  val x : String = null
//  println(x.length) null ptr exception
  // 1. throwing exception
//  val aWeiredValue : String = throw new NullPointerException

  // throwable classes extends the Throwable class.
  // 2. Catch exception
  def getInt(withExceptions : Boolean):Int =
    if(withExceptions) throw new RuntimeException("No int !")
    else 42
  try{
    // code that might fail
    getInt(true)
  }catch{
    case e:RuntimeException => println("Caught a Runtime exception")
  }finally {
    // code that will get executed no matter what
    println("Finally !")
  }
  // 3 .how to define your own exception
  class MyException extends Exception
  val  exceptions  = new MyException
//  throw exceptions
  /*
  1. Crash your program with an OutOfMemoryError
  2. Crash with SOError
  3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

      Throw
      - OverflowException if add(x,y) exceeds INT.MAX_VALUE
      - UnderflowException if substract(x,y) exceeds INT.MIN_VALUE
      - MatchCalculationException for division by 0
   */
  // OOM
//    val array = Array.ofDim(Int.MaxValue)
  // SO
//  def infinite : Int = 1 + infinite
//  val noLimit = infinite
  class OverFlowException extends RuntimeException
  class UnderFlowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero !")
  object pocketCalculator{
    def add(x:Int,y:Int): Int = {
      val res = x + y

      if(x > 0 && y > 0 && res < 0 ) throw new OverFlowException
      else if (x > 0 && y > 0 && res > 0) throw new UnderFlowException
      else res
    }
    def subtract(x:Int,y:Int): Int ={
      val res = x - y
      if (x > 0 && y < 0 && res < 0) throw new OverFlowException
      else if (x < 0 && y > 0 && res > 0) throw new UnderFlowException
      else res
    }

    def multiply(x: Int, y: Int): Int = {
      val res = x * y
      if (x > 0 && y > 0 && res < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && res > 0) throw new OverFlowException
      else if (x > 0 && y < 0 && res > 0) throw new UnderFlowException
      else if (x < 0 && y < 0 && res > 0) throw new UnderFlowException
      else res
    }
    def divide(x:Int,y:Int): Int = {
      if(y==0) throw new MathCalculationException
      else x/y
    }
  }
}
