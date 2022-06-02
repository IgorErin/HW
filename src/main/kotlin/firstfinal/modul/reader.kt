package firstfinal.modul

import org.jsoup.Jsoup

class Searcher(private val url: String) {
    private val prefix: String = url.split("/wiki")[0]

    fun search() {
        print(getUrls())
    }

    private fun getUrls(): List<String> {
        val urls = getHtml(url).select("#bodyContent a[href^=/wiki/]")

        return urls.map { prefix + it.attr("href") }
    }

    private fun getHtml(url: String): org.jsoup.nodes.Element {
        return Jsoup.connect(url).get().body()
    }
}

