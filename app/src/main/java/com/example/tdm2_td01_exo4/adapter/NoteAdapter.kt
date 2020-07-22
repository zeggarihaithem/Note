package com.example.tdm2_td01_exo4.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tdm2_td01_exo4.R
import com.example.tdm2_td01_exo4.data.entity.Note


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var notes: List<Note> = ArrayList()
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: NoteHolder, position: Int) {
        val currentNote: Note = notes[position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()
        holder.textViewDate.text = currentNote.date
        holder.noteCardView.setCardBackgroundColor(Color.parseColor(currentNote.color))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
    fun getNoteAt(position: Int): Note? {
        return notes[position]
    }
    inner class NoteHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal val noteCardView :CardView = itemView.findViewById(R.id.note_item)
        internal val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        internal val textViewDescription: TextView = itemView.findViewById(R.id.text_view_description)
        internal val textViewPriority: TextView = itemView.findViewById(R.id.text_view_priority)
        internal val textViewDate: TextView = itemView.findViewById(R.id.text_view_date)


    }
}
