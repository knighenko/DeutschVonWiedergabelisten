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
    private final List<String> lessons = new ArrayList<String>();
    private OnLessonClickListener onLessonClickListener;


   public LessonAdapter(OnLessonClickListener onLessonClickListener){
       this.onLessonClickListener=onLessonClickListener;
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

    public void setListLessons(Collection<String> lessons) {
        this.lessons.addAll(lessons);
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleLesson;
        private final ImageView imageViewLesson;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLesson = (TextView) itemView.findViewById(R.id.lessonTitleTextView);
            imageViewLesson = (ImageView) itemView.findViewById(R.id.imageLesson);
        }

        public void bind(String title) {
            titleLesson.setText(title);
            //  titleLesson.setPaintFlags(titleLesson.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            imageViewLesson.setImageResource(R.drawable.lesson);
            imageViewLesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url=lessons.get(getLayoutPosition());
                    onLessonClickListener.onLessonClick(url);
                }
            });
        }
    }

    public interface OnLessonClickListener {
        void onLessonClick(String url);
    }
}
