package puyo

class Noyo(val x: Int, val y: Int) extends Yo {
  def color = PuyoColor.Gray

  def drop = new Noyo(x, y + 1)
}