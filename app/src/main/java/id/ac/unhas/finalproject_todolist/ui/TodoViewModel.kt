package id.ac.unhas.finalproject_todolist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.unhas.finalproject_todolist.db.TodoRepository
import id.ac.unhas.todolist.db.ToDo
import java.util.*

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private var todoRepository = TodoRepository(application)
    private var lists: LiveData<List<ToDo>>? = todoRepository.getTodos()

    fun addTodos(toDo: ToDo) {
        todoRepository.AddTodos(toDo)
    }

    fun addNotes(toDo: ToDo) {
        todoRepository.AddNotes(toDo)
    }

    fun getTodos(): LiveData<List<ToDo>>? {
        return lists
    }

    fun addDueDate(toDo: ToDo) {
        todoRepository.addDueDate(toDo)
    }

    fun deleteTodo(toDo: ToDo) {
        todoRepository.deleteTodo(toDo)
    }

    fun updateTodo(toDo: ToDo) {
        todoRepository.deleteTodo(toDo)
    }

}