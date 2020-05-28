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

    fun searchByTitle(title: String) : LiveData<List<ToDo>>? {
        return  todoRepository.searchByTitle(title)
    }

    fun sortDateCreatedDesc() : LiveData<List<ToDo>>? {
        return todoRepository.sortDateCreatedDesc()
    }

    fun sortDateCreatedAscend() : LiveData<List<ToDo>>? {
        return todoRepository.sortDateCreatedAscend()
    }

    fun sortDueDateDesc() : LiveData<List<ToDo>>? {
        return todoRepository.sortDueDateDesc()
    }

    fun sortDueDateAscend() : LiveData<List<ToDo>>? {
        return todoRepository.sortDueDateAscend()
    }

    fun getTodos(): LiveData<List<ToDo>>? {
        return lists
    }

    fun deleteTodo(toDo: ToDo) {
        todoRepository.deleteTodo(toDo)
    }

    fun updateTodo(toDo: ToDo) {
        todoRepository.updateTodo(toDo)
    }

}