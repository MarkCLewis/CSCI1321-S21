package recursion

object Graph extends App {
  val graph = Array(
    Array(0, 1, 1, 1, 0),
    Array(0, 1, 0, 0, 0),
    Array(1, 0, 0, 0, 0),
    Array(0, 1, 1, 0, 1),
    Array(0, 0, 0, 0, 0),
  )

  def reachable(node1: Int, node2: Int, connect: Array[Array[Int]], visited: Set[Int]): Boolean = {
    if (node1 == node2) true
    else {
      var ret = false
      val newVisited = visited + node1
      for (i <- 0 until connect.length; if connect(node1)(i) != 0 && ! visited(i)) {
        ret ||= reachable(i, node2, connect, newVisited)
      }
      ret
    }
  }

  def shortestPath(node1: Int, node2: Int, connect:Array[Array[Int]], visited: Set[Int]): Int = {
    if (node1 == node2) 0
    else {
      var ret = 1000000000
      val newVisited = visited + node1
      for (i <- 0 until connect.length; if connect(node1)(i) != 0 && ! visited(i)) {
        ret = ret min shortestPath(i, node2, connect, newVisited)
      }
      ret+1
    }
  }

  println(reachable(0, 4, graph, Set.empty))
  println(reachable(1, 3, graph, Set.empty))
  println(shortestPath(0, 4, graph, Set.empty))
  println(shortestPath(2, 4, graph, Set.empty))
  println(shortestPath(1, 4, graph, Set.empty))
}