package com.abhinav.simplenote.Activity;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.abhinav.simplenote.Model.NotesEntity;
import com.abhinav.simplenote.R;
import com.abhinav.simplenote.ViewModel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Date;

public class Update_Note_Activity extends AppCompatActivity {
    String sTitle, sNotes;
    String priority_val;
    EditText EdTitle, EdNotes;
    FloatingActionButton update_button;
    RadioGroup UpdateradioGroup;
    RadioButton Updateradiobutton;
    RadioButton sHigh, sMedium, sLow;
    NotesViewModel notesViewModel;
    int Sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__note_);
        //get all layout
        EdTitle = findViewById(R.id.Update_title);
        EdNotes = findViewById(R.id.Update_Notes);
        update_button = findViewById(R.id.Update_Note_Button);
        UpdateradioGroup = findViewById(R.id.Update_Priority_Group);
        sHigh = findViewById(R.id.UpHigh_Priority);
        sMedium = findViewById(R.id.UpMedium_Priority);
        sLow = findViewById(R.id.UpLow_Priority);

        //get old data from insert activity
        Sid = getIntent().getIntExtra("id",0);
        sTitle = getIntent().getStringExtra("title");
        sNotes = getIntent().getStringExtra("notes");

        //set old data
        EdTitle.setText(sTitle);
        EdNotes.setText(sNotes);

        //get view model
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = EdTitle.getText().toString();
                String notes = EdNotes.getText().toString();
                int selected_priority = UpdateradioGroup.getCheckedRadioButtonId();
                Updateradiobutton = (RadioButton)findViewById(selected_priority);
                if(selected_priority == -1){
                    Toast.makeText(Update_Note_Activity.this,"Please select priority",Toast.LENGTH_LONG).show();
                }
                else{
                    String str = Updateradiobutton.getText().toString();
                    String a = sHigh.getText().toString();
                    String b = sMedium.getText().toString();
                    String c = sLow.getText().toString();
                    if(str.compareTo(a) == 0){
                        priority_val = "1";
                    }
                    else if(str.compareTo(b) == 0){
                        priority_val = "2";
                    }
                    else if(str.compareTo(c) == 0){
                        priority_val = "3";
                    }
                    UpdateNotes(title,notes,priority_val);
                }
            }
        });



    }

    private void UpdateNotes(String title, String notes, String priority_val) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        NotesEntity updateNotes = new NotesEntity();
        updateNotes.id = Sid;
        updateNotes.NoteTitle = title;
        updateNotes.Notes = notes;
        updateNotes.date = sequence.toString();
        updateNotes.priority = priority_val;
        notesViewModel.ViewModelUpdateNote(updateNotes);
        FancyToast.makeText(this,"Note updated successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Delete_note){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Update_Note_Activity.this);
            View view = LayoutInflater.from(Update_Note_Activity.this).
                    inflate(R.layout.delete_bottom_options,(LinearLayout) findViewById(R.id.design_bottom_sheet));
            bottomSheetDialog.setContentView(view);
            TextView yes, no;
            yes = view.findViewById(R.id.bottom_sheet_YES);
            no = view.findViewById(R.id.bottom_sheet_NO);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesViewModel.ViewModelDeleteNote(Sid);
                    FancyToast.makeText(Update_Note_Activity.this,"Deleted successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                    finish();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
        }
        return true;
    }
}