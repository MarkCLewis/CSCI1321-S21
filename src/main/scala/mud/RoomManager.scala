package mud

import akka.actor.Actor
import scala.io.Source
import akka.actor.ActorRef
import akka.actor.Props

class RoomManager extends Actor {
  val rooms = readRooms()
  for (child <- context.children) child ! Room.LinkExits(rooms)

  // def readRooms(): Map[String, ActorRef] = {
  //   val source = Source.fromFile("map.txt")
  //   val lines = source.getLines()
  //   val ret = Array.fill(lines.next().toInt)(readRoom(lines)).toMap
  //   source.close()
  //   ret
  // }

  // def readRoom(lines: Iterator[String]): (String, ActorRef) = {
  //   val key = lines.next()
  //   val name = lines.next()
  //   val desc = lines.next()
  //   val exits = lines.next().split(",")
  //   val items = List.fill(lines.next().toInt)(Item(lines.next(), lines.next()))
  //   key -> context.actorOf(Props(new Room(name, desc, exits, items)), key)
  // }

  def readRooms(): Map[String, ActorRef] = {
    val xmlData = xml.XML.loadFile("map.xml")
    (xmlData \ "room").map(readRoom).toMap
  }

  def readRoom(node: xml.Node): (String, ActorRef) = {
    val key = (node \ "@keyword").text
    val name = (node \ "@name").text
    val desc = (node \ "description").text
    val exits = (node \ "exits").text.split(",")
    val items = (node \ "item").map(n => Item((n \ "@name").text, n.text)).toList
    key -> context.actorOf(Props(new Room(name, desc, exits, items)), key)
  }

  def receive = {
    
    case m => println("Unhandled message in RoomManager: " + m)
  }

}