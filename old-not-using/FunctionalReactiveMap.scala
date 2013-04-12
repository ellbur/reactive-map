
import reactive._

object FunctionalReactiveMap extends App with Observing {
  val kv = new KeyValue {
    type Key = Int
    type Value = String

    val ordering: Ordering[Key] = implicitly
  }

  import kv._

  val put = new EventSource[(Key, Value)]
  val clear = new EventSource[Key]

  val map = new LinearFRMap(put, clear)
  map.get(1) foreach { now =>
    println(s"Now it is $now")
  }

  put.fire(2, "Hello")
  put.fire(1, "Joe")
  put.fire(1, "No")
  put.fire(2, "Bye")
  clear.fire(1)
  clear.fire(2)
}
