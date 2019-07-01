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

        fun makeUserFromBuilder(builder: Builder): User
        {
            var currId = builder.id
            if (builder.id === "-1"){
                lastId++
                currId = lastId.toString()
            }

            return User("$currId", builder.firstName, builder.lastName,
                builder.avatar, builder.rating, builder.respect, builder.lastVisit, builder.isOnline)
        }
    }

    class Builder {
        var id: String = "-1"
            private set

        var firstName: String? = null
            private set

        var lastName: String? = null
            private set

        var avatar: String? = null
            private set

        var rating: Int = 0
            private set

        var respect: Int = 0
            private set

        var lastVisit: Date? = Date()
            private set

        var isOnline: Boolean = false
            private set

        fun id(id: String) = apply { this.id = id }

        fun firstName(firstName: String?) = apply { this.firstName = firstName }

        fun lastName(lastName: String?) = apply { this.lastName = lastName }

        fun avatar(avatar: String?) = apply { this.avatar = avatar }

        fun rating(rating: Int) = apply { this.rating = rating }

        fun respect(respect: Int) = apply { this.respect = respect }

        fun lastVisit(lastVisit: Date?) = apply { this.lastVisit = lastVisit }

        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }

        fun build() = makeUserFromBuilder(this)
    }
}