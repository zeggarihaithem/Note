package com.example.tdm2_td01_exo4.data.dataBase

import android.content.Context
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tdm2_td01_exo4.data.dao.NoteDao
import com.example.tdm2_td01_exo4.data.entity.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object {
        private var instance: NoteDataBase? = null
        @Synchronized
        fun getInstance(context: Context): NoteDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java, "note_database"
                )
                    .fallbackToDestructiveMigration()
                   // .addCallback(roomCallback)
                    .build()
            }
            return instance
        }
      /*private val roomCallback: Callback = object : Callback() {
            override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { PopulateDbAsyncTask(it).execute() }
            }
        }

        private open class PopulateDbAsyncTask(db: NoteDataBase) :
            AsyncTask<Void?, Void?, Void?>() {
            private val noteDao: NoteDao = db.noteDao()!!
            protected override fun doInBackground(vararg params: Void?): Void? {
                noteDao.addNote(Note("Title 1", "Description 1", "1","01-01-2019","#ff0000"))
                return null
            }

        }*/
    }

}