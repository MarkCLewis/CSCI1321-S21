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
import java.io.ObjectOutputStream
import scalafx.scene.control.TextInputDialog

object Client extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)

  val dialog = new TextInputDialog("localhost")
  dialog.contentText = "What machine is the server running on?"
  dialog.title = "Server"
  dialog.headerText = "Server Machine"
  val serverMachine = dialog.showAndWait().getOrElse("localhost")

  val sock = new Socket(serverMachine, 8080)
  val ois = new ObjectInputStream(sock.getInputStream())
  val oos = new ObjectOutputStream(sock.getOutputStream())

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

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => oos.writeInt(ControlKeys.Pressed); oos.writeInt(ControlKeys.Left); oos.flush()
          case KeyCode.Right => oos.writeInt(ControlKeys.Pressed); oos.writeInt(ControlKeys.Right); oos.flush()
          case KeyCode.Up => oos.writeInt(ControlKeys.Pressed); oos.writeInt(ControlKeys.Up); oos.flush()
          case KeyCode.Down => oos.writeInt(ControlKeys.Pressed); oos.writeInt(ControlKeys.Down); oos.flush()
          case _ =>
        }
      }
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => oos.writeInt(ControlKeys.Released); oos.writeInt(ControlKeys.Left); oos.flush()
          case KeyCode.Right => oos.writeInt(ControlKeys.Released); oos.writeInt(ControlKeys.Right); oos.flush()
          case KeyCode.Up => oos.writeInt(ControlKeys.Released); oos.writeInt(ControlKeys.Up); oos.flush()
          case KeyCode.Down => oos.writeInt(ControlKeys.Released); oos.writeInt(ControlKeys.Down); oos.flush()
          case _ =>
        }
        oos.flush()
      }
    }
  }
}