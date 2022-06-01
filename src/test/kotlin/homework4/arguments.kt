package homework4

import bigList
import bigMediumList
import org.junit.jupiter.params.provider.Arguments
import smallList
import smallMediumList

val autoGenSortTestArguments = listOf(
    Arguments.of(smallList(), -1),
    Arguments.of(smallMediumList(), -3),
    Arguments.of(bigMediumList(), -5),
    Arguments.of(bigList(), -100),
    Arguments.of(smallList(), 0),
    Arguments.of(smallMediumList(), 0),
    Arguments.of(bigMediumList(), 0),
    Arguments.of(bigList(), 0),
    Arguments.of(smallList(), 1),
    Arguments.of(smallMediumList(), 1),
    Arguments.of(bigMediumList(), 1),
    Arguments.of(bigList(), 1),
    Arguments.of(smallList(), 5),
    Arguments.of(smallMediumList(), 5),
    Arguments.of(bigMediumList(), 5),
    Arguments.of(bigList(), 5),
    Arguments.of(smallList(), 10),
    Arguments.of(smallMediumList(), 10),
    Arguments.of(bigMediumList(), 10),
    Arguments.of(bigList(), 10),
    Arguments.of(smallList(), 15),
    Arguments.of(smallMediumList(), 15),
    Arguments.of(bigMediumList(), 15),
    Arguments.of(bigList(), 15),
)
