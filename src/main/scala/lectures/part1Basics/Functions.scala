package lectures.part1Basics

object Functions extends App {
  private def aFunction(a:String, b:Int): String = {
    a + " " + b
  }
  println(aFunction("hello",3))

  def parameterLessFunction ():String ={
    "Hello This is parameter less"
  }
  println(parameterLessFunction())

  def aRecursive (a : String , b : Int): String ={ // return type is must
    if(b==1) a
    else a + aRecursive(a,b-1)
  }
  println(aRecursive("Hello",3))

  //When you need loops use recursion.
  def aFunctionWithSideEffect(aString : String) : Unit = println(aString)

  def aBigFunction(n : Int):Int={
    def aSmallFunction(a:Int,b:Int):Int = a + b
    aSmallFunction(n,n+1)
  }
  println(aBigFunction(4))

  def greetings(name:String,age:Int):String=
    "Hi, my name is " + name +" and I am "+ age +" old."
  def factorial(num:Int):Int={
    if(num==1 || num ==0) 1
    else num*factorial(num - 1)
  }

  def fibbonacci(num:Int):Int={
    if(num <= 2) 1
    else fibbonacci(num-1) + fibbonacci(num-2)
//    num
  }

  def isPrime(n:Int):Boolean={
    def isPrimeUntil(t:Int):Boolean=
      if(t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    isPrimeUntil(n/2)
  }
  println(greetings("saurabh",22))
  println(factorial(5))
  println(fibbonacci(8))
  println(isPrime(22 ))
  
}
