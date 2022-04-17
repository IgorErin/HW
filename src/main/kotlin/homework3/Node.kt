
class Node<K, V>(val key: K, var value: V) {
    private var height = 1

    var left: Node<K, V>? = null
    var right: Node<K, V>? = null

    private val balance: Int
        get() = (right?.height ?: 0) - (left?.height ?: 0)

    private fun updateHeight() {
        height = maxOf(this.right?.height ?: 0, this.right?.height ?: 0) + 1
    }

    private fun leftRightRotate(): Node<K, V> {
        val leftNode = left ?: this

        left = leftNode.leftRotate()

        return this.rightRotate()
    }

    private fun rightLeftRotate(): Node<K, V> {
        val rightNode = right ?: this

        right = rightNode.rightRotate()

        return this.leftRotate()
    }

    private fun rightRotate(): Node<K, V> {
        val nodeForRotate = left ?: return this

        left = nodeForRotate.right
        nodeForRotate.right = this

        this.updateHeight()
        nodeForRotate.updateHeight()

        return nodeForRotate
    }

    private fun leftRotate(): Node<K, V> {
        val nodeForRotate = right ?: return this

        right = nodeForRotate.left
        nodeForRotate.left = this

        this.updateHeight()
        nodeForRotate.updateHeight()

        return nodeForRotate
    }

    fun findMin(): Node<K, V>? {
        left ?: return this

        return left?.findMin()
    }

    fun removeMin(): Node<K, V>? {
        left ?: return right

        left = left?.removeMin()

        return this.balance()
    }

    fun balance(): Node<K, V> {
        this.updateHeight()

        val rightNodeBalance = this.right?.balance ?: 0
        val leftNodeBalance = this.right?.balance ?: 0

        return when (this.balance) {
            CRITICAL_UPPER_HEIGHT -> {
                if (rightNodeBalance < 0) {
                    this.rightLeftRotate()
                } else {
                    this.leftRotate()
                }
            }
            CRITICAL_LOWER_HEIGHT -> {
                if (leftNodeBalance > 0) {
                    this.leftRightRotate()
                } else {
                    this.rightRotate()
                }
            }
            else -> this
        }
    }

    override fun toString(): String = "$key : $value [$left, $right]"

    companion object {
        const val CRITICAL_UPPER_HEIGHT = 2
        const val CRITICAL_LOWER_HEIGHT = -2
    }
}
