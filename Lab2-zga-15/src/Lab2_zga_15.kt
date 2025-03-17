package lab2_zga_15


//Imports
import entities_zga_15.Team_zga_15
import entities_zga_15.HockeyPlayer_zga_15
import entities_zga_15.Coach_zga_15


fun main() {
    val team = Team_zga_15()

    // Adding players before the coach
    team.addPlayer(HockeyPlayer_zga_15("Sam Kanjo", "1112223333", "sam@sharks.com", "89", "Center", 3, 4, 0))
    team.addPlayer(HockeyPlayer_zga_15("Zach Student", "2223334444", "Zach@sharks.com", "90", "Left Wing", 3, 1, 0))

    // Adding coach in the middle
    team.addCoach(Coach_zga_15("Coach Jackson", "1234567890", "coach@sharks.com", "Head Coach"))

    // Adding more players after the coach
    team.addPlayer(HockeyPlayer_zga_15("Paul Student", "3334445555", "Paul@sharks.com", "88", "Right Wing", 0, 0, 1))
    team.addPlayer(HockeyPlayer_zga_15("James Kamau", "4445556666", "james@sharks.com", "91", "Defense", 1, 2, 3))
    team.addPlayer(HockeyPlayer_zga_15("Janeth Tito", "5556667777", "janeth@sharks.com", "92", "Goalie", 0, 1, 1))


    // Printing the team roster
    team.printRoster()
}
