private const val MINIMUM_LENGTH = 2

enum class Command(val stringNameOfCommand: String) {
    ADD_TO_END("TTE"),
    ADD_TO_BEGIN("TTB"),
    MOVE("MOV"),
    PRINT("PRT"),
    REVERSE("REV")
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
            storage.doAction(AddToEnd(listOfCommand[1].toInt()))
        }
        Command.ADD_TO_BEGIN.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH) { "incomplete input" }
            storage.doAction(AddToBegin(listOfCommand[1].toInt()))
        }
        Command.MOVE.stringNameOfCommand -> {
            require(listOfCommand.size >= MINIMUM_LENGTH + 1) { "incomplete input" }
            storage.doAction(Move(listOfCommand[1].toInt(), listOfCommand[2].toInt()))
        }
        Command.PRINT.stringNameOfCommand -> println(
            storage.numbers.joinToString(separator = ", ", prefix = "numbers: ")
        )
        Command.REVERSE.stringNameOfCommand -> storage.reverseAction()
        else -> {
            println("invalid command, repeat the input")
        }
    }
    return storage.numbers
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
