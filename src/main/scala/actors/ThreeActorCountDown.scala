package actors

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props

object ThreeActorCountDown extends App {
  class CountActor extends Actor {
    def receive = {
      case m => println("Oops: " + m)
    }
  }

  val system = ActorSystem("ThreeCount")
  val a1 = system.actorOf(Props(new CountActor), "First")
  val a2 = system.actorOf(Props(new CountActor), "Second")
  val a3 = system.actorOf(Props(new CountActor), "Third")
  system.terminate()
}