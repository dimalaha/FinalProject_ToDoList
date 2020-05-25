package id.ac.unhas.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val dateCreate: Date? = null,
    val dateUpdated : Date? = null,
    val dueDate: Date?= null,
    var title: String,
    var note: String
)
