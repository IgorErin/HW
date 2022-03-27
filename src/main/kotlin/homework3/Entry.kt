
class Entry<K, V>(override var value: V, override val key: K) : MutableMap.MutableEntry<K, V> {
    override fun setValue(newValue: V): V {
        val previousValue = value
        value = newValue

        return previousValue
    }
}
