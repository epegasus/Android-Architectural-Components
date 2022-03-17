package com.architectural.components.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.architectural.components.model.Note
import com.architectural.components.repository.AppRepository

class FragmentHomeViewModel(application: Application) : AndroidViewModel(application) {

    var noteList: LiveData<List<Note>>

    private var repository: AppRepository = AppRepository(application.applicationContext)

    init {
        noteList = repository.noteList
    }
}