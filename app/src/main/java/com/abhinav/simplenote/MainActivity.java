package com.abhinav.simplenote;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.abhinav.simplenote.Activity.Insert_Note_Activity;
import com.abhinav.simplenote.Adapter.NotesAdapter;
import com.abhinav.simplenote.Model.NotesEntity;
import com.abhinav.simplenote.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_note_button;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NotesAdapter adapter;
    TextView noFilter;
    TextView HighToLowFliter;
    TextView LowToHighFilter;
    List<NotesEntity> filterNotesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //get all resource
        add_note_button = findViewById(R.id.Add_Note_Button);
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        recyclerView = findViewById(R.id.notesRecylerView);
        noFilter = findViewById(R.id.No_filter);
        HighToLowFliter = findViewById(R.id.High_to_low_filter);
        LowToHighFilter = findViewById(R.id.Low_to_high_filter);

        //set drwaable resource in noFilter

        noFilter.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                loadData(0);
                HighToLowFliter.setBackgroundResource(R.drawable.filter_unselected);
                LowToHighFilter.setBackgroundResource(R.drawable.filter_unselected);
                noFilter.setBackgroundResource(R.drawable.filter_selected);
            }
        });

        HighToLowFliter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(1);
                HighToLowFliter.setBackgroundResource(R.drawable.filter_selected);
                LowToHighFilter.setBackgroundResource(R.drawable.filter_unselected);
                noFilter.setBackgroundResource(R.drawable.filter_unselected);
            }
        });

        LowToHighFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(2);
                HighToLowFliter.setBackgroundResource(R.drawable.filter_unselected);
                LowToHighFilter.setBackgroundResource(R.drawable.filter_selected);
                noFilter.setBackgroundResource(R.drawable.filter_unselected);
            }
        });

        add_note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Insert_Note_Activity.class));
            }
        });

        notesViewModel.getAllNote.observe(this, new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notesEntities) {
                setAdapter(notesEntities);
                filterNotesList = notesEntities;
            }
        });
    }
    public void loadData(int i){
        if(i == 0){
            notesViewModel.getAllNote.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notesEntities) {
                    setAdapter(notesEntities);
                    filterNotesList = notesEntities;
                }
            });
        }
        else if(i == 1){
            notesViewModel.getHighToLow.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notesEntities) {
                    setAdapter(notesEntities);
                    filterNotesList = notesEntities;
                }
            });
        }
        else if(i == 2){
            notesViewModel.getLowToHigh.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notesEntities) {
                    setAdapter(notesEntities);
                    filterNotesList = notesEntities;
                }
            });
        }
    }
    public void setAdapter(List<NotesEntity> notesEntities){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notesEntities);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });
        return true;
    }

    private void NotesFilter(String newText) {
        ArrayList<NotesEntity> filter_notes = new ArrayList<>();

        for(NotesEntity notes : this.filterNotesList){
            if(notes.NoteTitle.contains(newText)){
                filter_notes.add(notes);
            }
        }
        this.adapter.search_notes(filter_notes);

    }
}