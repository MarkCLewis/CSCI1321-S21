package actors

import akka.actor.Actor

class Speck extends Actor {
  def receive = {
    case Speck.MoveTo(x, y) =>
      var dx = util.Random.nextInt(3) - 1
      var dy = util.Random.nextInt(2)
      while (dx == 0 && dy == 0) {
        dx = util.Random.nextInt(3) - 1
        dy = util.Random.nextInt(2)
      }
      sender ! Solution.CheckPosition(x+dx, y+dy, x, y)
    case m => println(s"Unhandled message in Speck: $m")
  }

}

object Speck {
  case class MoveTo(x: Int, y: Int)
}