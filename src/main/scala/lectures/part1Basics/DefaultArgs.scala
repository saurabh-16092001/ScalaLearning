package lectures.part1Basics

object DefaultArgs extends App {
  def trFact(n:Int,acc:Int = 1):Int=  // const acc value if not pass then value is 1 i.e default value
    if(n<=1) acc
    else trFact(n-1, n*acc)
  val fact10 = trFact(10,1) // in this case acc is const
    // leading paraments cannot be resolved  => func(i=1:Int,b:Int)
   def savePic(format:String="jpg",width:Int,height:Int):Unit= println("")
  savePic(width = 23,height = 3)
  /*
    1.pass in every leading argument
    2.name the argument
     */
}
