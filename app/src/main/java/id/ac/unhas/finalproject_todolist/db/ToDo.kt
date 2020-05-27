package id.ac.unhas.todolist.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "date_created")
    var dateCreate: Int? = null,

    @ColumnInfo(name = "date_updated")
    var dateUpdated : Int? = null,

    @ColumnInfo(name = "due_date")
    var dueDate: String? = null,

    @ColumnInfo(name = "due_hour")
    var dueHour: String? = null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "note")
    var note: String
) : Parcelable
