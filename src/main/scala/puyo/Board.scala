package puyo

import collection.mutable

class Board {
  val width = 6
  val height = 12
  private var leftHeld = false
  private var rightHeld = false
  private var upHeld = false
  private var downHeld = false

  // These variables help with game play when the user presses and releases a button during the delay period.
  private var leftWasPressed = false
  private var rightWasPressed = false
  private var upWasPressed = false
  private var downWasPressed = false

  private var _yos: List[Yo] = List(new Noyo(2, 11))
  private var dropping = List(new Puyo(2, 0, PuyoColor.random), 
    new Puyo(2, -1, PuyoColor.random))
  private var _next = List(new Puyo(2, 0, PuyoColor.random), 
    new Puyo(2, -1, PuyoColor.random))
  private var dropDelay = 0.0
  val dropInterval = 1.0
  private var moveDelay = 0.0
  val moveInterval = 0.2
  private var fallDelay = 0.0
  val fallInterval = 0.1
  private var mode = RunMode.Dropping
  private val offsets = Array((1, 0), (-1, 0), (0, 1), (0, -1))

  def yos = _yos
  def allYos = dropping ::: yos
  def next = _next

  def update(delay: Double): Unit = {
    mode match {
      case RunMode.Dropping =>
        dropDelay += delay
        moveDelay += delay
        if (dropDelay >= dropInterval) {
          tryDrop()
          dropDelay = 0.0
        }
        if ((leftHeld || leftWasPressed) && moveDelay >= moveInterval) {
          val newDropping = dropping.map(_.move(-1, 0, isClear))
          if ((dropping, newDropping).zipped.forall(_ != _)) dropping = newDropping
          leftWasPressed = false
          moveDelay = 0.0
        }
        if ((rightHeld || rightWasPressed) && moveDelay >= moveInterval) {
          val newDropping = dropping.map(_.move(1, 0, isClear))
          if ((dropping, newDropping).zipped.forall(_ != _)) dropping = newDropping
          rightWasPressed = false
          moveDelay = 0.0
        }
        if ((downHeld || downWasPressed) && moveDelay >= moveInterval) {
          tryDrop()
          downWasPressed = false
          moveDelay = 0.0
        }
        if ((upHeld || upWasPressed) && moveDelay >= moveInterval) {
          if (dropping(0).x == dropping(1).x) {
            val sdrop = dropping.sortBy(_.y)
            if (isClear(sdrop(0).x + 1, sdrop(1).y)) {
              dropping = List(sdrop(1).move(1, 0, isClear), sdrop(0).move(0, 1, isClear))
            }
          } else {
            val sdrop = dropping.sortBy(_.x)
            dropping = List(sdrop(0), sdrop(1).move(-1, -1, isClear))
          }
          upWasPressed = false
          moveDelay = 0.0
        }
      case RunMode.Falling =>
        fallDelay += delay
        if (fallDelay >= fallInterval) {
          if (!makeFall()) mode = RunMode.CheckRemove
          fallDelay = 0.0
        }
      case RunMode.CheckRemove =>
        mode = (if (checkRemove()) RunMode.Falling else RunMode.Dropping)
    }
  }

  def tryDrop(): Unit = {
    val dropped = dropping.map(_.move(0, 1, isClear))
    if ((dropping, dropped).zipped.exists(_ == _)) {
      _yos ++= dropping.filter(_.y >= 0)
      dropping = next
      _next = List(new Puyo(2, 0, PuyoColor.random), new Puyo(2, -1, PuyoColor.random))
      mode = RunMode.Falling
    } else dropping = dropped
  }

  def makeFall(): Boolean = {
    var somethingFell = false
    // This code isn't efficient. A grid would be faster, but this is sufficient for our needs.
    var oldYos = _yos.sortBy(_.y).reverse
    _yos = Nil
    for (yo <- oldYos) {
      _yos ::= (if (isClear(yo.x, yo.y + 1)) {
        somethingFell = true
        yo.move(0, 1, (_, _) => true) 
      } else yo)
    }
    somethingFell
  }

  def checkRemove(): Boolean = {
    val grid = Array.fill(width, height)(null: Yo)
    for (yo <- _yos) grid(yo.x)(yo.y) = yo
    def recurOnGrid(color: PuyoColor.Value, x: Int, y: Int, visited: mutable.Set[Yo]): Unit = {
      grid(x)(y) match {
        case puyo: Puyo =>
          if (puyo.color == color) {
            visited += puyo
            val rets = for {
              (dx, dy) <- offsets
              nx = x + dx
              ny = y + dy
              if nx >= 0 && nx < width && ny >= 0 && ny < height && !visited(grid(nx)(ny))
            } yield recurOnGrid(color, nx, ny, visited)
          }
        case noyo: Noyo =>
          visited += noyo
        case _ =>
      }
    }

    val removed = mutable.Set[Yo]()
    for (x <- 0 until width; y <- 0 until height; if grid(x)(y) != null && !removed(grid(x)(y))) {
      val removeHere = mutable.Set[Yo]()
      val col = grid(x)(y).color
      recurOnGrid(col, x, y, removeHere)
      if (removeHere.count(_.color == col) >= 4) removed ++= removeHere
    }
    _yos = _yos.filter(yo => !removed(yo))
    removed.nonEmpty
  }

  def isClear(x: Int, y: Int): Boolean = {
    x >= 0 && x < width && y < height && _yos.forall(yo => yo.x != x || yo.y != y)
  }

  def leftPressed() = {
    leftHeld = true
    leftWasPressed = true
  }
  def rightPressed() = {
    rightHeld = true
    rightWasPressed = true
  }
  def upPressed() = {
    upHeld = true
    upWasPressed = true
  }
  def downPressed() = {
    downHeld = true
    downWasPressed = true
  }
  def leftReleased() = leftHeld = false
  def rightReleased() = rightHeld = false
  def upReleased() = upHeld = false
  def downReleased() = downHeld = false
}