package com.architectural.components.daos

import androidx.room.*
import com.architectural.components.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNoteList(noteList: List<Note>)

    @Delete
    fun deleteNote(note: Note)

    @Query("Delete FROM notes")
    fun deleteAllNotes(): Int

    @Query("Select * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): Note

    @Query("Select * FROM notes ORDER BY timeStamp DESC")
    fun getAllNotes(): List<Note>

    @Query("Select COUNT(*) FROM notes")
    fun getSize(): Int

}