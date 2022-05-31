
import kotlin.random.Random

private const val HIGH_BOUND_OF_RANDOM_NUMBERS = 1000000
private const val SMALL_ELEMENTS_NUMBER = 1000
private const val SMALL_MEDIUM_ELEMENT_NUMBER = 10000
private const val BIG_MEDIUM_ELEMENT_NUMBER = 100000
private const val BIG_ELEMENT_NUMBER = 1000000

fun smallList() = MutableList(SMALL_ELEMENTS_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
fun smallMediumList() = MutableList(SMALL_MEDIUM_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
fun bigMediumList() = MutableList(BIG_MEDIUM_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
fun bigList() = MutableList(BIG_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
