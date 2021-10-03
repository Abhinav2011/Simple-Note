package com.abhinav.simplenote.Repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.abhinav.simplenote.DAO.NotesDAO;
import com.abhinav.simplenote.Database.NotesDatabase;
import com.abhinav.simplenote.Model.NotesEntity;

import java.util.List;

public class NotesRepository {

    public NotesDAO notesDAO;
    public LiveData<List<NotesEntity>> getAllNotes;
    public LiveData<List<NotesEntity>> getHighToLow;
    public LiveData<List<NotesEntity>> getLowToHigh;

    public NotesRepository(Application application){
        NotesDatabase notesDatabase = NotesDatabase.getDatabaseInstance(application);
        notesDAO = notesDatabase.notesDAO();
        getAllNotes = notesDAO.getAllNotes();
        getHighToLow = notesDAO.HighToLow();
        getLowToHigh = notesDAO.LowToHigh();
    }
    public void InsertNote(NotesEntity note){
        notesDAO.InsertNote(note);
    }
    public void DeleteNote(int id){
        notesDAO.DeleteNote(id);
    }
    public void UpdateNote(NotesEntity note){
        notesDAO.UpdateNote(note);
    }
}
