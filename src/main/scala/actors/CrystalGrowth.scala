package actors

import akka.actor.ActorSystem
import scalafx.scene.image.WritableImage
import scalafx.application.JFXApp
import scalafx.scene.image.ImageView
import scalafx.scene.Scene
import scalafx.scene.image.PixelWriter
import scalafx.scene.paint.Color
import akka.actor.Props

object CrystalGrowth extends JFXApp {
  val system = ActorSystem("CrystalGrowth")
  val image = new WritableImage(1000, 1000)
  val view = new ImageView(image)
  val solution = system.actorOf(Props(new Solution(image)), "Solution")

  stage = new JFXApp.PrimaryStage {
    title = "Crystal Growth"
    scene = new Scene(image.width(), image.height()) {
      content = view

      solution ! Solution.Start
    }
  }
}