package id.ac.unhas.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var dateCreate: String? = null,
    var dateUpdated : String? = null,
    var dueDate: String? = null,
    var dueHour: String? = null,
    var title: String,
    var note: String
)
