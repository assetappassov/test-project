package kz.projects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

class ConcurrentTest {

    @Test
    fun `run a lot of threads`() {
        val c = AtomicLong()

        for (i in 1..1_000_000L)
            thread(start = true) {
                c.addAndGet(i)
            }

        println(c.get())

        assertEquals("a", "a")
    }
}
