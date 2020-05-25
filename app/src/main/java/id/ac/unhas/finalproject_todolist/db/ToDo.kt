package id.ac.unhas.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title: String,
    var note: String
)
