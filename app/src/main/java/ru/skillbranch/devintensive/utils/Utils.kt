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
        val words = payload.trim().split(" ").toMutableList()

        var output = ""
        var first = true


        for(word in words){
            var trimWord = word.trim()
            var transliteratedWordBuilder = StringBuilder()
            for (i in 0 until trimWord.length) {
                transliteratedWordBuilder.append(transliterationMap[trimWord[i].toString().toLowerCase()] ?: trimWord[i])
            }

            if (!first)
                output += divider
            else
                first = false

            output += transliteratedWordBuilder.toString().capitalize()
        }

        return output.trim()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fn = firstName?.trim()
        val ln = lastName?.trim()

        if (fn.isNullOrBlank() && ln.isNullOrBlank())
            return null

        if (fn.isNullOrBlank())
            return ln?.get(0)?.toUpperCase().toString()

        if (ln.isNullOrBlank())
            return fn?.get(0)?.toUpperCase().toString()

        return "${fn?.get(0)?.toUpperCase()}${ln?.get(0)?.toUpperCase()}"
    }
}


