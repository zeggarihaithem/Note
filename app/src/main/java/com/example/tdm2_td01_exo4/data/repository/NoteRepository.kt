package com.example.tdm2_td01_exo4.data.repository

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.tdm2_td01_exo4.data.dao.NoteDao
import com.example.tdm2_td01_exo4.data.dataBase.NoteDataBase
import com.example.tdm2_td01_exo4.data.entity.Note


class NoteRepository(application: Application?) {
    private val noteDao: NoteDao?
    private val allNotes: LiveData<List<Note?>?>?

    init {
        val database: NoteDataBase? = NoteDataBase.getInstance(application as Context)
        noteDao = database?.noteDao()
        allNotes = noteDao?.getAllNotes()
    }
    fun insert(note: Note?) {
        noteDao?.let { InsertNoteAsyncTask(it).execute(note) }
    }

    fun update(note: Note?) {
        noteDao?.let { UpdateNoteAsyncTask(it).execute(note) }
    }

    fun delete(note: Note?) {
        noteDao?.let { DeleteNoteAsyncTask(it).execute(note) }
    }

    fun deleteAllNotes() {
        noteDao?.let { DeleteAllNotesAsyncTask(it).execute() }
    }

    fun getAllNotes(): LiveData<List<Note?>?>? {
        return allNotes
    }

    class InsertNoteAsyncTask(private val noteDao : NoteDao):AsyncTask<Note?, Void?, Void?>() {
       override fun doInBackground(vararg notes: Note?): Void? {
           notes[0]?.let { noteDao.addNote(it) }
            return null
        }

    }

    class UpdateNoteAsyncTask (private val noteDao : NoteDao):AsyncTask<Note?, Void?, Void?>() {
       override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.updateNote(it) }
            return null
        }

    }

    class DeleteNoteAsyncTask(private val noteDao : NoteDao):AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.deleteNote(it) }
            return null
        }

    }

    class DeleteAllNotesAsyncTask (private val noteDao : NoteDao):AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            noteDao.deleteAllNotes()
            return null
        }

    }


}