package puyo

class Board {
  private var _yos: List[Yo] = List(new Puyo(5, 5, PuyoColor.Green))

  def yos = _yos
}