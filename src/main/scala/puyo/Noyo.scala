package puyo

class Noyo(val x: Int, val y: Int) extends Yo {
  def color = PuyoColor.Gray

  def move(dx: Int, dy: Int) = new Noyo(x + dx, y + dy)
}