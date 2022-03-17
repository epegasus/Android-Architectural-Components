package com.architectural.components.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.architectural.components.database.AppDatabase
import com.architectural.components.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppRepository(context: Context) {

    var noteList: LiveData<List<Note>>
    private val appDatabase: AppDatabase = AppDatabase.getInstance(context)
    private var coroutine: CoroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        noteList = getAllNotes()
    }

    private fun getAllNotes(): LiveData<List<Note>> {
        return appDatabase.noteDao().getAllNotes()
    }

    fun addNote(note: Note): Boolean {
        coroutine.launch {
            appDatabase.noteDao().insertNote(note)
        }
        return true
    }
}