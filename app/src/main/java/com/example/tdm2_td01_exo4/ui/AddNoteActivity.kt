package com.example.tdm2_td01_exo4.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tdm2_td01_exo4.R
import com.skydoves.colorpickerpreference.ColorPickerView
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "EXTRA_PRIORITY"
        const val EXTRA_DATE = "EXTRA_DATE"
        const val EXTRA_COLOR = "EXTRA_COLOR"

    }
    private lateinit var color : String
    private lateinit var titleView :EditText
    private lateinit var descriptionView :EditText
    private lateinit var dateView: EditText
    private lateinit var priorityView :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        titleView = findViewById(R.id.edit_text_title)
        descriptionView = findViewById(R.id.edit_text_description)
        dateView = findViewById(R.id.edit_text_date)
        priorityView = findViewById(R.id.edit_text_priority)

        colorPickerView.setColorListener { colorEnvelope ->
            color = "#"+colorEnvelope.colorHtml

        }

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        val title = titleView.text.toString()
        val description = descriptionView.text.toString()
        val date = dateView.text.toString()
        val priority = priorityView.text.toString()
        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()|| date.trim { it <= ' ' }.isEmpty()|| priority.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "remplir tous les champs ", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)
        data.putExtra(EXTRA_DATE,date)
        data.putExtra(EXTRA_COLOR, color)
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
