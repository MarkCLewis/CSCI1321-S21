package mud

import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import scala.concurrent.Future

// I'm writing this because Macs apparently don't have a good telnet anymore.
object Client extends App {
  val host = args(0)
  val port = args(1).toInt
  val sock = new Socket(host, port)
  val in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
  val out = new PrintStream(sock.getOutputStream())

  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
  Future {
    while (true) {
      println(in.readLine())
    }
  }
  while (true) {
    out.println(Console.in.readLine())
  }
}