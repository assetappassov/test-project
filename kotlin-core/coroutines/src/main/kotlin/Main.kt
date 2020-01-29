import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong

//fun main() {
//    val c = AtomicLong()
//
//    for (i in 1..1_000_000L)
//        GlobalScope.launch {
//            c.addAndGet(i)
//        }
//
//    println(c.get())
//}

fun main() {
    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            n
        }
    }
    runBlocking {
        val sum = deferred.sumBy { it.await().toInt() }
        println("Sum: $sum")
    }
}