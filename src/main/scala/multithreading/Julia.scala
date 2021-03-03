package multithreading

import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Julia(c: Complex) extends Stage {
  title = "Julia: " + c
  scene = new Scene(Mandelbrot.imageSize, Mandelbrot.imageSize) {
    val image = new WritableImage(Mandelbrot.imageSize, Mandelbrot.imageSize)
    content = new ImageView(image)
    val start = System.nanoTime()
    fillImage(image, -1.0, 1.0, -1.0, 1.0)
    println((System.nanoTime() - start)*1e-9)
  }

  def fillImage(image: WritableImage, rmin: Double, rmax: Double, imin: Double, imax: Double): Unit = {
    val writer = image.pixelWriter
    for (x <- 0 until image.width().toInt; y <- 0 until image.height().toInt) {
      val c = Complex(rmin + x * (rmax-rmin) / image.width(), imin + y * (imax-imin) / image.height())
      val cnt = juliaCount(c)
      val color = countToColor(cnt)
      writer.setColor(x, y, color)
    }
  }

  def countToColor(cnt: Int): Color = {
    if (cnt < Mandelbrot.maxCount) Color(math.log(cnt)/Mandelbrot.logMax, 0.0, 1.0 - math.log(cnt)/Mandelbrot.logMax, 1.0) else Color.Black
  }

  def juliaCount(z0: Complex): Int = {
    var z = z0
    var cnt = 0
    while (cnt < Mandelbrot.maxCount && z.r*z.r + z.i*z.i < 4) {
      z = z*z+c
      cnt += 1
    }
    cnt
  }

}