package puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

// * Open Window
// * Make the window the right size
// * Draw board (background)
// * Display Puyo
// * Display Noyo
// * Display multiple Puyos
// * Include the falling puyos
// * Don't draw Puyo above the top
// * Falls pieces
// * Moving
// * Moving and falling at the right speed
// * Walls
// * Rotating
// * Stack
// * Things go away

object Main extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  val board = new Board

  stage = new JFXApp.PrimaryStage {
    title = "Puyo Puyo"
    scene = new Scene(1000, 800) {
      content += canvas

      var lastTime = -1L
      val timer = AnimationTimer { time =>
        if (lastTime >= 0) {
          val delay = (time - lastTime) / 1e9
          board.update(delay)
          renderer.render(board.makePassable)
        }
        lastTime = time
      }
      timer.start()

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => board.leftPressed()
          case KeyCode.Right => board.rightPressed()
          case KeyCode.Up => board.upPressed()
          case KeyCode.Down => board.downPressed()
          case _ =>
        }
      }
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => board.leftReleased()
          case KeyCode.Right => board.rightReleased()
          case KeyCode.Up => board.upReleased()
          case KeyCode.Down => board.downReleased()
          case _ =>
        }
      }
    }
  }
}