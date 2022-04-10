package me.thkim7527.maze

import java.io.File

class Point(val x: Int, val y: Int)

class Maze(private val width: Int, private val height: Int) {
    private lateinit var maze: Array<Array<Boolean>>

    init {
        if (width % 2 == 0 || height % 2 == 0) {
            throw IllegalArgumentException("Width and height must be odd number")
        } else {
            generateMaze()
        }
    }

    private fun generateMaze() {
        maze = Array(height) {
            Array(width) { true }
        }

        openWay(Point(1, 1))
        maze[0][1] = false
        maze[height - 1][width - 2] = false
    }

    private fun openWay(current: Point) {
        maze[current.y][current.x] = false
        listOf(
            Point(current.x + 2, current.y),
            Point(current.x - 2, current.y),
            Point(current.x, current.y + 2),
            Point(current.x, current.y - 2)
        ).shuffled().forEach { next ->
            if (next.x in 1 until width && next.y in 1 until height && maze[next.y][next.x]) {
                maze[(current.y + next.y) / 2][(current.x + next.x) / 2] = false
                openWay(next)
            }
        }
    }

    fun printMaze() {
        maze.forEach { row ->
            row.forEach { column ->
                if (column) {
                    print("⬛")
                } else {
                    print("⬜")
                }
            }
            print("\n")
        }
    }

    fun saveMaze() {
        val file = File("./out.txt")

        file.printWriter().use {
            maze.forEach { row ->
                row.forEach { column ->
                    if (column) {
                        it.print("⬛")
                    } else {
                        it.print("⬜")
                    }
                }
                it.print("\n")
            }
        }
    }
}
