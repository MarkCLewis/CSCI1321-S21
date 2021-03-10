package actors

import akka.actor.ActorSystem
import scalafx.scene.image.WritableImage
import scalafx.application.JFXApp
import scalafx.scene.image.ImageView
import scalafx.scene.Scene
import scalafx.scene.image.PixelWriter
import scalafx.scene.paint.Color

object CrystalGrowth extends JFXApp {
  val system = ActorSystem("CrystalGrowth")
  val image = new WritableImage(1000, 1000)
  val view = new ImageView(image)
  val writer = image.pixelWriter
  for (y <- 0 until image.height().toInt-1; x <- 0 until image.width().toInt) {
    writer.setColor(x, y, Color.Black)
  }
  for (x <- 0 until image.width().toInt) {
    writer.setColor(x, image.height().toInt-1, Color.White)
  }

  stage = new JFXApp.PrimaryStage {
    title = "Crystal Growth"
    scene = new Scene(image.width(), image.height()) {
      content = view
    }
  }
}