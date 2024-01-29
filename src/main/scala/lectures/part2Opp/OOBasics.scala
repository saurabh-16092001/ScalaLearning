package lectures.part2Opp

import java.io
import java.io.Writer

object OOBasics extends App {
  val person = new Person("John",36)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val auther = new Writer("Charles","Dickens",1812)
  val imposter = new Writer("Charles","Dickens",1812)
  val novel1 = new Novel("Great Expectations",1861,auther)
  println(novel1.autherAge())
  println(novel1.isWritternBy(auther))
  println(novel1.isWritternBy(imposter))


  val counter = new Counter();
  counter.inc.print
  counter.inc.inc.inc.print 
}

// class parameters are not fields to make them fields use val
class Person(name:String,val age:Int = 0) { // constructor
  val x = 2

  println(1+2)

  def greet(name:String):Unit = println(s"${this.name} says: Hi,$name")
  // overloading
  def greet():Unit=println(s"Hi $name")

  // multiple constructor
   def this(name:String) = this(name,0) // auxiliary constructor
  def this() = this("John Doe")
}
// exercise


class Writer(firstName:String,surName:String,val year:Int){
  def fullName (): String = firstName+" "+surName
}

class Novel (name:String,yearRelease:Int,auther:Writer){
  def autherAge():Int=yearRelease - auther.year
  def isWritternBy(auther:Writer) = auther == this.auther
  def copy(newYear:Int):Novel = new Novel(name,newYear,auther)
}
class Counter(val cnt:Int =0 ){
  def inc ={
    println("Incrementing")
    new Counter(cnt + 1) // immutability
  }
  def dec = {
    println("Decrementing")
    new Counter ( cnt - 1)
  }
  def inc(n :Int):Counter = {
    if( n <= 0) this
    else
      inc.inc(n-1)
  }
  def dec(n :Int):Counter = {
    if( n <= 0) this
    else
      dec.dec(n-1)
  }

  def print = println(cnt)
}

