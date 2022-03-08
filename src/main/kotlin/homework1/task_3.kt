private const val MINIMUM_LENGTH = 2

interface CommandStorage {
    fun reverseAction()
    fun returnStringOfNumbers(): String
    fun returnListOfNumbers(): List<Int>
}

enum class Action {
    ADD_TO_HEAD, ADD_TO_TAIL, MOVE
}

enum class Command(val stringNameOfCommand: String) {
    ADD_TO_END("TTE"),
    ADD_TO_BEGIN("TTB"),
    MOVE("MOV"),
    PRINT("PRT"),
    REVERSE("REV")
}

class PerformedCommandStorage : CommandStorage {
    private val actions = mutableListOf<Action>()
    private val listOfNumbers = mutableListOf<Int>()
    private val listOfIndexes = mutableListOf<Pair<Int, Int>>()

    fun addToBegin(number: Int) {
        listOfNumbers.add(0, number)
        actions.add(Action.ADD_TO_HEAD)
    }

    fun addToEnd(number: Int) {
        listOfNumbers.add(number)
        actions.add(Action.ADD_TO_TAIL)
    }

    fun move(currentIndex: Int, finalIndex: Int) {
        require(currentIndex in listOfNumbers.indices) { "first index out of range" }
        require(finalIndex in listOfNumbers.indices) { "second index out of range" }

        val numForMove = listOfNumbers[currentIndex]

        listOfNumbers.removeAt(currentIndex)
        listOfNumbers.add(finalIndex, numForMove)
        actions.add(Action.MOVE)
        listOfIndexes.add(currentIndex to finalIndex)
    }

    override fun reverseAction() {
        require(actions.isNotEmpty()) { "the stack of completed actions is empty" }

        when (actions.removeLast()) {
            Action.ADD_TO_HEAD -> {
                listOfNumbers.removeFirst()
            }
            Action.ADD_TO_TAIL -> {
                listOfNumbers.removeLast()
            }
            Action.MOVE -> {
                move(listOfIndexes[listOfIndexes.size - 1].second, listOfIndexes[listOfIndexes.size - 1].first)
                listOfIndexes.removeLast()
                actions.removeLast()
            }
        }
    }

    override fun returnStringOfNumbers(): String {
        return listOfNumbers.joinToString(separator = ", ", prefix = "numbers: ")
    }

    override fun returnListOfNumbers(): List<Int> {
        return listOfNumbers
    }
}

fun getCommand(): List<String> {
    val stringOfCommand = readLine()

    require(stringOfCommand != null) { "input error" }

    return stringOfCommand.split(" ")
}

fun interactions(listOfCommand: List<String>, storage: PerformedCommandStorage): String {
    require(listOfCommand.isNotEmpty()) { "repeat the input, please" }

    when (listOfCommand[0]) {
        Command.ADD_TO_END.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
            storage.addToEnd(listOfCommand[1].toInt())
        }
        Command.ADD_TO_BEGIN.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
            storage.addToBegin(listOfCommand[1].toInt())
        }
        Command.MOVE.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH + 1) { "incomplete input" }
            storage.move(listOfCommand[1].toInt(), listOfCommand[2].toInt())
        }
        Command.PRINT.stringNameOfCommand -> println(storage.returnStringOfNumbers())
        Command.REVERSE.stringNameOfCommand -> storage.reverseAction()
        else -> {
            println("invalid command, repeat the input")
        }
    }
    return storage.returnStringOfNumbers()
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

    while (listOfCommand.isEmpty()) {
        println("repeat the input, please")
        listOfCommand = getCommand()
    }

    while (listOfCommand[0] != "END") {
        try {
            interactions(listOfCommand, storage)
        } catch (e: NumberFormatException) {
            println("invalid arguments, repeat the input")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
        listOfCommand = getCommand()
    }
}
