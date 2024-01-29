package lectures.part3fp.HOF

object HOFCurries extends App {
  // higher order function (HOF)
  val superFunction:(Int,(String,(Int => Boolean)) => Int) => (Int => Int) = null
  // map,filter in list

  // function that applies a function n times over a value x
  // nTimes(f,n,x)
  // nTimes(f,3,x) = f(f(f(x))) = nTimes(f,2,f(x)) = f(f(f(x)))
   //  =nTimes(f,n-1,f(x))
   def nTimes(f:Int => Int,n:Int,x:Int):Int ={
    if(n <= 0) x
    else nTimes(f,n-1,f(x))
  }
  val printOne = (x :Int) => x + 1
  //ntb(f,n)=x => f(f(f..(x)))

  def nTimesBetter(f:Int => Int,n:Int):(Int=>Int)={
    if(n<=0) (x:Int) => x
    else (x:Int) => nTimesBetter(f,n-1)(f(x))
  }
  //curried function
  def superAdder : Int => Int => Int = (x:Int)=>(y:Int) => x + y
  val power=superAdder(3)
  println(nTimes(printOne,10,1))
  println(nTimesBetter(printOne,10)(1))
  println(power(2))

  // functions with multiple parameters
  def curriedFormatters(c:String)(x:Double):String=c.format(x)
  val standardFormatter :(Double => String) = curriedFormatters("%4.4f")
}
