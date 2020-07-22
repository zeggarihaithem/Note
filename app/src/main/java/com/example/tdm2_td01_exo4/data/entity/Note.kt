package com.example.tdm2_td01_exo4.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val title: String, val description: String ,val priority :String, val date: String, val color :String) {
   @PrimaryKey(autoGenerate = true)
   var id = 0

}