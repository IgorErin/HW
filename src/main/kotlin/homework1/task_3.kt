private const val MINIMUM_LENGTH = 2

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

fun addToBegin(
    number: Int,
    listOfNumbers: MutableList<Int>,
    actions: MutableList<Action>
) {
    listOfNumbers.add(0, number)
    actions.add(Action.ADD_TO_HEAD)
}

fun addToEnd(
    number: Int,
    listOfNumbers: MutableList<Int>,
    actions: MutableList<Action>
) {
    listOfNumbers.add(number)
    actions.add(Action.ADD_TO_TAIL)
}

fun move(
    currentIndex: Int,
    finalIndex: Int,
    listOfNumbers: MutableList<Int>,
    actions: MutableList<Action>,
    listOfIndexes: MutableList<Pair<Int, Int>>
) {
    require(currentIndex in listOfNumbers.indices) { "first index out of range" }
    require(finalIndex in listOfNumbers.indices) { "second index out of range" }

    val numForMove = listOfNumbers[currentIndex]

    listOfNumbers.removeAt(currentIndex)
    listOfNumbers.add(finalIndex, numForMove)
    actions.add(Action.MOVE)
    listOfIndexes.add(currentIndex to finalIndex)
}

class PerformedCommandStorage {
    private val actions = mutableListOf<Action>()
    private val listOfNumbers = mutableListOf<Int>()
    private val listOfIndexes = mutableListOf<Pair<Int, Int>>()

    fun doAction(command: Action, firstArgument: Int, secondArgument: Int) {
        when (command) {
            Action.ADD_TO_TAIL -> {
                addToEnd(firstArgument, listOfNumbers, actions)
            }
            Action.ADD_TO_HEAD -> {
                addToBegin(firstArgument, listOfNumbers, actions)
            }
            Action.MOVE -> {
                move(firstArgument, secondArgument, listOfNumbers, actions, listOfIndexes)
            }
        }
    }

    fun reverseAction() {
        require(actions.isNotEmpty()) { "the stack of completed actions is empty" }

        when (actions.removeLast()) {
            Action.ADD_TO_HEAD -> {
                listOfNumbers.removeFirst()
            }
            Action.ADD_TO_TAIL -> {
                listOfNumbers.removeLast()
            }
            Action.MOVE -> {
                move(
                    listOfIndexes[listOfIndexes.size - 1].second,
                    listOfIndexes[listOfIndexes.size - 1].first,
                    listOfNumbers,
                    actions,
                    listOfIndexes
                )
                listOfIndexes.removeLast()
                listOfIndexes.removeLast()
                actions.removeLast()
            }
        }
    }

    fun returnListOfNumbers(): List<Int> {
        return listOfNumbers
    }
}

fun getCommand(): List<String> {
    val stringOfCommand = readLine()

    require(stringOfCommand != null) { "input error" }

    return stringOfCommand.split(" ")
}

fun interactions(listOfCommand: List<String>, storage: PerformedCommandStorage): List<Int> {
    require(listOfCommand.isNotEmpty()) { "repeat the input, please" }

    when (listOfCommand[0]) {
        Command.ADD_TO_END.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
            storage.doAction(Action.ADD_TO_TAIL, listOfCommand[1].toInt(), 0)
        }
        Command.ADD_TO_BEGIN.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
            storage.doAction(Action.ADD_TO_HEAD, listOfCommand[1].toInt(), 0)
        }
        Command.MOVE.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH + 1) { "incomplete input" }
            storage.doAction(Action.MOVE, listOfCommand[1].toInt(), listOfCommand[2].toInt())
        }
        Command.PRINT.stringNameOfCommand -> println(
            storage.returnListOfNumbers().joinToString(separator = ", ", prefix = "numbers: ")
        )
        Command.REVERSE.stringNameOfCommand -> storage.reverseAction()
        else -> {
            println("invalid command, repeat the input")
        }
    }
    return storage.returnListOfNumbers()
}

fun main() {
    val storage = PerformedCommandStorage()

    println(
        """
    Enter a number and one of the following commands separated by a space
    TTE' to add to the end, 'TTB' to add to the beginning
    Enter command 'MOV' and two indexes separated by a space
    to move the item from the first to the second position respectively
    Enter 'PRT' to print the list of numbers
    Enter 'REV' to go back a step, 'END' to end the program
        """.trimIndent()
    )

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
