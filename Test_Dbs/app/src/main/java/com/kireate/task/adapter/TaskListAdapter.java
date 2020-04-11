package com.kireate.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kireate.task.R;
import com.kireate.task.response.TaskListResponse;

import java.util.ArrayList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {


    private Context ctx;
    private ArrayList<TaskListResponse> taskListResponses;
     private TaskItemClickListener onClickListener;


    public TaskListAdapter(Context ctx, ArrayList<TaskListResponse> taskListResponses, TaskItemClickListener listener) {
        this.ctx = ctx;
        this.taskListResponses = taskListResponses;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.ViewHolder viewHolder, int position) {

        TaskListResponse response = taskListResponses.get(position);

        viewHolder.tvTitle.setText(response.getTitle());
        viewHolder.tvDate.setText(String.valueOf(response.getLastUpdate()));
        viewHolder.tvDescription.setText(response.getShortDescription());
        Glide.with(ctx)
                .load(response.getAvatar())
                .into(viewHolder.imgViewCover);
        viewHolder.itemView.setOnClickListener(v -> onClickListener.onListItemClick(position));

    }


    @Override
    public int getItemCount() {
        return taskListResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatImageView imgViewCover;
        private final AppCompatTextView tvTitle;
        private final AppCompatTextView tvDate;
        private final AppCompatTextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewCover = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
