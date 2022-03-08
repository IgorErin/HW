private const val MINIMUM_LENGTH = 2

enum class Action{
    ADD_TO_HEAD, ADD_TO_TAIL, MOVE
}

enum class Command(val stringNameOfCommand: String) {
    ADD_TO_END("TTE"),
    ADD_TO_BEGIN("TTB"),
    MOVE("MOV"),
    PRINT("PRT"),
    REVERSE("REV")
}

fun addToBegin(number: Int, listOfNumbers: MutableList<Int>, actions: MutableList<Action>) {
    listOfNumbers.add(0, number)
    actions.add(Action.ADD_TO_HEAD)
}

fun addToEnd(number: Int, listOfNumbers: MutableList<Int>, actions: MutableList<Action>) {
    listOfNumbers.add(number)
    actions.add(Action.ADD_TO_TAIL)
}

fun move(currentIndex: Int, finalIndex: Int, listOfNumbers: MutableList<Int>,
         actions: MutableList<Action>, listOfIndexes: MutableList<Pair<Int, Int>>) {
    require(currentIndex in listOfNumbers.indices) { "first index out of range" }
    require(finalIndex in listOfNumbers.indices) { "second index out of range" }

    val numForMove = listOfNumbers[currentIndex]

    listOfNumbers.removeAt(currentIndex)
    listOfNumbers.add(finalIndex, numForMove)
    actions.add(Action.MOVE)
    listOfIndexes.add(currentIndex to finalIndex)
}

class PerformedCommandStorage() {
    private val actions = mutableListOf<Action>()
    private val listOfNumbers = mutableListOf<Int>()
    private val listOfIndexes = mutableListOf<Pair<Int, Int>>()

    fun doAction(listOfCommand: List<String>) {
        require(listOfCommand.isNotEmpty()) { "repeat the input, please" }

        when (listOfCommand[0]) {
            Command.ADD_TO_END.stringNameOfCommand -> {
                require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
                addToEnd(listOfCommand[1].toInt(), listOfNumbers, actions)
            }
            Command.ADD_TO_BEGIN.stringNameOfCommand -> {
                require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
                addToBegin(listOfCommand[1].toInt(), listOfNumbers, actions)
            }
            Command.MOVE.stringNameOfCommand -> {
                require(listOfCommand.size >= MINIMUM_LENGTH + 1) { "incomplete input" }
                move(listOfCommand[1].toInt(), listOfCommand[2].toInt(), listOfNumbers, actions, listOfIndexes)
                println(actions)
            }
            Command.PRINT.stringNameOfCommand -> println(returnListOfNumbers())
            Command.REVERSE.stringNameOfCommand -> reverseAction()
            else -> {
                println("invalid command, repeat the input")
            }
        }
    }

    private fun reverseAction() {
        require(actions.isNotEmpty()) { "the stack of completed actions is empty" }

        when (actions.removeLast()) {
            Action.ADD_TO_HEAD -> {
                listOfNumbers.removeFirst()
            }
            Action.ADD_TO_TAIL -> {
                listOfNumbers.removeLast()
            }
            Action.MOVE -> {
                move(listOfIndexes[listOfIndexes.size - 1].second,
                    listOfIndexes[listOfIndexes.size - 1].first, listOfNumbers, actions, listOfIndexes)
                listOfIndexes.removeLast()
                listOfIndexes.removeLast()
                actions.removeLast()
                println(actions)
            }
        }
    }

    private fun returnListOfNumbers(): List<Int> {
        return listOfNumbers
    }
}

fun getCommand(): List<String> {
    val stringOfCommand = readLine()

    require(stringOfCommand != null) { "input error" }

    return stringOfCommand.split(" ")
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
            storage.doAction(listOfCommand)
        } catch (e: NumberFormatException) {
            println("invalid arguments, repeat the input")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
        listOfCommand = getCommand()
    }
}
