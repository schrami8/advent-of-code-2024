fun main() {

    fun calibration(row: String): Int {
        val numbers = row.filter { it.isDigit() }
        return numbers.first().digitToInt() * 10 + numbers.last().digitToInt()
    }

    fun part1(input: List<String>): Int {
        val result = input.sumOf { row ->
            calibration(row)
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val words: Map<String, Int> = mapOf(
            "one"   to 1,
            "two"   to 2,
            "three" to 3,
            "four"  to 4,
            "five"  to 5,
            "six"   to 6,
            "seven" to 7,
            "eight" to 8,
            "nine"  to 9,
        )

        val result = input.sumOf { row ->
            val numbers = row.mapIndexedNotNull { i, c ->
                if (c.isDigit()) {
                    c.toString()
                } else {
                    var number: Int? = null
                    for (j in 3..5) {
                        if (i + j > row.length)
                            break

                        val possibleNumber = row.substring(i, i + j)
                        if (words.containsKey(possibleNumber)) {
                            number = words[possibleNumber]
                            break
                        }
                    }
                    number.toString()
                }
            }

            calibration(numbers.joinToString())
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("test/Day01/Part1_test")
    check(part1(testInput) == 142)

    var input = readInput("test/Day01/Part1")
    part1(input).println()

    testInput = readInput("test/Day01/Part2_test")
    check(part2(testInput) == 281)

    input = readInput("test/Day01/Part2")
    part2(input).println()
}
