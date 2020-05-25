package id.ac.unhas.finalproject_todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.unhas.todolist.db.ToDo
import id.ac.unhas.todolist.db.TodoDao

@Database (entities = [ToDo::class], exportSchema= false, version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    companion object {
        private const val db_name = "ToDo_Database"
        private var instance: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase? {
            if (instance==null) {
                synchronized(TodoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        TodoDatabase::class.java,
                        db_name
                    ).build()
                }
            }
            return instance
        }
    }
}