package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val cellSize = 40
  val leftOffset = 50
  val topOffset = 50
  val nextLeftOffset = 5
  val nextTopOffset = 50
  
  def render(board: PassableBoard): Unit = {
    gc.fill = Color.Black
    gc.fillRect(leftOffset, topOffset, cellSize * Board.width, cellSize * Board.height)

    for (yo <- board.allYos; if yo.y >= 0) {
      gc.fill = getColor(yo)
      gc.fillOval(leftOffset + cellSize * yo.x, topOffset + cellSize * yo.y, cellSize, cellSize)
    }
    for (yo <- board.next) {
      gc.fill = getColor(yo)
      gc.fillOval(nextLeftOffset, nextTopOffset + cellSize * (yo.y + 1), cellSize, cellSize)
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