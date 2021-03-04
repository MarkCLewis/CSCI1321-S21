package adt

import collection.mutable

object BreadthFirstSearch extends App {
  val maze = Array(
    Array(0,1,0,0,0,0,0,0,0,0),
    Array(0,1,1,1,1,0,1,1,1,1),
    Array(0,1,0,0,0,0,0,0,0,0),
    Array(0,1,1,1,0,1,1,1,1,0),
    Array(0,0,0,0,0,0,0,0,1,0),
    Array(0,1,1,0,1,1,1,1,1,0),
    Array(0,1,0,0,1,0,0,0,1,0),
    Array(0,1,0,1,1,0,1,0,1,0),
    Array(0,0,0,0,0,0,1,0,0,0),
  )

  val offsets = Array((1, 0), (-1, 0), (0, 1),  (0, -1))

  def shortestPath(sx: Int, sy: Int, ex: Int, ey: Int): Int = {
    val queue = new ArrayQueue[(Int, Int, Int)]()
    val visited = mutable.Set[(Int, Int)]()
    queue.enqueue((sx, sy, 0))
    visited += sx -> sy
    while (!queue.isEmpty) {
      val (x, y, steps) = queue.dequeue()
      for ((ox, oy) <- offsets) {
        val nx = x + ox
        val ny = y + oy
        if (nx == ex && ny == ey) return steps + 1
        if (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze(nx).length 
            && maze(nx)(ny) == 0 && !visited(nx -> ny)) {
          queue.enqueue(nx, ny, steps + 1)
          visited += nx -> ny
        }
      }
    }
    1000000000
  }

  println(shortestPath(0, 0, 9, 9))
}

//      u
//     lEr
//      d
//
//
//                P