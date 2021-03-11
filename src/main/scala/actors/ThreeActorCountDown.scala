package actors

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

object ThreeActorCountDown extends App {
  class CountActor extends Actor {
    def receive = {
      case StartCounting(n, next, nextNext) =>
        println(n)
        next ! CountDown(n-1, nextNext)
      case CountDown(n, next) =>
        if (n < 0) {
          system.terminate()
        } else {
          println(n)
          next ! CountDown(n-1, sender)
        }
      case m => println("Oops: " + m)
    }
  }

  val system = ActorSystem("ThreeCount")
  val a1 = system.actorOf(Props(new CountActor), "First")
  val a2 = system.actorOf(Props(new CountActor), "Second")
  val a3 = system.actorOf(Props(new CountActor), "Third")
  a1 ! StartCounting(10, a2, a3)

  case class StartCounting(n: Int, next: ActorRef, nextNext: ActorRef)
  case class CountDown(n: Int, next: ActorRef)
}