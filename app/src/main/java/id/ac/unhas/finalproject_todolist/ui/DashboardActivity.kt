package id.ac.unhas.finalproject_todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import kotlinx.android.synthetic.main.activity_dashboard2.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var addButton: Button
  //  private lateinit var filterSortButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        addButton = findViewById(R.id.button_add)

        recyclerview_todo.layoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(this) { todo, i ->
            showAlertMenu(todo)
        }
        recyclerview_todo.adapter = todoAdapter

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel.getTodos()?.observe(this, Observer {
            todoAdapter.setTodos(it)
        })

        addButton.setOnClickListener {
            addTodo()
        }

//        filterSortButton.setOnClickListener {
    //        sortList()
    //    }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun addTodo() {
        val intent = Intent(this, AddTodo::class.java)
        startActivity(intent)
    }

    private fun sortList() {

    }

    private fun showAlertMenu(toDo: ToDo) {
        val items = arrayOf("Done", "Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            when (which) {
                0 -> {
                    todoViewModel.deleteTodo(toDo)
                }
                1 -> {
                    EditTodo(toDo)
                }
                2 -> {
                    todoViewModel.deleteTodo(toDo)

                }
            }
        }
        builder.show()
    }

    private fun EditTodo(toDo: ToDo) {
        val intent = Intent(this, UpdateTodo::class.java)
            .putExtra("UPDATED_LIST", toDo)
            .putExtra(UpdateTodo.TITLE_UPDATE, toDo.title)
            .putExtra(UpdateTodo.NOTE_UPDATE, toDo.note)
            .putExtra(UpdateTodo.DUE_DATE_UPDATE, toDo.dueDate)
            .putExtra(UpdateTodo.TIME_UPDATE, toDo.dueHour)

        this.startActivity(intent)
    }
}
