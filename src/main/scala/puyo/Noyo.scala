package puyo

class Noyo(val x: Int, val y: Int) extends Yo {
  def color = PuyoColor.Gray

  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Noyo = 
    if (isClear(x + dx, y + dy)) new Noyo(x + dx, y + dy) else this

  def makePassable(): PassableYo = PassableYo(x, y, color)
}