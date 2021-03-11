package com.knyghenko.deutschvonwiedergabelisten.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.activities.TaskActivity;
import com.knyghenko.deutschvonwiedergabelisten.activities.TasksList;
import com.knyghenko.deutschvonwiedergabelisten.entity.Lesson;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTask;
        private final EditText textViewAnswer;
        private final Button buttonSubmit;
        private final Button buttonHelp;
        private final ImageView imageCheck;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTask = (TextView) itemView.findViewById(R.id.textViewTask);
            textViewAnswer = (EditText) itemView.findViewById(R.id.editTextMyAnswer);
            buttonSubmit = (Button) itemView.findViewById(R.id.buttonSubmit);
            buttonHelp = (Button) itemView.findViewById(R.id.buttonHelp);
            imageCheck=(ImageView)itemView.findViewById(R.id.imageCheck);
        }


        public void bind(String task, String answer) {
            textViewTask.setText(task);
          buttonSubmit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String myAnswer=textViewAnswer.getText().toString();
                  if myAnswer.equals(answer){
                     imageCheck
                             .setVisibility(View.VISIBLE);
                  }
              }
          });


        }
    }
}
