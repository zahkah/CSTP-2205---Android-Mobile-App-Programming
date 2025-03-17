
package entities_zga_15

class HockeyPlayer_zga_15(
    name: String,
    phoneNumber: String,
    emailAddress: String,
    val number: String,
    val position: String,
    var goals: Int,
    var assists: Int,
    var penalties: Int
) : Person_zga_15(name, phoneNumber, emailAddress) {
    override fun toString(): String {
        return "$name - $position - Goals: $goals, Assists: $assists, Penalties: $penalties"
    }
}
