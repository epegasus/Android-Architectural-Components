package com.architectural.components.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.architectural.components.model.Note
import com.architectural.components.repository.AppRepository

class FragmentAddRecordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository = AppRepository(application.applicationContext)

    fun addNote(text: String): Boolean {
        val note = Note(text, System.currentTimeMillis())
        return repository.addNote(note)
    }
}