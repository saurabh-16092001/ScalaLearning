package lectures.part2Opp

object Enums {

  enum Permissions{
    case READ,WRITE,EXECUTE,NONE
    // add field's or method's
    def openDocument():Unit={
      if(this == READ) println("Opening document..")
      else println("reading or writing is not allowed.")
    }
  }

  val somePermissions:Permissions = Permissions.READ
  // Constructor argument
  enum PermissionWithBits(bits:Int){
    case READ extends PermissionWithBits(4) // 100
    case WRITE extends PermissionWithBits(2) // 010
    case EXECUTE extends PermissionWithBits(1) // 001
    case NONE extends  PermissionWithBits(0) // 000
  }
  // source
  object PermissionWithBits{
    def fromBits(bits:Int):PermissionWithBits=PermissionWithBits.READ
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal  // indexes
  val allPermissions = PermissionWithBits.values // values
  val readPermission:Permissions = Permissions.valueOf("READ") // permission.read

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
    println(readPermission)
  }
}
