package com.knyghenko.deutschvonwiedergabelisten.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.knyghenko.deutschvonwiedergabelisten.entity.Task;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private final List<Task> tasks = new ArrayList<Task>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tasks.get(holder.getAdapterPosition()));
    }


    @Override
    public int getItemCount() {
        return tasks.size();

    }

    public void setListTasks(Collection<Task> tasks) {
        this.tasks.addAll(tasks);
        notifyDataSetChanged();
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
            imageCheck = (ImageView) itemView.findViewById(R.id.imageCheck);
        }


        public void bind(Task task) {

            textViewTask.setText(task.getRus());
            textViewAnswer.setText("");
            buttonHelp.setVisibility(View.INVISIBLE);
            if (task.isChecks()) {
                imageCheck
                        .setVisibility(View.VISIBLE);
            } else imageCheck
                    .setVisibility(View.INVISIBLE);

            buttonSubmit.setOnClickListener(new View.OnClickListener() {
                int count = 0;


                @Override
                public void onClick(View view) {
                    count++;
                    String myAnswer = textViewAnswer.getText().toString();
                    if (count > 2) {
                        buttonHelp.setVisibility(View.VISIBLE);
                        count = 0;
                    }
                    ;
                    if (myAnswer.equals(task.getDeu())) {
                        imageCheck
                                .setVisibility(View.VISIBLE);
                        count = 0;
                        ConnectServer.connectToServerSearch("5:" + e_mail+":"+task.getId());
                        buttonHelp.setVisibility(View.INVISIBLE);
                    } else {
                        imageCheck
                                .setVisibility(View.INVISIBLE);
                    }

                }
            });
            buttonHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textViewAnswer.setText(task.getDeu());
                }
            });


        }
    }
}
