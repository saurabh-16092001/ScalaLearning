package lectures.part3fp.AnonymousFunction

object AnonymousFunctions extends App {
  // anonymous function (LAMBDA)
  // for lambda call function with params ()
  val doubler = (x:Int)=> x * 2
//  val doubler:Int => Int =( x :Int) => x * 2 same as above
  // multiple parameters
  val func = (x:Int,y:Int)=> x+y
//  val func:(Int,Int)=>Int = (x:Int,y:Int) => x + y
  // no params
  val noParams = () => 4
  println(noParams) // return instance of function
  println(noParams()) // return value

  // Curly braces
  val stringToInt = { (str:String)=>
    str.toInt
  }
  println(stringToInt("6"))
  //MOAR syntactic sugar
  val incrementor:Int => Int = _ + 1 // equivalent to (x:Int) => x + 1
  val incrementorWithParams : (Int,Int) => Int = _ + _  // a + b
  /// curred function in lambda
  val curried = (x:Int) =>(y:Int)=> x + y
  println(curried(3)(4))
}