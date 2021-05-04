package basics

object MyRegex extends App {
  def numberedLines(lines: List[String]): Map[Int, String] = {
    val regex = """(\d+)\.(.*)""".r
    (for (regex(num, str) <- lines) yield num.toInt -> str).toMap
  }
}