package com.abhinav.simplenote.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.abhinav.simplenote.DAO.NotesDAO;
import com.abhinav.simplenote.Model.NotesEntity;

@Database(entities = {NotesEntity.class},version = 2)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDAO notesDAO();
    public static NotesDatabase INSTANCE;
    public static NotesDatabase getDatabaseInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"NOTES_DATABSE").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
