package ru.skillbranch.devintensive.utils

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

    fun transliteration(payload: String, divider: String=" "): String {
        fun transliterateChar(char:String):String {
            return when (char) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                " " -> divider
                else -> char
            }
        }

        var result = ""
        for (char in payload){
            result += if(char.isUpperCase()) transliterateChar(char.toLowerCase().toString()).capitalize()
            else transliterateChar(char.toString())
        }

        return result
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


