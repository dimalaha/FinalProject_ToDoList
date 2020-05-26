package id.ac.unhas.finalproject_todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        recyclerview_todo.layoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(this) { todo, i ->
            showAlertMenu(todo)
        }
        recyclerview_todo.adapter = todoAdapter

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel.getTodos()?.observe(this, Observer {
            todoAdapter.setTodos(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.button_add -> addTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addTodo() {
        val Intent = Intent(this, AddTodo::class.java)
        startActivity(Intent)
        val alert = AlertDialog.Builder(this)




        val editText = EditText(applicationContext)
        editText.hint = "Enter your To Do"

        alert.setTitle("New To Do")
        alert.setView(editText)

        alert.setPositiveButton("Save") { dialog, which ->
            todoViewModel.addTodos(
                ToDo(
                    title = editText.text.toString(),
                    note = editText.text.toString(),
                    dueDate = editText.text.toString())
            )
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        alert.show()
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
                    todoViewModel.deleteTodo(toDo)
                }
            }
        }
        builder.show()
    }

    private fun EditTodo(toDo: ToDo) {
        val Intent = Intent(this, UpdateTodo::class.java)
        startActivity(Intent)
    }
}
