package firstfinal.modul

import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.util.Queue

class SearcherHitler(private val url: String) {
    private val listOfUrls: MutableList<Pair<String, Int>> = mutableListOf(Pair(url, 0))
    private val prefix: String = url.split("/wiki")[0]

    fun search(): Int = runBlocking {
        searchHitler(url)
        var count = 0

        while (listOfUrls.isNotEmpty()) {
            count += 1
            val newUrl = listOfUrls[0]

            println("-> ${newUrl.first}")

            if (searchHitler(newUrl.first)) {
                return@runBlocking newUrl.second
            }

            listOfUrls.addAll(getUrls(newUrl.first).map { Pair(it, count) })
            listOfUrls.removeAt(0)
        }

        return@runBlocking 0
    }

    private fun searchHitler(url: String): Boolean {
        val element = getHtml(url)

        return element.text().contains("Hitler") //TODO()
    }

    private fun getUrls(url: String): List<String> {
        val urls = getHtml(url).select("#bodyContent a[href^=/wiki/]")

        return urls.map { prefix + it.attr("href") }
    }

    private fun getHtml(url: String): org.jsoup.nodes.Element {
        return Jsoup.connect(url).get().body()
    }
}

