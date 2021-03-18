package networking

import akka.actor.ActorSystem
import akka.actor.Props
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import scala.concurrent.duration._
import scala.concurrent.Future

object ActorChat extends App {
  val system = ActorSystem("ActorChat")
  implicit val ec = system.dispatcher

  val manager = system.actorOf(Props(new ChatManager))
  system.scheduler.scheduleAtFixedRate(0.1 seconds, 0.1 seconds, manager, Messages.CheckAllInputs)

  val ss = new ServerSocket(4040)

  while (true) {
    val sock = ss.accept()
    val in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
    val out = new PrintStream(sock.getOutputStream())
    out.println("Welcome to our chat.")
    out.println("What is your name?")
    Future {
      val name = in.readLine()
      manager ! Messages.NewChatClient(name, sock, in, out)
    }
  }
}