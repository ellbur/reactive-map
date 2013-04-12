
package ellbur.collection.reactivemap.clobber

import reactive.{Signal, EventStream}

class ClobberFRMapID[Key,Value](val renew: EventStream[Map[Key,Value]]) {
  def get(key: Key): Signal[Option[Value]] = (renew map {
    it =>
      it.get(key)
  }).hold(toMap.now.get(key))

  lazy val toMap: Signal[Map[Key,Value]] = renew.hold(Map())
}
