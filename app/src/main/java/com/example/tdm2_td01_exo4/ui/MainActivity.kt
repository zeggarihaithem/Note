package com.example.tdm2_td01_exo4.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tdm2_td01_exo4.R
import com.example.tdm2_td01_exo4.adapter.NoteAdapter
import com.example.tdm2_td01_exo4.data.entity.Note
import com.example.tdm2_td01_exo4.viewModel.NoteViewModel
import com.example.tdm2_td01_exo4.viewModel.NoteViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {
    companion object{
        const val ADD_NOTE_REQUEST = 1
    }

    private lateinit var noteViewModel : NoteViewModel
    private lateinit var noteViewModelFactory: NoteViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add_note.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        //set recycle view adapter
        val recyclerView: RecyclerView = recycler_view as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter()
        recyclerView.adapter = adapter

        //set ViewModel
        noteViewModelFactory = NoteViewModelFactory(this.application)
        noteViewModel= ViewModelProviders.of(this ,noteViewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes()
            ?.observe(this,
                Observer { notes -> adapter.setNotes(notes as List<Note>) })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_NOTE_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                val title = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE)
                val description = data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
                val priority = data?.getStringExtra(AddNoteActivity.EXTRA_PRIORITY)
                val date = data?.getStringExtra(AddNoteActivity.EXTRA_DATE)
                val color = data?.getStringExtra(AddNoteActivity.EXTRA_COLOR)

                val note = Note(title!!,description!!,priority!!,date!!,color!!)
                noteViewModel.insert(note)
                Toast.makeText(this, "Note ajouté", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Note n'a pas ajouté", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


