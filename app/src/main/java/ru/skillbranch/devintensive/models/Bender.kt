package ru.skillbranch.devintensive.models

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    var badAnswersCount = 0

    fun listenAnswer(answer : String) : Pair<String, Triple<Int, Int, Int>>{
        if (question.answers.contains(answer.toLowerCase()))
        {
            badAnswersCount = 0
            question = question.nextQuestion()
            return "Отлично - ты справился\n${question.question}" to status.color
        }else{
            badAnswersCount++
            if (badAnswersCount == 4)
            {
                status = Status.NORMAL
                question = Question.NAME
                badAnswersCount = 0
                return "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
            status = status.nextStatus()
            return "Это неправильный ответ\n${question.question}" to status.color
        }
    }

    enum class Status(val color : Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus() : Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validateAnswer(answer: String): String = if (answer.isBlank() || !answer[0].isUpperCase()) "Имя должно начинаться с заглавной буквы" else ""
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
            override fun validateAnswer(answer: String): String = if (answer.isBlank() || !answer[0].isLowerCase()) "Профессия должна начинаться со строчной буквы" else ""
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
            override fun validateAnswer(answer: String): String = if (answer.contains(Regex("\\d"))) "Материал не должен содержать цифр" else ""
        },
        BDAY("Когда меня содали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
            override fun validateAnswer(answer: String): String = if (!answer.matches(Regex("^\\d{4}\$"))) "Год моего рождения должен содержать только цифры" else ""
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
            override fun validateAnswer(answer: String): String = if (!answer.matches(Regex("^\\d{7}\$"))) "Серийный номер содержит только цифры, и их 7" else ""
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
            override fun validateAnswer(answer: String): String = ""
        };

        abstract fun nextQuestion() : Question
        abstract fun validateAnswer(answer:String):String
    }
}