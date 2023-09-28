import java.lang.Exception

fun main() {
    val id: String = getNatId()
    val idInfo: IdInfo? = getInfoFromId(id)
    printIdData(idInfo)
}


fun printIdData(info: IdInfo?) {
    if (info == null) {
        println("INVALID NATIONAL ID")
    } else {
        println("Birth data: ${info.birthDay}/${info.birthMonth}/${info.birthYear}")
        println("Birth governorate: ${info.birthGov}")
        println("Gender: ${info.gender}")
    }
}

fun getNatId(): String {
    print("Enter your national id: ")
    return readln()
}

fun getInfoFromId(id: String): IdInfo? {
    if (id.length != 14) {
        return null
    }
    try {
        // birth year
        val isGenTwo: Boolean =
            id[0].code == "2"[0].code
        val gen = if (isGenTwo) "19" else "20"
        val bYear = gen + id.substring(1, 3)
        val bMonth = id.substring(3, 5)
        val bDay = id.substring(5, 7)

        // Governorate
        val govId: String = id.substring(7, 9)
        val gov: String? = govs[govId]

        // Gender
        val gender: String =
            if (id[12].toInt() % 2 == 0) "Female" else "Male"

        return if (gov == null) {
            null
        } else {
            IdInfo(
                bYear,
                bMonth,
                bDay,
                gov,
                gender,
            )
        }
    } catch (e: Exception) {
        return null
    }

}

val govs: Map<String, String> = mapOf(
    "01" to "Cairo",
    "02" to "Alexandria",
    "03" to "Port Said",
    "04" to "Suez",
    "11" to "Damietta",
    "12" to "Dakahlia",
    "13" to "Sharkia",
    "14" to "Qalyubia",
    "15" to "Kafr El-Sheikh",
    "16" to "Gharbia",
    "17" to "Menoufia",
    "18" to "Beheira",
    "19" to "Ismailia",
    "21" to "Giza",
    "22" to "Beni Suef",
    "23" to "Fayoum",
    "24" to "Minya",
    "25" to "Assiut",
    "26" to "Sohag",
    "27" to "Qena",
    "28" to "Aswan",
    "29" to "Luxor",
    "31" to "Red Sea",
    "32" to "New Valley",
    "33" to "Matrouh",
    "34" to "North Sinai",
    "35" to "South Sinai",
    "88" to "outside the Republic",
)

class IdInfo(
    val birthYear: String,
    val birthMonth: String,
    val birthDay: String,
    val birthGov: String,
    val gender: String,
)