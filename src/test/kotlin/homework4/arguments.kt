package homework4

import bigList
import bigMediumList
import org.junit.jupiter.params.provider.Arguments
import smallList
import smallMediumList

private fun testList(index: Int): List<Int> = when {
    index % 4 == 0 -> smallList()
    index % 4 == 1 -> smallMediumList()
    index % 4 == 2 -> bigMediumList()
    else -> bigList()
}

fun autoGenSortTestArguments(): List<Arguments> =  List(12) { Arguments.of(testList(it), it - 2) }
