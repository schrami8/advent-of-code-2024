enum class Color(val value: String) {
    BLUE("blue"),
    GREEN("green"),
    RED("red");

    
    companion object {
        fun from(value: String): Color? = Color.entries.firstOrNull { it.value == value}
    }
}

data class Cube(val count: Int, val color: Color)

fun main() {

    fun parse(input: List<String>): List<Pair<String, List<Cube>>> {
        return input.map {
            val (game, sets) = it.split(": ")
            val (_, gameID) = game.split("Game ")

            val cubeSets = sets.split(";").map { cube ->
                cube.split(", ").map {
                    val (count, color) = it.trim().split(" ")
                    Cube(count.toInt(), Color.from(color)!!)
                }
            }

            gameID to cubeSets.flatten()
        }
    }
    
    fun part1(input: List<String>): Int {
        val result = parse(input)
        .filter {
            val blue = it.second.filter { cube -> cube.color == Color.BLUE }.all { it.count <= 14 }
            val green = it.second.filter { cube -> cube.color == Color.GREEN }.all { it.count <= 13 }
            val red = it.second.filter { cube -> cube.color == Color.RED }.all { it.count <= 12 }

            blue && green && red
        }.sumOf {
            it -> it.first.toInt()
        }
        
        return result
    }

    fun part2(input: List<String>): Int {
        val result = parse(input)
        .map {
            val blue = it.second.filter { cube -> cube.color == Color.BLUE }.maxOf { it.count }
            val green = it.second.filter { cube -> cube.color == Color.GREEN }.maxOf { it.count }
            val red = it.second.filter { cube -> cube.color == Color.RED }.maxOf { it.count }
            
            blue * green * red
        }
        .sum()

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test/Day02/Test")
    check(part1(testInput) == 8)

    var input = readInput("test/Day02/Part1")
    part1(input).println()

    check(part2(testInput) == 2286)

    input = readInput("test/Day02/Part2")
    part2(input).println()
}
