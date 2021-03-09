package mud

import scala.io.Source
import akka.actor.Actor
import akka.actor.ActorRef

class Room(
  val name: String, 
  val desc: String, 
  private val exitKeys: Array[String], 
  private var items: List[Item]
) extends Actor {

  private var exits: Array[Option[ActorRef]] = null

  import Room._
  def receive = {
    case LinkExits(rooms) => 
      exits = exitKeys.map(key => rooms.get(key))
    case FullDescription => 
      sender ! Player.PrintMessage(fullDescription())
    case GetExit(dir) => 
      sender ! Player.TakeExit(getExit(dir))
    case m => println("Unhandled message in Room: " + m)
  }

  def fullDescription(): String = ???

  def getExit(dir: Int): Option[ActorRef] = exits(dir)

  def getItem(itemName: String): Option[Item] = {
    items.find(_.name == itemName) match {
      case Some(item) =>
        val index = items.indexOf(item)
        items = items.patch(index, Nil, 1)
        Some(item)
      case None => None
    }
  }

  def dropItem(item: Item): Unit = ???

}

object Room {
  case class LinkExits(rooms: Map[String, ActorRef])
  case object FullDescription
  case class GetExit(dir: Int)
}