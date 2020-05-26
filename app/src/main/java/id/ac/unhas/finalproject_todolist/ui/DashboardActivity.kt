package id.ac.unhas.finalproject_todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import kotlinx.android.synthetic.main.activity_dashboard2.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var addButton: FloatingActionButton

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

        addButton.setOnClickListener() {
            addTodo()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        return super.onOptionsItemSelected(item)
    }

    private fun addTodo() {
        val intent = Intent(this, AddTodo::class.java)
        startActivity(intent)
    }

    private fun showAlertMenu(toDo: ToDo) {
        val items = arrayOf("Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            when (which) {
                0 -> {
                    EditTodo(toDo)
                }
                1 -> {
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Delete To Do?")
                    alert.setMessage("Are you sure?")
                    alert.setPositiveButton("Yes"){dialog, _ ->
                        todoViewModel.deleteTodo(toDo)
                        dialog.dismiss()
                    }
                    alert.setNegativeButton("No"){dialog, _ ->
                        dialog.dismiss()
                    }

                }
            }
        }
        builder.show()
    }

    private fun EditTodo(toDo: ToDo) {
        val intent = Intent(this, UpdateTodo::class.java)
            .putExtra(UpdateTodo.TITLE_UPDATE, toDo.title)
            .putExtra(UpdateTodo.NOTE_UPDATE, toDo.note)
            .putExtra(UpdateTodo.DUE_DATE_UPDATE, toDo.dueDate)
            .putExtra(UpdateTodo.TIME_UPDATE, toDo.dueHour)

        startActivity(intent)
    }
}
