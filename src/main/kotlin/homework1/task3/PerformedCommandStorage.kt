class PerformedCommandStorage(initListOfNumbers: MutableList<Int> = mutableListOf()) {
    private val actions = mutableListOf<Action>()
    private val listOfNumbers = initListOfNumbers
    val numbers: MutableList<Int>
        get() = listOfNumbers

    fun doAction(command: Action) {
        command.doAction(listOfNumbers)

        actions.add(command)
    }

    fun reverseAction() {
        require(actions.isNotEmpty()) { "the stack of completed actions is empty" }

        actions.removeLast().reversAction(listOfNumbers)
    }
}
