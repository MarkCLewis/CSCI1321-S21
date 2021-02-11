package puyo

class Board {
  val width = 6
  val height = 12
  private var _yos: List[Yo] = List(new Noyo(2, 11))
  private var dropping = List(new Puyo(3, 0, PuyoColor.random), 
    new Puyo(3, -1, PuyoColor.random))

  def yos = _yos
  def allYos = dropping ::: yos

  def update(delay: Double): Unit = {
    dropping = dropping.map(_.drop)
  }
}