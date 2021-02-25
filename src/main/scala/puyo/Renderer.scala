package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val cellSize = 40
  def render(board: Board): Unit = {
    gc.fill = Color.Black
    gc.fillRect(50, 50, cellSize * board.width, cellSize * board.height)

    for (yo <- board.allYos; if yo.y >= 0) {
      yo.color match {
        case PuyoColor.Red => gc.fill = Color.Red
        case PuyoColor.Green => gc.fill = Color.Green
        case PuyoColor.Blue => gc.fill = Color.Blue
        case PuyoColor.Purple => gc.fill = Color.Purple
        case PuyoColor.Yellow => gc.fill = Color.Yellow
        case PuyoColor.Gray => gc.fill = Color.Gray
      }
      gc.fillOval(50 + cellSize * yo.x, 50 + cellSize * yo.y, cellSize, cellSize)
    }
  }
}