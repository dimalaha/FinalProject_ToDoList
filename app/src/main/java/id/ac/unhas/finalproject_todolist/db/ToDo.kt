package id.ac.unhas.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    // val dateCreate: String,
    // val dateUpdated : String,
    var dueDate: String? = null,
    var title: String,
    var note: String
)
