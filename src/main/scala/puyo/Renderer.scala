package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val cellSize = 40
  def render(board: PassableBoard): Unit = {
    gc.fill = Color.Black
    gc.fillRect(50, 50, cellSize * Board.width, cellSize * Board.height)

    for (yo <- board.allYos; if yo.y >= 0) {
      gc.fill = getColor(yo)
      gc.fillOval(50 + cellSize * yo.x, 50 + cellSize * yo.y, cellSize, cellSize)
    }
    for (yo <- board.next) {
      gc.fill = getColor(yo)
      gc.fillOval(5, 50 + cellSize * (yo.y + 1), cellSize, cellSize)
    }
  }

  def getColor(yo: PassableYo): Color = yo.color match {
    case PuyoColor.Red => Color.Red
    case PuyoColor.Green => Color.Green
    case PuyoColor.Blue => Color.Blue
    case PuyoColor.Purple => Color.Purple
    case PuyoColor.Yellow => Color.Yellow
    case PuyoColor.Gray => Color.Gray
  }
}