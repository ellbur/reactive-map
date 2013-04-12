
package ellbur.collection.reactivemap

import scala.collection.mutable
import ref.WeakReference

class WeakMultiMap[K,V <: AnyRef] {
  type WeakV = WeakReference[V]

  private val map = new mutable.HashMap[K,mutable.Set[WeakV]] with mutable.MultiMap[K,WeakV]

  def put(key: K, value: V) {
    map.addBinding(key, WeakReference(value))
  }
  def get(key: K): Traversable[V] = {
    val them = map.get(key)
    them match {
      case None =>
        None
      case Some(things) =>
        val thingList = things.toList

        var exists: Boolean = false

        val ok = new mutable.ListBuffer[V]
        for (thing <- thingList) thing.get match {
          case None =>
            things -= thing
          case Some(thing) =>
            exists = true
            ok += thing
        }

        if (!exists) {
          map -= key
          None
        }
        else ok
    }
  }
}
