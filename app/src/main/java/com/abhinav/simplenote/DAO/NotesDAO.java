package com.abhinav.simplenote.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.abhinav.simplenote.Model.NotesEntity;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM NOTES_DATABASE")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("SELECT * FROM NOTES_DATABASE ORDER BY Priority ASC")
    LiveData<List<NotesEntity>> HighToLow();

    @Query("SELECT * FROM NOTES_DATABASE ORDER BY Priority DESC")
    LiveData<List<NotesEntity>> LowToHigh();

    @Insert
    public void InsertNote(NotesEntity... notesEntities);

    @Query("DELETE FROM NOTES_DATABASE WHERE id=:id")
    public void DeleteNote(int id);

    @Update
    public void UpdateNote(NotesEntity notesEntity);

}
