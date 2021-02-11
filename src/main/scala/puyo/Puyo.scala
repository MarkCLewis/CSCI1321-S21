package puyo

class Puyo(val x: Int, val y: Int, val color: PuyoColor.Value) extends Yo {
  def drop = new Puyo(x, y + 1, color)
}