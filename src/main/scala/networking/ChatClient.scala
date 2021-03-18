package networking

import akka.actor.Actor
import java.net.Socket
import java.io.BufferedReader
import java.io.PrintStream

class ChatClient(name: String, sock: Socket, in: BufferedReader, out: PrintStream) extends Actor {
  def receive = {
    case Messages.CheckInput =>
      if (in.ready()) {
        val input = in.readLine()
        sender ! Messages.SendToAll(s"$name: $input")
      }
    case Messages.PrintMessage(message) =>
      out.println(message)
    case m => println(s"Unhandled message in ChatClient: $m")
  }
}