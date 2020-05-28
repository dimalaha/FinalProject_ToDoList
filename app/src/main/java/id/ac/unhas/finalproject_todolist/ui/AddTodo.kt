package id.ac.unhas.finalproject_todolist.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import android.widget.Button
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.util.*

class AddTodo : AppCompatActivity() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var editTitle: EditText
    private lateinit var editNote: EditText
    private lateinit var editTime: EditText
    private lateinit var editDueDate: EditText
    private lateinit var saveButton: Button
    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        editTitle = findViewById(R.id.title_content)
        editNote = findViewById(R.id.notes_content)
        editDueDate = findViewById(R.id.duedate_content)
        editTime = findViewById(R.id.duetime_content)
        saveButton = findViewById(R.id.button_save)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        editDueDate.setOnClickListener {
            setDueDate()
        }

        editTime.setOnClickListener {
            setDueTime()
        }

        saveButton.setOnClickListener {
            saveTodo()
        }

    }

    // Go back to Dashboard if you cancel adding To Do
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun setDueDate(){
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        // Date picker dialog
        val datePicker = DatePickerDialog.OnDateSetListener{
                view, year, month, date ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DATE, date)
            editTime.setText(SimpleDateFormat("dd, MM, yyyy").format(calendar.time))
            }

        DatePickerDialog(this, datePicker, date, month, year).show()
    }

    private fun setDueTime(){
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            editTime.setText(SimpleDateFormat("HH:mm").format(calendar.time))
        }

        TimePickerDialog(this, timeSetListener, hour, minute, true).show()
    }

    private fun saveTodo() {
        val currentTime = ZonedDateTime.now()
        val mill = currentTime.toInstant().epochSecond

        val title = editTitle.text.toString().trim()
        val note = editNote.text.toString().trim()
        val dueDate = editDueDate.text.toString().trim()
        val time = editTime.text.toString().trim()
        val createdDate = mill.toInt()

        todoViewModel.addTodos(
            ToDo(
                dueDate = dueDate,
                note = note,
                title = title,
                dueHour = time,
                dateCreate = createdDate
            )
        )
        finish()
    }
}
