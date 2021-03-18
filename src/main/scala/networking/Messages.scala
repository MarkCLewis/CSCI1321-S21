package networking

import java.net.Socket
import java.io.BufferedReader
import java.io.PrintStream

object Messages {
  // Messages for the manager
  case class NewChatClient(name: String, sock: Socket, in: BufferedReader, out: PrintStream)
  case object CheckAllInputs
  case class SendToAll(message: String)

  // Messages for the client
  case object CheckInput
  case class PrintMessage(message: String)
}