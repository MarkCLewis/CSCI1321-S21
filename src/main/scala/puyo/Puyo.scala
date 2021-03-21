package puyo

class Puyo(val x: Int, val y: Int, val color: PuyoColor.Value) extends Yo {
  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Puyo = 
    if (isClear(x + dx, y + dy)) new Puyo(x + dx, y + dy, color) else this
}