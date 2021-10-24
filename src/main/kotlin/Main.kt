fun main() {
    val newMaze = Maze(10, 10)
    newMaze.printMaze()
}

class Maze(private val height: Int, private val width: Int) {
    val map = Array(height) {
        BooleanArray(width) {
            true
        }
    }
    val enter = Location(this, 1, 0).setFalse()
    val exit = Location(this, 8, 9).setFalse()

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
}

class Location(private val maze: Maze, private val x: Int, private val y: Int) {
    fun setTrue() {
        maze.map[y][x] = true
    }

    fun setFalse() {
        maze.map[y][x] = false
    }
}