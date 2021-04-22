package recursion

object Sorts extends App {
  def quicksort[A](lst: List[A])(lt: (A, A) => Boolean): List[A] = lst match {
    case Nil | _ :: Nil => lst
    case pivot :: rest =>
      val (less, notLess) = rest.partition(a => lt(a, pivot))
      quicksort(less)(lt) ::: (pivot :: quicksort(notLess)(lt))
  }

  val nums = List.fill(10)(util.Random.nextInt(100))
  println(nums)
  println(quicksort(nums)(_ < _))
}