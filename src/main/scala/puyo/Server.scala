package puyo

import java.net.ServerSocket
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import java.net.Socket
import scala.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue

case class ConnectedClient(sock: Socket, ois: ObjectInputStream, oos: ObjectOutputStream, board: Board)

object Server extends App {
  private val clientQueue = new LinkedBlockingQueue[ConnectedClient]()
  private var clients = List[ConnectedClient]()
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  val ss = new ServerSocket(8080)
  Future {
    while (true) {
      val sock = ss.accept()
      val oos = new ObjectOutputStream(sock.getOutputStream())
      val ois = new ObjectInputStream(sock.getInputStream())
      val board = new Board()
      clientQueue.put(ConnectedClient(sock, ois, oos, board))
    }
  }

  val sendInterval = 0.03
  private var sendDelay = 0.0
  private var lastTime = -1L
  while(true) {
    while (!clientQueue.isEmpty()) {
      val cc = clientQueue.take()
      clients ::= cc
    }
    val time = System.nanoTime()
    if (lastTime >= 0) {
      val delay = (time - lastTime) / 1e9
      sendDelay += delay
      val sendBoards = sendDelay > sendInterval
      if (sendBoards) sendDelay = 0.0
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
        board.update(delay)
        if (sendBoards) oos.writeObject(board.makePassable())
      }
    }
    lastTime = time
  }
}