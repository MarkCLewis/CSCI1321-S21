package puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer

// * Open Window
// * Make the window the right size
// * Draw board (background)
// * Display Puyo
// * Display Noyo
// * Display multiple Puyos
// * Include the falling puyos
// * Don't draw Puyo above the top
// * Falls pieces
// Walls
// Moving
// Rotating

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
          renderer.render(board)
        }
        lastTime = time
      }
      timer.start()
    }
  }
}