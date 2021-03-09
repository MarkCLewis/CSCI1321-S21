package mud

import akka.actor.ActorRef

class Player() {
  def processCommand(command: String): Unit = ???
  def getFromInventory(itemName: String): Option[Item] = ???
  def addToInventory(item: Item): Unit = ???
  def inventoryListing(): String = ???
  def move(dir: String): Unit = ???

}

object Player {
  case class PrintMessage(msg: String)
  case class TakeExit(oroom: Option[ActorRef])
}