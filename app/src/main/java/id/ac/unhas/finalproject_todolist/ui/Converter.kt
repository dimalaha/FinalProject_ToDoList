package id.ac.unhas.finalproject_todolist.ui

import java.time.ZonedDateTime

class Converter {
    companion object{
        fun dateToInt(time: ZonedDateTime): Int{
            val millis = time.toInstant().epochSecond
            return millis.toInt()
        }
    }
}