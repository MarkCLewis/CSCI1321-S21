package puyo

import java.net.ServerSocket
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import java.net.Socket
import scala.concurrent.Future

case class ConnectedClient(sock: Socket, ois: ObjectInputStream, oos: ObjectOutputStream, board: Board)

object Server extends App {
  private var clients = List[ConnectedClient]()
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  val ss = new ServerSocket(8080)
  Future {
    while (true) {
      val sock = ss.accept()
      val oos = new ObjectOutputStream(sock.getOutputStream())
      val ois = new ObjectInputStream(sock.getInputStream())
      val board = new Board()
      clients ::= ConnectedClient(sock, ois, oos, board)
    }
  }

  var lastTime = -1L
  while(true) { 
    val time = System.nanoTime()
    if (lastTime >= 0) {
      for (ConnectedClient(sock, ois, oos, board) <- clients) {
        if (ois.available() > 0) {
          val pressRelease = ois.readInt()
          val key = ois.readInt()
          println(pressRelease, key)
          if (pressRelease == ControlKeys.Pressed) {
            key match {
              case ControlKeys.Left => board.leftPressed()
              case ControlKeys.Right => board.rightPressed()
              case ControlKeys.Up => board.upPressed()
              case ControlKeys.Down => board.downPressed()
              case _ =>
            }
          } else {
            key match {
              case ControlKeys.Left => board.leftReleased()
              case ControlKeys.Right => board.rightReleased()
              case ControlKeys.Up => board.upReleased()
              case ControlKeys.Down => board.downReleased()
              case _ =>
            }
          }
        }
        val delay = (time - lastTime) / 1e9
        board.update(delay)
        oos.writeObject(board.makePassable())
      }
    }
    lastTime = time
  }
}