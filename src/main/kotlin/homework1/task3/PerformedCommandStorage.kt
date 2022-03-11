class PerformedCommandStorage {
    private val actions = mutableListOf<Action>()
    private val listOfNumbers = mutableListOf<Int>()

    fun doAction(command: Action) {
        command.doAction(listOfNumbers)

        actions.add(command)
    }

    fun reverseAction() {
        require(actions.isNotEmpty()) { "the stack of completed actions is empty" }

        actions.removeLast().reversAction(listOfNumbers)
    }

    fun returnListOfNumbers(): List<Int> {
        return listOfNumbers
    }
}
