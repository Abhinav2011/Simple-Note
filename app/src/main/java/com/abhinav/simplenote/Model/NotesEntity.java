package com.abhinav.simplenote.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NOTES_DATABASE")
public class NotesEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "NoteTitle")
    public String NoteTitle;

    @ColumnInfo(name = "Notes")
    public String Notes;

    @ColumnInfo(name = "Date")
    public String date;

    @ColumnInfo(name = "Priority")
    public String priority;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
