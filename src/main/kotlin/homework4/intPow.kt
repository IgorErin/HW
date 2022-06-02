import kotlin.math.pow

fun Int.pow(powNumber: Int): Int = this.toDouble().pow(powNumber).toInt()
