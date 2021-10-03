package com.abhinav.simplenote.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.abhinav.simplenote.Model.NotesEntity;
import com.abhinav.simplenote.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<NotesEntity>> getAllNote;
    public LiveData<List<NotesEntity>> getHighToLow;
    public LiveData<List<NotesEntity>> getLowToHigh;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        getAllNote = notesRepository.getAllNotes;
        getHighToLow = notesRepository.getHighToLow;
        getLowToHigh = notesRepository.getLowToHigh;
    }

    public void ViewModelInsertNote(NotesEntity note){
        notesRepository.InsertNote(note);
    }
    public void ViewModelDeleteNote(int id){
        notesRepository.DeleteNote(id);
    }
    public void ViewModelUpdateNote(NotesEntity note){
        notesRepository.UpdateNote(note);
    }
}
