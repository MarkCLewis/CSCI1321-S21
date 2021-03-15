package actors

import scalafx.scene.image.WritableImage
import akka.actor.Actor
import scalafx.scene.paint.Color
import akka.actor.Props
import scalafx.application.Platform

class Solution(image: WritableImage) extends Actor {
  val reader = image.pixelReader.get
  val writer = image.pixelWriter
  for (_ <- 1 to 8) {
    context.actorOf(Props(new Speck))
  }

  def receive = {
    case Solution.Start =>
      for (y <- 0 until image.height().toInt-1; x <- 0 until image.width().toInt) {
        writer.setColor(x, y, Color.Black)
      }
      for (x <- 0 until image.width().toInt) {
        writer.setColor(x, image.height().toInt-1, Color.White)
      }
      for (child <- context.children) {
        child ! Speck.MoveTo(util.Random.nextInt(image.width().toInt), 0)
      }
    case Solution.CheckPosition(x, y, ox, oy) =>
    // println(s"Check $x $y")
      if (x < 0 || x >= image.width()) sender ! Speck.MoveTo(ox, oy)
      else if (reader.getColor(x, y) == Color.White) {
        // println(s"Set at $ox, $oy")
        Platform.runLater(writer.setColor(ox, oy, Color.White))
        sender ! Speck.MoveTo(util.Random.nextInt(image.width().toInt), 0)
      } else sender ! Speck.MoveTo(x, y)

    case m => println(s"Unhandled message in Solution: $m")
  }
}

object Solution {
  case object Start
  case class CheckPosition(x: Int, y: Int, ox: Int, oy: Int)
}