package puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import java.net.Socket
import java.io.ObjectInputStream
import scala.concurrent.Future
import scalafx.application.Platform

object Client extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)

  val sock = new Socket("localhost", 8080)
  val ois = new ObjectInputStream(sock.getInputStream())

  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  Future {
    while (true) {
      ois.readObject() match {
        case pb: PassableBoard => 
          Platform.runLater(renderer.render(pb))
        case _ =>
      }
    }
  }

  stage = new JFXApp.PrimaryStage {
    title = "Puyo Puyo"
    scene = new Scene(1000, 800) {
      content += canvas

      // onKeyPressed = (ke: KeyEvent) => {
      //   ke.code match {
      //     case KeyCode.Left => board.leftPressed()
      //     case KeyCode.Right => board.rightPressed()
      //     case KeyCode.Up => board.upPressed()
      //     case KeyCode.Down => board.downPressed()
      //     case _ =>
      //   }
      // }
      // onKeyReleased = (ke: KeyEvent) => {
      //   ke.code match {
      //     case KeyCode.Left => board.leftReleased()
      //     case KeyCode.Right => board.rightReleased()
      //     case KeyCode.Up => board.upReleased()
      //     case KeyCode.Down => board.downReleased()
      //     case _ =>
      //   }
      // }
    }
  }
}