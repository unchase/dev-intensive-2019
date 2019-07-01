package ru.skillbranch.devintensive.utils

private val transliterationMap = mapOf(
    "а" to "a",
    "б" to "b",
    "в" to "v",
    "г" to "g",
    "д" to "d",
    "е" to "e",
    "ё" to "e",
    "ж" to "zh",
    "з" to "z",
    "и" to "i",
    "й" to "i",
    "к" to "k",
    "л" to "l",
    "м" to "m",
    "н" to "n",
    "о" to "o",
    "п" to "p",
    "р" to "r",
    "с" to "s",
    "т" to "t",
    "у" to "u",
    "ф" to "f",
    "х" to "h",
    "ц" to "c",
    "ч" to "ch",
    "ш" to "sh",
    "щ" to "sh'",
    "ъ" to "",
    "ы" to "i",
    "ь" to "",
    "э" to "e",
    "ю" to "yu",
    "я" to "ya"
)

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts : List<String>? = fullName?.trim()?.split(" ")

        var firstName = parts?.getOrNull(0)
        if (firstName.isNullOrBlank())
            firstName = null
        var lastName = parts?.getOrNull(1)
        if (lastName.isNullOrBlank())
            lastName = null

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val words = payload.trim().split(divider).toMutableList()

        var output = ""

        for(word in words){
            var transliteratedWordBuilder = StringBuilder()
            for (i in 0 until word.length) {
                when (divider) {
                    word[i].toString() -> transliteratedWordBuilder.append(divider)
                    else -> transliteratedWordBuilder.append(transliterationMap[word[i].toString().toLowerCase()] ?: word[i])
                }
            }

            output += transliteratedWordBuilder.toString().capitalize() + divider
        }

        return output.trim()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fn = firstName?.trim()
        val ln = lastName?.trim()

        if (fn.isNullOrBlank() || ln.isNullOrBlank())
            return null

        if (fn.isNullOrBlank())
            return ln.toUpperCase()

        if (ln.isNullOrBlank())
            return fn.toUpperCase()

        return "${fn.toUpperCase()}${ln.toUpperCase()}"
    }
}


