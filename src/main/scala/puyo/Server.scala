package puyo

import java.net.ServerSocket
import java.io.ObjectOutputStream

object Server extends App {
  val board = new Board

  val ss = new ServerSocket(8080)
  val sock = ss.accept()
  val oos = new ObjectOutputStream(sock.getOutputStream())

  var lastTime = -1L
  while(true) { 
    val time = System.nanoTime()
    if (lastTime >= 0) {
      val delay = (time - lastTime) / 1e9
      board.update(delay)
      oos.writeObject(board.makePassable())
    }
    lastTime = time
  }
}