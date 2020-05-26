package id.ac.unhas.finalproject_todolist.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import java.text.SimpleDateFormat
import java.util.*

class UpdateTodo : AppCompatActivity() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var toDo: ToDo
    private lateinit var editTitle: EditText
    private lateinit var editNote: EditText
    private lateinit var editTime: EditText
    private lateinit var editDueDate: EditText
    private lateinit var updateButton: Button
    private var calendar = Calendar.getInstance()

    // to get existing data from database for updating
    companion object{
        const val TITLE_UPDATE = "TITLE"
        const val DUE_DATE_UPDATE = "date-month-year"
        const val TIME_UPDATE = "hour:minutes"
        const val NOTE_UPDATE = "NOTE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_todo)

        editTitle = findViewById(R.id.title_content)
        editNote = findViewById(R.id.notes_content)
        editDueDate = findViewById(R.id.duedate_content)
        editTime = findViewById(R.id.duetime_content)
        updateButton = findViewById(R.id.button_save)


        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        // get data for update
        getDatafromDatabase()

        editDueDate.setOnClickListener() {
            setDueDate()
        }

        editTime.setOnClickListener() {
            setDueTime()
        }

        updateButton.setOnClickListener() {
            updateTodo()
        }
    }

    // Go back to Dashboard if you cancel update To Do
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun getDatafromDatabase() {
        toDo = intent.getParcelableExtra("UPDATED_DATA")!!
        editTitle.setText(intent.getStringExtra(TITLE_UPDATE))
        editNote.setText(intent.getStringExtra(NOTE_UPDATE))
        editDueDate.setText(intent.getStringExtra(DUE_DATE_UPDATE))
        editTime.setText(intent.getStringExtra(TIME_UPDATE))
    }

    private fun setDueDate(){
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        // Date picker dialog
        val datePicker = DatePickerDialog(
            this@UpdateTodo,
            DatePickerDialog.OnDateSetListener{
                    view, year, month, date ->
                editDueDate.setText("" + date + "-" + (month+1) + "-" + year)
            }, year, month, date)

        datePicker.show()
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

    private fun updateTodo() {
        val title = editTitle.text.toString().trim()
        val note = editNote.text.toString().trim()
        val dueDate = editDueDate.text.toString().trim()
        val time = editTime.text.toString().trim()

        todoViewModel.updateTodo(toDo)
        finish()
    }
}
