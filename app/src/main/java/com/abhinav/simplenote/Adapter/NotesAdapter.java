package com.abhinav.simplenote.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;
import com.abhinav.simplenote.Activity.Update_Note_Activity;
import com.abhinav.simplenote.MainActivity;
import com.abhinav.simplenote.Model.NotesEntity;
import com.abhinav.simplenote.R;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {
    MainActivity mainActivity;
    List<NotesEntity> notesEntities;
    List<NotesEntity> searchNotesItem;
    public NotesAdapter(MainActivity mainActivity, List<NotesEntity> notesEntities) {
        this.mainActivity = mainActivity;
        this.notesEntities = notesEntities;
        searchNotesItem = new ArrayList<>(notesEntities);
    }

    public void search_notes(List<NotesEntity> filter_name){
        this.notesEntities = filter_name;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout return
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewHolder holder, int position) {
        NotesEntity notes = notesEntities.get(position);
        if(notes.priority.equals("1")){
            holder.priority_item.setText("High");
        }
        else if(notes.priority.equals("2")){
            holder.priority_item.setText("Medium");
        }
        else if(notes.priority.equals("3")){
            holder.priority_item.setText("Low");
        }
        holder.title.setText(notesEntities.get(position).NoteTitle);
        holder.date.setText(notesEntities.get(position).date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, Update_Note_Activity.class);
                intent.putExtra("id",notes.id);
                intent.putExtra("title",notes.NoteTitle);
                intent.putExtra("notes",notes.Notes);
                intent.putExtra("priority",notes.priority);

                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesEntities.size();
    }
    public class notesViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        TextView priority_item;
        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            date = itemView.findViewById(R.id.notesDate);
            priority_item = itemView.findViewById(R.id.item_notes_priority);
        }
    }
}
