package puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas

// * Open Window
// * Make the window the right size
// * Draw board (background)
// * Display Puyo
// Display Noyo
// Display multiple Puyos
// Walls
// Falls pieces
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
      renderer.render(board)
    }
  }
}