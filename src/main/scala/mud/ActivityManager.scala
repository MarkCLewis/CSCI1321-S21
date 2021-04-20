package mud

import akka.actor.Actor
import adt.SortedArrayPQ0
import akka.actor.ActorRef

class ActivityManager extends Actor {
  import ActivityManager._

  val pq = new SortedArrayPQ0[Activity](_.when < _.when)
  var time = 0
  def receive = {
    case CheckActivities =>
      time += 1
      // while first item should happen
      //  dequeue and send message
    case ScheduleActivity(delay, whom, msg) =>
      // add new activity to the PQ at time + delay
    case m => println("Unhandled message in ActorManager: "+m)
  }
}

object ActivityManager {
  case object CheckActivities
  case class ScheduleActivity(delay: Int, whom: ActorRef, msg: Any)

  case class Activity(when: Int, whom: ActorRef, msg: Any)
}