package me.thkim7527.maze

import kotlin.random.Random
import kotlin.random.nextInt

class Maze(private val width: Int, private val height: Int) {
    private lateinit var board: Array<Array<Block>>

    init {
        if (width % 2 == 0 || height % 2 == 0) {
            throw IllegalArgumentException("Width and height must be odd number")
        } else {
            generateMaze()
        }
    }

    private fun generateMaze() {
        board = Array(height) {h ->
            Array(width) {w ->
                Block(Location(w, h), )
            }
        }

        val pointer = Location(1, 1)
        openWay(pointer, pointer)
    }

    //TODO
    private fun openWay(location: Location, prevLocation: Location) {
        if (location == Location(1, 1)) {
            return
        } else if (
            location.x in 1 until width &&
            location.y in 1 until height &&
            board[location.y][location.x].isBlocked
        ) {
            board[location.y][location.x].isBlocked = false
            board[(prevLocation.y + location.y) / 2][(prevLocation.x + location.x) / 2].isBlocked = false

            this.printMaze()
            print("\n")

            if (location == Location(1, 1)) {
                return
            }

            when(Random.nextInt(0, 4)) {
                0 -> openWay(Location(location.x, location.y - 2), location)
                1 -> openWay(Location(location.x + 2, location.y), location)
                2 -> openWay(Location(location.x, location.y + 2), location)
                3 -> openWay(Location(location.x - 2, location.y), location)
            }
        } else {
            return
        }
    }

    fun printMaze() {
        for (h in 0 until height) {
            for (w in 0 until width) {
                if (board[h][w].isBlocked) {
                    print("■ ")
                } else {
                    print("□ ")
                }
            }
            print("\n")
        }
    }
}

class Location(val x: Int, val y: Int)

class Block(val location: Location, var isBlocked: Boolean = true, var prevLocation: Location? = null)