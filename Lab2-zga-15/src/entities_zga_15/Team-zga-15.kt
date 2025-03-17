package entities_zga_15

class Team_zga_15 {
    private val players = mutableListOf<HockeyPlayer_zga_15>()
    private val coaches = mutableListOf<Coach_zga_15>()

    fun addPlayer(player: HockeyPlayer_zga_15) {
        players.add(player)
    }

    fun addCoach(coach: Coach_zga_15) {
        coaches.add(coach)
    }

    fun printRoster() {
        println("Coaches:")
        coaches.forEach { println("${it.name} ${it.title}") }
        println("---")
        println("Players\tPosition\tG/A/P")
        players.forEach { player ->
            println("${player.name}\t${player.position}\t${player.number} ${player.goals} ${player.assists} ${player.penalties}")
        }
        println("Number of Players: ${players.size}")
        println("Number of Coaches: ${coaches.size}")
    }
}
