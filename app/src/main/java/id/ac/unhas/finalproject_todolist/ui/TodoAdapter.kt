package id.ac.unhas.finalproject_todolist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.ac.unhas.finalproject_todolist.R
import id.ac.unhas.todolist.db.ToDo
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class TodoAdapter (private val context: Context?, private val listener: (ToDo, Int) -> Unit) :
    RecyclerView.Adapter<TodoViewHolder>() {

    private var list = listOf<ToDo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
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

    fun filterList(lists: List<ToDo>) {
        this.list = lists
        notifyDataSetChanged()
    }


}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(context: Context, toDo: ToDo, listener: (ToDo, Int) -> Unit) {
        itemView.list_item_title.text = toDo.title
        itemView.list_item_note.text = toDo.note
        itemView.list_item_duedate.text = toDo.dueDate
        itemView.list_item_duetime.text = toDo.dueHour

        itemView.setOnClickListener {
            listener(toDo, layoutPosition)
        }
    }
}
