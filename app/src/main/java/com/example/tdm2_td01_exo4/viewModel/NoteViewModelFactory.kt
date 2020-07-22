package com.example.tdm2_td01_exo4.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tdm2_td01_exo4.data.repository.NoteRepository

/**
 *  This class is for passing  the VideosRepository to the VideoFragment
 *  when we create the instance of the VideoViewModel
 */

@Suppress("UNCHECKED_CAST")
open class NoteViewModelFactory(
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(
            application
        ) as T
    }

}