package com.knyghenko.deutschvonwiedergabelisten.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.activities.TasksList;
import com.knyghenko.deutschvonwiedergabelisten.entity.Lesson;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder>{


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

         ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    } public void bind(Lesson lesson) {
        titleLesson.setText(lesson.getTitle());
        imageViewLesson.setImageResource(R.drawable.lesson);
        imageDownload.setImageResource(R.drawable.download);
        imageViewLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tasks = ConnectServer.connectToServerSearch("3:" + lesson.getId());
                Intent intent = new Intent(itemView.getContext(), TasksList.class);
                intent.putExtra("tasks", tasks);
                intent.putExtra("lessonTitle", lesson.getTitle());
                itemView.getContext().startActivity(intent);
            }
        });
        imageYouTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = lesson.getYouTubeUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                itemView.getContext().startActivity(intent);
            }
        });
        imageDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLessonClickListener.onAdvClick(lesson);

            }
        });
    }
}
