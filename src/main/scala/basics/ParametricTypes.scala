package basics

object ParametricTypes extends App {
  def findAndRemove[A](lst: List[A])(pred: A => Boolean): (List[A], Option[A]) = {
    def helper(lst2: List[A]): (List[A], Option[A]) = lst2 match {
      case Nil => (Nil, None)
      case head :: tl => 
        if (pred(head)) (tl, Some(head)) else {
          val (rest, found) = helper(tl)
          (head :: rest, found)
        }
    }
    helper(lst)
  }

  val nums = List(2, 8, 5, 3, 9, 5)
  println(findAndRemove(nums)(_ == 3))
  println(findAndRemove(nums)(_ == 5))
  println(findAndRemove(nums)(_ == 4))
  println(findAndRemove(nums)(_ > 8))
}