package me.thkim7527.maze

import java.util.*

class Location(private val maze: Maze, private val x: Int, private val y: Int) {
    fun setTrue() {
        maze.map[y][x] = true
    }

    fun setFalse() {
        maze.map[y][x] = false
    }

    fun openWall() {
        val random = Random().nextInt(2)
        if(random == 0) {
            Location(maze, x + 1, y).setFalse() //오른쪽 비우기
        } else {
            Location(maze, x, y + 1).setFalse() //아래 비우기
        }
    }
}