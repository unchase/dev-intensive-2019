package ru.skillbranch.devintensive.utils

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.text.TextPaint
import android.util.TypedValue
import ru.skillbranch.devintensive.R
import java.lang.Math.round

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

    fun validateRepository(repo: String) : Boolean{
        val repoPattern = Regex("^(?:https://|https:\\\\\\\\|)(?:www\\.|)github\\.com/((?:(?:\\d|\\w)|(?:\\d|\\w)-)*(?:\\d|\\w)|(?:\\d|\\w))(?:/|\\\\|)\$", setOf(RegexOption.IGNORE_CASE))
        val githubPattern = Regex("^(?:https://|https:\\\\\\\\|)(?:www\\.|)github\\.com/",setOf(RegexOption.IGNORE_CASE))
        val restrictedWords = arrayOf(
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join"
        )
        return if (repoPattern.matches(repo)){
            val userName = repo
                .replace(githubPattern, "")
                .replace("/","")
                .replace("\\","")
            restrictedWords.indexOf(userName) == -1
        }else{
            false
        }
    }

    fun getInitialsBitmap(cont: Context, initials:String): Bitmap? {
        if (initials.isEmpty()) return null

        val textSizeCoef = 0.43f
        val imageSize = cont.resources.getDimensionPixelSize(R.dimen.avatar_round_size)
        val tv = TypedValue()
        val a = cont.obtainStyledAttributes(tv.data, intArrayOf(R.attr.colorAccent))


        val rectF = RectF()
        with(rectF) {
            bottom = imageSize.toFloat()
            right = imageSize.toFloat()
        }
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.color = a.getColor(0,0)

        val textWidth: Float
        val textBaseLine: Float
        val textPaint = TextPaint(ANTI_ALIAS_FLAG)

        with(textPaint){
            textSize = imageSize * textSizeCoef //* cont.resources.displayMetrics.scaledDensity
            color = Color.WHITE
            textWidth = measureText(initials) * 0.5f
            textBaseLine = fontMetrics.ascent * -0.4f
        }

        val centerX = round(imageSize * 0.5f)
        val centerY = round(imageSize * 0.5f)

        val bitmap = Bitmap.createBitmap(imageSize, imageSize, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        with (canvas) {
            drawRect(rectF, paint)
            drawText(initials, centerX - textWidth, centerY + textBaseLine, textPaint)
        }

        a.recycle()
        return bitmap
    }
}


