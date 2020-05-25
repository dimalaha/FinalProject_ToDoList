package id.ac.unhas.finalproject_todolist.db

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.unhas.todolist.db.ToDo
import id.ac.unhas.todolist.db.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class TodoRepository (application: Application) {
    private val todoDao: TodoDao?
    private var lists: LiveData<List<ToDo>>? = null

    init {
        val db = TodoDatabase.getInstance(application.applicationContext)
        todoDao = db?.todoDao()
        lists = todoDao?.getAllTodo()
    }

    fun getTodos(): LiveData<List<ToDo>>? {
        return lists
    }

    fun AddTodos(toDo: ToDo) = runBlocking {
        this.launch(Dispatchers.IO) {
            todoDao?.addTodo(toDo)
        }
    }

    fun AddNotes(toDo: ToDo) = runBlocking {
        this.launch(Dispatchers.IO) {
            todoDao?.addNote(toDo)
        }
    }

    fun addDueDate(dueDate: Date) = runBlocking {
        this.launch(Dispatchers.IO) {
                todoDao?.addDueDate(dueDate)
            }
        }

        fun deleteTodo(toDo: ToDo) {
            runBlocking {
                this.launch(Dispatchers.IO) {
                    todoDao?.deleteTodo(toDo)
                }
            }
        }

        fun updateTodo(toDo: ToDo) = runBlocking {
            this.launch(Dispatchers.IO) {
                todoDao?.updateTodo(toDo)
            }
        }
    }