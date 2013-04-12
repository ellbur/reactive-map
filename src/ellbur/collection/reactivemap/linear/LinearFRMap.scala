
import reactive._

trait KeyValue {
  type Key
  type Value

  /**
   * This is a very stupid linear-time algorithm, but it is,
   * apparently, entirely frp.
   */
  class LinearFRMap(put: EventStream[(Key, Value)], clear: EventStream[Key]) {
    sealed trait Operation
    case class Put(key: Key, value: Value) extends Operation
    case class Clear(key: Key) extends Operation

    val putOp = put map { case (k, v) => Put(k, v) }
    val clearOp = clear map (Clear(_))

    val op = putOp | clearOp

    def get(key: Key): Signal[Option[Value]] = (op collect {
      case Put(`key`, value) => Some(value)
      case Clear(`key`) => None
    }).hold(None)
  }
}
