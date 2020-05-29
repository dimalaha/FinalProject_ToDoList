package id.ac.unhas.finalproject_todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import kotlinx.android.synthetic.main.activity_dashboard2.*
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var addButton: Button
    private lateinit var searchFilter: EditText
    private lateinit var filterSortButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        addButton = findViewById(R.id.button_add)
        filterSortButton = findViewById(R.id.filter_list)

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

        filterSortButton.setOnClickListener {
            sortList()
        }

        searchFilter = findViewById(R.id.search_bar)
        searchFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    filter(s.toString())
                } else {
                    todoViewModel.getTodos()?.observe(this@DashboardActivity, Observer {
                        todoAdapter.setTodos(it)
                    })
                }
            }
        })
    }

    // fun to do filter if user want to search by filter
    private fun filter(text: String) {
        val lists = listOf<ToDo>()
        val filteredList = ArrayList<ToDo>()

        for (s in lists) {
            if (s.title.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(s)
            }
        }
        
        todoViewModel.searchByTitle(text)?.observe(this, Observer {
            todoAdapter.filterList(it)
        })
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
        val items = arrayOf("Sort by Created Date", "Sort by Due Date")

        val builder = AlertDialog.Builder(this)
        val alerts = AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            when (which) {
                0 -> {
                    alerts.setTitle(items[which])
                        .setPositiveButton("Newest to Oldest") { dialog, which ->
                            todoViewModel.sortDateCreatedDesc()?.observe(this, Observer {
                                todoAdapter.setTodos(it)
                            })
                        }
                        .setNegativeButton("Oldest to Newest") { dialog, which ->
                            todoViewModel.sortDateCreatedAscend()?.observe(this, Observer {
                                todoAdapter.setTodos(it)
                            })
                        }
                    alerts.show()
                }
                1 -> {
                    alerts.setTitle(items[which])
                        .setPositiveButton("Oldest to Newest") { dialog, which ->
                            todoViewModel.sortDueDateDesc()?.observe(this, Observer {
                                todoAdapter.setTodos(it)
                            })
                        }
                        .setNegativeButton("Newest to Oldest") { dialog, which ->
                            todoViewModel.sortDueDateAscend()?.observe(this, Observer {
                                todoAdapter.setTodos(it)
                            })
                        }
                    alerts.show()
                }
            }
        }
        builder.show()
    }

    private fun showAlertMenu(toDo: ToDo) {
        val items = arrayOf("Done", "Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            when (which) {
                0 -> {
                    todoViewModel.deleteTodo(toDo)
                    Toast.makeText(applicationContext, "Task completed!", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    editTodo(toDo)
                }
                2 -> {
                    val alerts = AlertDialog.Builder(this)
                    alerts.setTitle("Are you sure?")
                    alerts.setPositiveButton("Yes") { dialog, which ->
                        todoViewModel.deleteTodo(toDo)
                        Toast.makeText(applicationContext, "Task deleted", Toast.LENGTH_SHORT).show()
                    }
                    alerts.setNegativeButton("No") {dialog, which ->
                        dialog.dismiss()
                    }
                    alerts.show()
                }
            }
        }
        builder.show()
    }

    private fun editTodo(toDo: ToDo) {
        val intent = Intent(this, UpdateTodo::class.java)
            .putExtra("UPDATED_LIST", toDo)
            .putExtra(UpdateTodo.TITLE_UPDATE, toDo.title)
            .putExtra(UpdateTodo.NOTE_UPDATE, toDo.note)
            .putExtra(UpdateTodo.DUE_DATE_UPDATE, toDo.dueDate)
            .putExtra(UpdateTodo.TIME_UPDATE, toDo.dueHour)

        this.startActivity(intent)
    }
}
