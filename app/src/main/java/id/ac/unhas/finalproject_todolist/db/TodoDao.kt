package id.ac.unhas.todolist.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodo() : LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: ToDo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(todo: ToDo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDueDate(dueDate: Date)

    @Delete
    suspend fun deleteTodo(todo: ToDo)

    @Update
    suspend fun updateTodo(todo: ToDo)

}