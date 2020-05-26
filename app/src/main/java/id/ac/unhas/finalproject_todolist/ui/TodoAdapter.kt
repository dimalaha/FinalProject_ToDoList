package id.ac.unhas.finalproject_todolist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import kotlinx.android.synthetic.main.activity_add_todo.view.*
import kotlinx.android.synthetic.main.activity_dashboard2.view.*

class TodoAdapter (private val context: Context?, private val listener: (ToDo, Int) -> Unit) :
    RecyclerView.Adapter<TodoViewHolder>() {

    private var list = listOf<ToDo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        if (context!= null) {
            holder.bindItem(context, list[position], listener)
        }
    }

    fun setTodos(lists: List<ToDo>) {
        this.list = lists
        notifyDataSetChanged()
    }
}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(context: Context, toDo: ToDo, listener: (ToDo, Int) -> Unit) {
        itemView.title.text = toDo.title
        itemView.notes.text = toDo.note
        itemView.duedate.text = toDo.dueDate

        itemView.setOnClickListener {
            listener(toDo, layoutPosition)
        }
    }
}