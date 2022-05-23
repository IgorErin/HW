
fun dimensionCheck(height: Int, width: Int, name: String) {
    require(height > 0) { "height of $name matrix mast be greater then zero, was $height" }
    require(width > 0) { "width of $name matrix mast be greater then zero, was $width" }
}

fun zeroIntMatrix(height: Int, width: Int): IntMatrix {
    dimensionCheck(height, width, "zero")

    val listMatrix = List(height) { List(width) { 0 } }

    return IntMatrix(listMatrix)
}

fun idIntMatrix(height: Int, width: Int): IntMatrix {
    dimensionCheck(height, width, "id")

    val listMatrix = List(height) { columnIt ->
        List(width) { lineIt ->
            if (columnIt == lineIt) 1
            else 0
        }
    }

    return IntMatrix(listMatrix)
}
