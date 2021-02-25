package com.knyghenko.deutschvonwiedergabelisten.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knyghenko.deutschvonwiedergabelisten.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    private List<String> lessons = new ArrayList<String>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item_view, parent);
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
    public void setListLessons(Collection<String> lessons) {
        this.lessons.addAll(lessons);
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleLesson;
        private ImageView imageViewLesson;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLesson = (TextView) itemView.findViewById(R.id.lessonTitleTextView);
            imageViewLesson = (ImageView) itemView.findViewById(R.id.imageLesson);
        }

        public void bind(String title) {
            titleLesson.setText(title);
            titleLesson.setPaintFlags(titleLesson.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            imageViewLesson.setImageResource(R.drawable.lesson);
        }
    }
}
