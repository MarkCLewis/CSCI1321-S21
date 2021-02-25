package puyo

class Board {
  val width = 6
  val height = 12
  private var leftHeld = false
  private var rightHeld = false
  private var _yos: List[Yo] = List(new Noyo(2, 11))
  private var dropping = List(new Puyo(3, 0, PuyoColor.random), 
    new Puyo(3, -1, PuyoColor.random))

  def yos = _yos
  def allYos = dropping ::: yos

  def update(delay: Double): Unit = {
    // dropping = dropping.map(_.move(0, 1))
    if (leftHeld) dropping = dropping.map(_.move(-1, 0))
    if (rightHeld) dropping = dropping.map(_.move(1, 0))
  }

  def leftPressed() = leftHeld = true
  def rightPressed() = rightHeld = true
  def leftReleased() = leftHeld = false
  def rightReleased() = rightHeld = false
}