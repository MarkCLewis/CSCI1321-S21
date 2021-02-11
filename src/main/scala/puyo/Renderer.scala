package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  def render(board: Board): Unit = {
    gc.fill = Color.Black
    gc.fillRect(50, 50, 200, 500)

    for (yo <- board.yos) {
      yo.color match {
        case PuyoColor.Red => gc.fill = Color.Red
        case PuyoColor.Green => gc.fill = Color.Green
        case PuyoColor.Blue => gc.fill = Color.Blue
        case PuyoColor.Purple => gc.fill = Color.Purple
        case PuyoColor.Yellow => gc.fill = Color.Yellow
      }
      gc.fillOval(50 + 20 * yo.x, 50 + 20 * yo.y, 20, 20)
    }
  }
}