package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
){
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String): this(id = id, firstName = "John", lastName = "Doe $id")

    init {
        println("It's Alive!!!\n" +
                "${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName\n"}")
    }

    fun printMe() = println("""
            id : $id
            firstName : $firstName
            lastName : $lastName
            avatar : $avatar
            rating : $rating
            respect : $respect
            lastVisit : $lastName
            isOnline : $isOnline
        """.trimIndent())

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String?): User {
            lastId++

            var (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}