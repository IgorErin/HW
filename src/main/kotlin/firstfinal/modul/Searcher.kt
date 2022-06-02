package firstfinal.modul

import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class HitlerSearcher(private val url: String) {
    private val listOfUrls: MutableList<Pair<String, Int>> = mutableListOf(Pair(url, 1))
    private val prefix: String = url.split("/wiki")[0]

    val coroutineList = listOf<Job>()

    fun search(dept: Int, coroutineCount: Int): Int? = runBlocking {
        var count = 0
        val coroutineList = listOf<Job>()

        for (i in 0 until coroutineCount) {
            val coroutine = async {

            }
        }

        while (listOfUrls.isNotEmpty() || coroutineList.isNotEmpty()) {
            count += 1
            val newUrl = listOfUrls[0]

            println("article $count -> ${newUrl.first}")

            if (searchHitler(newUrl.first)) {
                return@runBlocking newUrl.second
            }

            if (newUrl.second > dept) {
                return@runBlocking null
            }

            listOfUrls.addAll(getUrls(newUrl.first).map { Pair(it, newUrl.second + 1) })
            listOfUrls.removeAt(0)
        }

        return@runBlocking null
    }

    /*private fun asyncJob(url: String) {

        async {

        }
    }*/

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
