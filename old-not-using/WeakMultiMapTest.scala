import ref.WeakReference

object WeakMultiMapTest extends App {
  import ellbur.collection.reactivemap.WeakMultiMap

  case class Joe(name: String) {
    override def finalize() { println(s"Bye bye $name!") }
  }

  val map = new WeakMultiMap[Int,Joe]
  val j = new java.lang.ref.WeakReference[Joe](Joe("Cinderella"))
  //map.put(1, Joe("Cinderella"))
  System.gc()
  System.runFinalization()

  println("Yes!")
}
