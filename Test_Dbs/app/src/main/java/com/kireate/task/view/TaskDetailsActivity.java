package com.kireate.task.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kireate.task.R;
import com.kireate.task.view_model.TaskDetailsViewModel;
import com.kireate.task.view_model.TaskViewModelFactory;

public class TaskDetailsActivity extends AppCompatActivity {
    private static final String TAG = TaskDetailsActivity.class.getSimpleName();

    AppCompatTextView tvTitle, tvDescription;
    AppCompatImageView imageView;
    private ProgressBar progressCircular;
    TaskDetailsViewModel taskDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        init();
        getTaskDetails();

    }

    private void init() {
        tvTitle = findViewById(R.id.tvTitle);
        imageView = findViewById(R.id.imageView);
        tvDescription = findViewById(R.id.tvDescription);
        progressCircular = findViewById(R.id.progress_circular);

        Intent intent = getIntent();

        String message = intent.getStringExtra("title_name");
        tvTitle.setText(message);

        String avatarImage = intent.getStringExtra("avatar_img");
        Glide.with(getApplicationContext())
                .load(avatarImage)
                .into(imageView);


        int id = intent.getIntExtra("id", 1);

        Log.d(TAG, "task_id" + id);

        taskDetailsViewModel = ViewModelProviders.of(this, new TaskViewModelFactory(this.getApplication(), String.valueOf(id))).get(TaskDetailsViewModel.class);

    }

    private void getTaskDetails() {
        taskDetailsViewModel.getTaskDetails().observe(this, taskDetailsResponse ->
        {

            if (taskDetailsResponse != null) {
                tvDescription.setText(taskDetailsResponse.getText());
                progressCircular.setVisibility(View.GONE);

            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(TaskDetailsActivity.this);
                alert.setTitle("Task Details");
                alert.setMessage("Unable to fetch the task details at the moments");
                alert.setPositiveButton("OK", null);
                alert.show();
                progressCircular.setVisibility(View.GONE);
            }
        });
    }
}
