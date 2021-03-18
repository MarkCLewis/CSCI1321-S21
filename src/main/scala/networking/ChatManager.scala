package networking

import akka.actor.Actor
import akka.actor.Props

class ChatManager extends Actor {
  def receive = {
    case Messages.NewChatClient(name, sock, in, out) =>
      context.actorOf(Props(new ChatClient(name, sock, in, out)), name)
    case Messages.CheckAllInputs =>
      for (child <- context.children) child ! Messages.CheckInput
    case Messages.SendToAll(message) =>
      for (child <- context.children) child ! Messages.PrintMessage(message)
    case m => println(s"Unhandled message in ChatManager: $m")
  }
}