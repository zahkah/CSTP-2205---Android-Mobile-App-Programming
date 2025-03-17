fun main() {
    val assessments = mutableMapOf(
        "Labs" to mutableListOf<Pair<String, Pair<Int, Int>>>(),
        "Projects" to mutableListOf<Pair<String, Pair<Int, Int>>>(),
        "Exams" to mutableListOf<Pair<String, Pair<Int, Int>>>(),
        "Assignments" to mutableListOf<Pair<String, Pair<Int, Int>>>(),
        "Presentations" to mutableListOf<Pair<String, Pair<Int, Int>>>()
    )

    while (true) {
        println("\nPlease enter a command: (e - enter grade, p - print report, q - quit)")
        when (readLine()?.trim()?.lowercase()) {
            "e" -> enterGrade(assessments)
            "p" -> printReport(assessments)
            "q" -> {
                println("Exiting program.")
                return
            }
            else -> println("Invalid command. Please try again.")
        }
    }
}

fun enterGrade(assessments: MutableMap<String, MutableList<Pair<String, Pair<Int, Int>>>>) {
    while (true) {
        println("\nPlease select a category to enter grades: (lbs - Labs, pr - Project, ex - Exams, as - Assignments, pt - Presentations, r - return to main menu)")
        when (readLine()?.trim()?.lowercase()) {
            "lbs" -> addAssessment(assessments["Labs"] ?: mutableListOf(), "Lab")
            "pr" -> addAssessment(assessments["Projects"] ?: mutableListOf(), "Project")
            "ex" -> addAssessment(assessments["Exams"] ?: mutableListOf(), "Exam")
            "as" -> addAssessment(assessments["Assignments"] ?: mutableListOf(), "Assignment")
            "pt" -> addAssessment(assessments["Presentations"] ?: mutableListOf(), "Presentation")
            "r" -> return
            else -> println("Invalid category. Please try again.")
        }
    }
}

fun addAssessment(category: MutableList<Pair<String, Pair<Int, Int>>>, type: String) {
    while (true) {
        println("\nPlease enter the assessment name (or type 'done' to stop adding assessments):")
        val name = readNonEmptyText() ?: break

        val earned = readPositiveInt("Please enter the assessment achieved points:")
        val total = readPositiveInt("Please enter the assessment total points (must be greater than zero):", mustBePositive = true)

        category.add(Pair(name, Pair(earned, total)))
        println("$type assessment '$name' added successfully.\n")
    }
}

fun printReport(assessments: Map<String, List<Pair<String, Pair<Int, Int>>>>) {
    val totalMarks = assessments.map { (category, items) ->
        val (earned, total) = printCategory(category, items)
        earned to total
    }

    val overallEarned = totalMarks.sumOf { it.first }
    val overallTotal = totalMarks.sumOf { it.second }
    val coursePercentage = if (overallTotal > 0) (overallEarned * 100) / overallTotal else 0
    val letterGrade = when (coursePercentage) {
        in 90..100 -> "A"
        in 80..89 -> "B+"
        in 70..79 -> "B"
        in 60..69 -> "C"
        in 50..59 -> "D"
        else -> "F"
    }

    println("\n--- Final Report ---")
    println("Current Course Percentage: $coursePercentage%")
    println("Current Letter Grade: $letterGrade\n")
}

fun printCategory(title: String, items: List<Pair<String, Pair<Int, Int>>>): Pair<Int, Int> {
    println("$title:")
    var earnedTotal = 0
    var maxTotal = 0
    for ((name, scores) in items) {
        println("$name ${scores.first}/${scores.second}")
        earnedTotal += scores.first
        maxTotal += scores.second
    }
    val percentage = if (maxTotal > 0) (earnedTotal * 100) / maxTotal else 0
    println("Total: $earnedTotal/$maxTotal Percentage: $percentage%\n")
    return earnedTotal to maxTotal
}

fun readPositiveInt(prompt: String, mustBePositive: Boolean = false): Int {
    while (true) {
        println(prompt)
        val input = readLine()?.trim()
        val value = input?.toIntOrNull()
        if (value != null && (!mustBePositive || value > 0)) {
            return value
        } else {
            println("Invalid input. Please enter a valid integer${if (mustBePositive) " greater than zero" else ""}.")
        }
    }
}

fun readNonEmptyText(): String? {
    while (true) {
        val input = readLine()?.trim()
        if (input.equals("done", ignoreCase = true)) return null
        if (!input.isNullOrEmpty()) return input
        println("Invalid input. Please enter a non-empty text.")
    }
}
