//"insertToTheBeginning", "insertAtTheEnd", "changeIAndJ"
class PerformedCommandStorage() {
    private val Action = mutableListOf<String>()
    val listOfNumbers = mutableListOf<Int>()
    private val listOfIndex = mutableListOf<List<Int>>()

    fun addToBegin(number: Int) {
        listOfNumbers.add(0, number)
        Action.add("insertToTheBeginning")
    }

    fun addToEnd(number: Int) {
        listOfNumbers.add(number)
        Action.add("insertAtTheEnd")
    }

    fun change(indexI: Int, indexJ: Int) {
        if (indexI >= listOfNumbers.size || indexJ >= listOfNumbers.size) {
            throw TODO()
        }
        val numForMove: Int = listOfNumbers[indexI]
        listOfNumbers.removeAt(indexI)
        listOfNumbers.add(indexJ, numForMove)
        Action.add("movedFromIToJ")
        listOfIndex.add(listOf(indexI, indexJ))
    }

    fun unDone() {
        if (Action.size < 1) {
            throw TODO()
        }
        when(Action[Action.size - 1]){
            "insertToTheBeginning" -> {
                listOfNumbers.removeAt(0)
            }
            "insertAtTheEnd" -> {
                listOfNumbers.removeAt(listOfNumbers.size - 1)
            }
            "movedFromIToJ" -> {
                change(listOfIndex[listOfIndex.size - 1][1], listOfIndex[listOfIndex.size - 1][0])
                listOfIndex.removeAt(listOfIndex.size - 1)
                Action.removeAt(Action.size - 1)
            }
        }
        Action.removeAt(Action.size - 1)
    }
}

fun main() {
    val myList = PerformedCommandStorage()
    myList.addToEnd(1)
    println(myList.listOfNumbers)
    myList.addToEnd(2)
    println(myList.listOfNumbers)
    myList.addToEnd(3)
    println(myList.listOfNumbers)

    myList.addToBegin(0)
    println(myList.listOfNumbers)
    myList.addToBegin(-1)
    println(myList.listOfNumbers)
    myList.addToBegin(-2)
    println(myList.listOfNumbers)
    println("change:")
    myList.change(2, 4)
    println(myList.listOfNumbers)
    myList.unDone()
    println("unchange:")
    println(myList.listOfNumbers)
    myList.unDone()
    println(myList.listOfNumbers)
    myList.unDone()
    println(myList.listOfNumbers)
    myList.unDone()
    println(myList.listOfNumbers)
    myList.unDone()
    println(myList.listOfNumbers)
    myList.unDone()
    println(myList.listOfNumbers)
    myList.unDone()
    print(myList.listOfNumbers)
}