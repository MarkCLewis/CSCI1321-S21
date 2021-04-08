package adt

import org.junit.Test
import org.junit.Before
import org.junit.Assert._

class TestList {
  private var lst: MyBuffer[Int] = null

  @Before def makeBuffer = lst = new DLLBuffer[Int]()

  @Test def emptyOnCreate = {
    assertTrue(lst.length == 0)
  }

  @Test def addGetAFew = {
    lst.insert(5, 0)
    assertEquals(1, lst.length)
    lst.insert(8, 1)
    assertEquals(2, lst.length)
    lst.insert(2, 0)
    assertEquals(3, lst.length)
    assertEquals(2, lst(0))
    assertEquals(5, lst(1))
    assertEquals(8, lst(2))
  }

  @Test def addRemoveAFew = {
    lst.insert(5, 0)
    assertEquals(1, lst.length)
    lst.insert(8, 1)
    assertEquals(2, lst.length)
    lst.insert(2, 0)
    assertEquals(3, lst.length)
    assertEquals(2, lst.remove(0))
    assertEquals(2, lst.length)
    assertEquals(8, lst.remove(1))
    assertEquals(1, lst.length)
    assertEquals(5, lst.remove(0))
    assertEquals(0, lst.length)
  }

}
