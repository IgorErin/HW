package firstfinal.modul

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.lang.Integer.min

class HitlerSearcher(private val url: String) {
    private val listOfUrls: MutableList<Triple<String, Int, MutableList<String>>> =
        mutableListOf(Triple(url, 1, mutableListOf()))
    private val prefix: String = url.split("/wiki")[0]

    fun search(dept: Int, coroutineCount: Int): Int? = runBlocking {
        require(dept > 0) {
            "hitler search error, dept = $dept, but must be grater than zero"
        }
        require(coroutineCount > 0) {
            "hitler search error, coroutine number = $coroutineCount, but must be grater than zero"
        }

        println("starting Hitler search...")

        var coroutineList: List<Deferred<Unit>>
        var newUrl: Triple<String, Int, MutableList<String>>

        while (listOfUrls.isNotEmpty()) {
            val indicesBound = min(coroutineCount, listOfUrls.size)
            coroutineList = List(indicesBound) { async { bfsStep(listOfUrls[it]) } }
            coroutineList.forEach { it.await() }

            repeat(indicesBound) {
                newUrl = listOfUrls[0]

                if (newUrl.second > dept) {
                    return@runBlocking null
                }
                if (searchHitler(newUrl.first)) {
                    println("result: ${newUrl.third.arrowPrint()} ${newUrl.first}")
                    return@runBlocking newUrl.second
                }

                println("checking article -> ${newUrl.first}")
                listOfUrls.removeAt(0)
            }
        }

        return@runBlocking null
    }

    private fun bfsStep(newUrl: Triple<String, Int, MutableList<String>>) {
        val newList = newUrl.third.deepCopy()
        newList.add(newUrl.first)

        listOfUrls.addAll(getUrls(newUrl.first).map { Triple(it, newUrl.second + 1, newList) })
    }

    private fun searchHitler(url: String): Boolean {
        val element = getHtml(url)

        return element.text().contains("Hitler")
    }

    private fun getUrls(url: String): List<String> {
        val urls = getHtml(url).select("#bodyContent a[href^=/wiki/]")

        return urls.map { prefix + it.attr("href") }
    }

    private fun getHtml(url: String): org.jsoup.nodes.Element {
        return Jsoup.connect(url).get().body()
    }

    private fun MutableList<String>.deepCopy(): MutableList<String> = MutableList(this.size) { this[it] }

    private fun MutableList<String>.arrowPrint(): String {
        var outputString = ""
        this.forEach { outputString += "$it ->" }

        return outputString
    }
}
