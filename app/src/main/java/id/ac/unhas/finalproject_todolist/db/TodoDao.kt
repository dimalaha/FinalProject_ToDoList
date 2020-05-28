package id.ac.unhas.todolist.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodo() : LiveData<List<ToDo>>

    @Query("SELECT * FROM todo WHERE title LIKE :title")
    fun searchByTitle(title: String) : LiveData<List<ToDo>>

    @Query("SELECT * FROM todo ORDER BY date_created DESC")
    fun sortDateCreatedDesc() : LiveData<List<ToDo>>

    @Query("SELECT * FROM todo ORDER BY date_created ASC")
    fun sortDateCreatedAscend() : LiveData<List<ToDo>>

    @Query("SELECT * FROM todo ORDER BY due_date DESC, due_hour DESC")
    fun sortDueDateDesc() : LiveData<List<ToDo>>

    @Query("SELECT * FROM todo ORDER BY due_date ASC, due_hour ASC")
    fun sortDueDateAscend() : LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: ToDo)

    @Delete
    suspend fun deleteTodo(todo: ToDo)

    @Update
    suspend fun updateTodo(todo: ToDo)

}