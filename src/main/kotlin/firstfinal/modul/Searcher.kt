package firstfinal.modul

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.lang.Integer.min

class HitlerSearcher(private val url: String) {
    private val listOfUrls: MutableList<Pair<String, Int>> = mutableListOf(Pair(url, 1), Pair(url, 1))
    private val prefix: String = url.split("/wiki")[0]

    fun search(dept: Int, coroutineCount: Int): Int? = runBlocking {
        var coroutineList: List<Deferred<Unit>>
        var newUrl: Pair<String, Int>


        while (listOfUrls.isNotEmpty()) {
            val indicesBound = min(coroutineCount, listOfUrls.size)
            coroutineList = List(indicesBound) { async { bfsStep(listOfUrls[it]) } }
            coroutineList.forEach { it.await() }

            for (ind in 0 until indicesBound) {
                newUrl = listOfUrls[0]

                if (searchHitler(newUrl.first)) {
                    return@runBlocking newUrl.second
                }

                if (newUrl.second > dept) {
                    return@runBlocking null
                }
                println("article > ${newUrl.first}")
                listOfUrls.removeAt(0)
            }
        }

        return@runBlocking null
    }

    private fun bfsStep(newUrl: Pair<String, Int>) {
        listOfUrls.addAll(getUrls(newUrl.first).map { Pair(it, newUrl.second + 1) })
        print("lol")
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
}
