import java.util.*

fun main() {
    val newMaze = Maze(29, 29)
    newMaze.genMaze()
    newMaze.printMaze()
}

class Maze(private val height: Int, private val width: Int) {
    val map = Array(height) {
        BooleanArray(width) {
            true
        }
    }
    val enter = Location(this, 1, 0)
    val exit = Location(this, width - 2, height - 1)

    fun printMaze() {
        for(i in 0 until height) {
            for(j in 0 until width) {
                val isTrue = map[i][j]
                if(isTrue) {
                    print("■")
                } else {
                    print("□")
                }
                print(" ")
            }
            println("")
        }
    }

    fun genMaze() {
        for(j in 1..height) {
            for(i in 1..width) {
                if((j % 2 == 0)&&(i % 2 == 0)) {
                    val location = Location(this, i - 1, j - 1)
                    location.setFalse()
                    location.openWall()
                }
            }
        }

        for(i in 2 until height) {
            Location(this, width - 2, i - 1).setFalse()
        }

        for(i in 2 until width) {
            Location(this, i - 1, height - 2).setFalse()
        }

        for(i in 0 until height) {
            Location(this, width - 1, i).setTrue()
        }

        for(i in 0 until width) {
            Location(this, i, height - 1).setTrue()
        }

        enter.setFalse()
        exit.setFalse()
    }
}

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
            val location = Location(maze, x + 1, y).setFalse()//오른쪽 비우기
        } else {
            val location = Location(maze, x, y + 1).setFalse() //아래 비우기
        }
    }
}