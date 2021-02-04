package mud

import scala.io.Source

class Room(val name: String, val desc: String, private val exits: Array[Int], private var items: List[Item]) {
  def fullDescription(): String = ???

  def getExit(dir: Int): Option[Room] = ???

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
  val rooms = readRooms()

  def readRooms(): Array[Room] = {
    val source = Source.fromFile("map.txt")
    val lines = source.getLines()
    val ret = Array.fill(lines.next().toInt)(readRoom(lines))
    source.close()
    ret
  }

  def readRoom(lines: Iterator[String]): Room = {
    val name = lines.next()
    val desc = lines.next()
    val exits = lines.next().split(",").map(_.toInt)
    val items = List.fill(lines.next().toInt)(Item(lines.next(), lines.next()))
    new Room(name, desc, exits, items)
  }
}