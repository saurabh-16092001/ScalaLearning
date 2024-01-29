package lectures.part1Basics

object Expressions extends App {
  val x = 1+'a'
  val a = 1+2

  println(x)

  // + - / * % ==<< >>  !=   >>> (right shift with zero extension)
  println(3==a)
  var aVariable = 2
  aVariable +=4 // changing variable ... side effects
  println(aVariable)
  val aCondition : Boolean = true
  val aConditionValue = if(aCondition) 5 else 3
  println(aConditionValue)

  var i = 0
  while(i<10){ // avoid this
    println(i)
    i+=1
  }
  // everything in scala is expression

  val aWeirdValue = (aVariable=3) // unit == void
  println(aWeirdValue)

  // side effects : println(),whiles,reassigning

  // code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if(z>2) "hello" else "good bye" // value of code block i.e last line of code block
  }
  println(aCodeBlock)
//  val anotherValue = z + 1  error
}