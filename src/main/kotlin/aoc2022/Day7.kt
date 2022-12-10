package aoc2022

import aoc.LineBasedSolver

class Day7: LineBasedSolver(2022, 7) {

    override fun solvePart1(inputs: List<String>): Any {
        val fs = FSNode("/")
        var currentFSNode = fs
        inputs.forEach {
            if(it.startsWith('$')) {
                val commandParts = it.substring(2).split(' ')
                when(commandParts[0]) {
                    "cd" -> currentFSNode = currentFSNode.goTo(commandParts[1])
                }
            } else {
                val contentParts = it.split(' ')
                if(contentParts[0] != "dir") {
                    currentFSNode.addChildNode(contentParts[1]).setSize(contentParts[0].toInt())
                }
            }
        }

        return fs.getSumOfSizesLessOrEqualThan(100000)
    }

    override fun solvePart2(inputs: List<String>): Any {
        val fs = FSNode("/")
        var currentFSNode = fs
        inputs.forEach {
            if(it.startsWith('$')) {
                val commandParts = it.substring(2).split(' ')
                when(commandParts[0]) {
                    "cd" -> currentFSNode = currentFSNode.goTo(commandParts[1])
                }
            } else {
                val contentParts = it.split(' ')
                if(contentParts[0] != "dir") {
                    currentFSNode.addChildNode(contentParts[1]).setSize(contentParts[0].toInt())
                }
            }
        }

        val rootSize = fs.getSize()
        val freeSpace = 70000000 - rootSize
        val requiredCleanupSize = 30000000 - freeSpace
        return fs.getSmallestWithSize(requiredCleanupSize)?.getSize() ?: 0
    }

    companion object {

        class FSNode(private val name: String, private val parent: FSNode? = null) {

            private val childNodes = mutableMapOf<String, FSNode>()
            private var size: Int = 0

            fun goTo(name: String): FSNode {
                return when(name) {
                    "/" -> parent?.goTo(name)
                    ".." -> parent
                    else -> if(childNodes.containsKey(name)) {
                        childNodes[name]
                    } else {
                        addChildNode(name)
                    }
                } ?: this
            }

            private fun isFile(): Boolean {
                return childNodes.isEmpty()
            }

            fun addChildNode(name: String): FSNode {
                if(childNodes.containsKey(name))
                    error("Already contains child node with name '$name'.")
                val node = FSNode(name, this)
                childNodes[name] = node
                return node
            }

            fun getSize(): Int {
                return size
            }

            fun setSize(size: Int) {
                this.size = size
                parent?.addChildNodeSize(size)
            }

            fun getSumOfSizesLessOrEqualThan(maxSize: Int): Int {
                if(isFile()) {
                    return 0
                }
                val childNodeSizes = childNodes.values.sumOf { it.getSumOfSizesLessOrEqualThan(maxSize) }
                return if(size <= maxSize) {
                    size + childNodeSizes
                } else {
                    childNodeSizes
                }
            }

            fun getSmallestWithSize(minSize: Int, maxSize: Int = Int.MAX_VALUE): FSNode? {
                if(isFile() || size < minSize || size > maxSize) {
                    return null
                }

                var node: FSNode = this
                childNodes.values.forEach {
                    val childNode = it.getSmallestWithSize(minSize, node.size)
                    if(childNode != null) {
                        node = childNode
                    }
                }
                return node
            }

            private fun addChildNodeSize(childNodeSize: Int) {
                size += childNodeSize
                parent?.addChildNodeSize(childNodeSize)
            }

            fun print(indent: Int = 0) {
                print(" ".repeat(indent))
                println("$name ($size)")
                childNodes.values.forEach {
                    it.print(indent + 2)
                }
            }

        }

    }

}

fun main() {
    Day7().verifyAndSolve("""
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent(), 95437, 24933642)
}