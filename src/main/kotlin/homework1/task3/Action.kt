interface Action {
    fun doAction(listOfNumbers: MutableList<Int>)

    fun reversAction(listOfNumbers: MutableList<Int>)
}

class AddToBegin(private val number: Int) : Action {
    private val numForAdd: Int = number

    override fun doAction(listOfNumbers: MutableList<Int>) {
        listOfNumbers.add(0, numForAdd)
    }

    override fun reversAction(listOfNumbers: MutableList<Int>) {
        listOfNumbers.removeFirst()
    }
}

class AddToEnd(private val number: Int) : Action {
    private val numForAdd: Int = number

    override fun doAction(listOfNumbers: MutableList<Int>) {
        listOfNumbers.add(numForAdd)
    }

    override fun reversAction(listOfNumbers: MutableList<Int>) {
        listOfNumbers.removeLast()
    }
}

class Move(private val currentIndex: Int, private val finalIndex: Int) : Action {
    override fun doAction(listOfNumbers: MutableList<Int>) {
        require(currentIndex in listOfNumbers.indices) { "first index out of range" }
        require(finalIndex in listOfNumbers.indices) { "second index out of range" }

        val numForMove = listOfNumbers[currentIndex]

        listOfNumbers.removeAt(currentIndex)
        listOfNumbers.add(finalIndex, numForMove)
    }

    override fun reversAction(listOfNumbers: MutableList<Int>) {
        val numForMove = listOfNumbers[finalIndex]

        listOfNumbers.removeAt(finalIndex)
        listOfNumbers.add(currentIndex, numForMove)
    }
}
