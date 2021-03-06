package com.knyghenko.deutschvonwiedergabelisten.adapter;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knyghenko.deutschvonwiedergabelisten.R;

import com.knyghenko.deutschvonwiedergabelisten.activities.TaskActivity;
import com.knyghenko.deutschvonwiedergabelisten.activities.TasksList;
import com.knyghenko.deutschvonwiedergabelisten.entity.Constants;
import com.knyghenko.deutschvonwiedergabelisten.entity.Lesson;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectToDownloadServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    private final List<Lesson> lessons = new ArrayList<Lesson>();
    private OnLessonClickListener onLessonClickListener;

    public LessonAdapter(OnLessonClickListener onLessonClickListener) {
        this.onLessonClickListener = onLessonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lessons.get(position));
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public void setListLessons(Collection<Lesson> lessons) {
        this.lessons.addAll(lessons);
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleLesson;
        private final ImageView imageViewLesson;
        private final ImageView imageDownload;
        private final ImageView imageYouTube;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLesson = (TextView) itemView.findViewById(R.id.lessonTitleTextView);
            imageViewLesson = (ImageView) itemView.findViewById(R.id.imageLesson);
            imageDownload = (ImageView) itemView.findViewById(R.id.imageDownload);
            imageYouTube = (ImageView) itemView.findViewById(R.id.imageYouTube);
        }

        public void bind(Lesson lesson) {
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

    public interface OnLessonClickListener {
        void onAdvClick(Lesson lesson);
    }

}
