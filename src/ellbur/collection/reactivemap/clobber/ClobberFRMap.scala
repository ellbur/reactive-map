
package ellbur.collection.reactivemap.clobber

import ellbur.dependenttypes._
import reactive._
import com.github.ellbur.collection.dependentmap.immutable._

class ClobberFRMap[Key <: Depender](val renew: EventStream[DependentMap[Key]]) {
  def get(key: Key): Signal[Option[key.T]] = (renew map {
    it =>
      it.get(key)
  }).hold(None)

  def toMap: Signal[DependentMap[Key]] = renew.hold(DependentMap())
}
