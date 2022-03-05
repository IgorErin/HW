interface CommandStorage {
    fun reversAction()
    fun printListOfNumbers()
    fun returnListOfNumbers(): List<Int>
}

class PerformedCommandStorage : CommandStorage {
    private val action = mutableListOf<String>()
    private val listOfNumbers = mutableListOf<Int>()
    private val listOfIndexes = mutableListOf<List<Int>>()

    fun addToBegin(number: Int) {
        listOfNumbers.add(0, number)
        action.add("addToHead")
    }

    fun addToEnd(number: Int) {
        listOfNumbers.add(number)
        action.add("addToTail")
    }

    fun move(indexI: Int, indexJ: Int) {
        require(indexI >= 0 && indexI < listOfNumbers.size) { "first index out of range" }
        require(indexJ >= 0 && indexJ < listOfNumbers.size) { "second index out of range" }

        val numForMove: Int = listOfNumbers[indexI]

        listOfNumbers.removeAt(indexI)
        listOfNumbers.add(indexJ, numForMove)
        action.add("moveFromIToJ")
        listOfIndexes.add(listOf(indexI, indexJ))
    }

    override fun reversAction() {
        require(action.size >= 1) { "the stack of completed actions is empty" }

        when (action.removeAt(action.size - 1)) {
            "addToHead" -> {
                listOfNumbers.removeAt(0)
            }
            "addToTail" -> {
                listOfNumbers.removeAt(listOfNumbers.size - 1)
            }
            "moveFromIToJ" -> {
                move(listOfIndexes[listOfIndexes.size - 1][1], listOfIndexes[listOfIndexes.size - 1][0])
                listOfIndexes.removeAt(listOfIndexes.size - 1)
                action.removeAt(action.size - 1)
            }
        }
    }

    override fun printListOfNumbers() {
        println(listOfNumbers.joinToString(", ", "numbers: "))
    }

    override fun returnListOfNumbers(): List<Int> {
        return listOfNumbers
    }
}

fun getCommand(): List<String> {
    val stringOfCommand = readLine()

    require(stringOfCommand != null) { "input error" }

    return stringOfCommand.split(Regex("\\s+"))
}

fun interaction(listOfCommand: List<String>, storage: PerformedCommandStorage): List<Int> {
    val minimumLength = 2

    when (listOfCommand[0]) {
        "TTE" -> {
            require(listOfCommand.size >= minimumLength) { "incomplete input" }
            storage.addToEnd(listOfCommand[1].toInt())
        }
        "TTB" -> {
            require(listOfCommand.size >= minimumLength) { "incomplete input" }
            storage.addToBegin(listOfCommand[1].toInt())
        }
        "MOV" -> {
            require(listOfCommand.size >= minimumLength + 1) { "incomplete input" }
            storage.move(listOfCommand[1].toInt(), listOfCommand[2].toInt())
        }
        "PRT" -> storage.printListOfNumbers()
        "REV" -> storage.reversAction()
        else -> {
            println("invalid command, repeat the input")
        }
    }
    return storage.returnListOfNumbers()
}

fun main() {
    val storage = PerformedCommandStorage()

    println("Enter a number and one of the following commands separated by a space")
    println("'TTE' to add to the end")
    println("'TTB' to add to the beginning")
    println("Enter command 'MOV' and two indexes separated by a space")
    println("to move the item from the first to the second position respectively")
    println("Enter 'PRT' to print the list of numbers")
    println("Enter 'REV' to go back a step")
    println("'END' to end the program")

    var listOfCommand = getCommand()

    while (listOfCommand[0] != "END") {
        try {
            interaction(listOfCommand, storage)
        } catch (e: NumberFormatException) {
            println("invalid arguments, repeat the input")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
        listOfCommand = getCommand()
    }
}
