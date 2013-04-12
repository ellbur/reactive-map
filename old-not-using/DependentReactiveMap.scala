
package ellbur.collection.reactivemap
import reactive._
import collection.mutable

trait Typed {
  type T
}

trait DependentPair[A<:Typed] {
  val car: A
  val cdr: car.T
}

trait KeyTyped {
  type Key <: Typed
  type Pair = DependentPair[Key]

  class DependentReactiveMap(put: EventStream[Pair], clear: EventStream[Key]) extends Observing {
    trait Listener {
      def changed(next: Any)
    }

    // TODO
    case class Listened(value: Any, listeners: Nothing)

    private val map = scala.collection.mutable.Map[Key,Any]()
    private val present = mutable.HashMap[Key,Listened]()
    private val absent = ???

    put foreach { pair =>
      ???
    }

    clear foreach { key =>
      ???
    }

    def get(key: Key): EventStream[Option[key.T]] = {
      val source = new EventSource[Option[key.T]]
      ???
    }
  }
}
