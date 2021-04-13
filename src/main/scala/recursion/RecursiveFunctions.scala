package recursion

object RecursiveFunctions extends App {
  def factorial(n: Int): Int = if (n < 2) 1 else n*factorial(n-1)

  def fib(n: Long): Long = if (n < 2) 1 else fib(n-1) + fib(n-2)

  println(factorial(15))

  println(fib(5))
  println(fib(6))

  def binPack(objs: List[Double], bins: Array[Double]): Boolean = objs match {
    case Nil => true
    case o :: os =>
      var ret = false
      for (i <- bins.indices; if o <= bins(i)) {
        bins(i) -= o
        ret ||= binPack(os, bins)
        bins(i) += o
      }
      ret
  }

  println(binPack(List(5, 4, 3), Array(7, 5)))
  println(binPack(List(5, 4, 4), Array(7, 5)))
  println(binPack(List(3, 5, 4, 3), Array(6, 6)))

  def knapsack01(items: List[(Double, Double)], maxWeight: Double): Double = items match {
    case Nil => 0.0
    case (value, weight) :: rest =>
      if (weight > maxWeight) knapsack01(rest, maxWeight)
      else knapsack01(rest, maxWeight) max (value + knapsack01(rest, maxWeight - weight))
  }

  println(knapsack01(List((5.0, 5.0), (4.0, 6.0), (3.0, 5.0), (4.0, 2.0)), 10.0))
}