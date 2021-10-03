package com.abhinav.simplenote.Activity;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.abhinav.simplenote.Model.NotesEntity;
import com.abhinav.simplenote.R;
import com.abhinav.simplenote.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Date;

public class Insert_Note_Activity extends AppCompatActivity {
    private String title,notes;
    private String priority_val = "1";
    EditText add_title;
    EditText add_notes;
    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton High, Medium, Low;
    FloatingActionButton done_btn;
    NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__note_);

        add_title = findViewById(R.id.Add_title);
        add_notes = findViewById(R.id.Add_Notes);
        radioGroup = findViewById(R.id.Priority_Group);
        High = findViewById(R.id.High_Priority);
        Medium = findViewById(R.id.Medium_Priority);
        Low = findViewById(R.id.Low_Priority);
        done_btn = findViewById(R.id.Done_Note_Button);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = add_title.getText().toString();
                notes = add_notes.getText().toString();
                int selected_priority = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selected_priority);
                if(selected_priority == -1){
                    Toast.makeText(Insert_Note_Activity.this,"Please select priority",Toast.LENGTH_LONG).show();
                }
                else{
                    String str = radioButton.getText().toString();
                    String a = High.getText().toString();
                    String b = Medium.getText().toString();
                    String c = Low.getText().toString();
                    if(str.compareTo(a) == 0){
                        priority_val = "1";
                    }
                    else if(str.compareTo(b) == 0){
                        priority_val = "2";
                    }
                    else if(str.compareTo(c) == 0){
                        priority_val = "3";
                    }
                    CreateNotes(title,notes,priority_val);
                }

            }
        });

    }
    private void CreateNotes(String title, String notes,String priority_val) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        NotesEntity notes1 = new NotesEntity();
        notes1.NoteTitle = title;
        notes1.Notes = notes;
        notes1.date = sequence.toString();
        notes1.priority = priority_val;
        notesViewModel.ViewModelInsertNote(notes1);

        FancyToast.makeText(this,"Note added successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
        finish();

    }

}