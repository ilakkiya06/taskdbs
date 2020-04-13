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
import com.kireate.task.response.TaskDetailsResponse;
import com.kireate.task.view_model.TaskDetailsViewModel;
import com.kireate.task.view_model.TaskViewModelFactory;

public class TaskDetailsActivity extends AppCompatActivity {
    private static final String TAG = TaskDetailsActivity.class.getSimpleName();

    AppCompatTextView tvTitle, tvDescription;
    AppCompatImageView imageView,editImageView;
    private ProgressBar progressCircular;
    TaskDetailsViewModel taskDetailsViewModel;
    TaskDetailsResponse taskDetails;
    String title = "";

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
        editImageView = findViewById(R.id.edit_imageView);

        Intent intent = getIntent();

        title = intent.getStringExtra("title_name");
        tvTitle.setText(title);

        String avatarImage = intent.getStringExtra("avatar_img");
        Glide.with(getApplicationContext())
                .load(avatarImage)
                .into(imageView);


        int id = intent.getIntExtra("id", 1);

        Log.d(TAG, "task_id" + id);

        taskDetailsViewModel = ViewModelProviders.of(this, new TaskViewModelFactory(this.getApplication(), String.valueOf(id))).get(TaskDetailsViewModel.class);




        editImageView.setOnClickListener(v -> {

                Intent i = new Intent(TaskDetailsActivity.this, TaskEditActivity.class);
                i.putExtra("id", taskDetails.getId());
                i.putExtra("title", title);
                i.putExtra("description", taskDetails.getText());

                startActivity(i);

        });
    }

    private void getTaskDetails() {
        taskDetailsViewModel.getTaskDetails().observe(this, taskDetailsResponse ->
        {

            if (taskDetailsResponse != null) {
                taskDetails = new TaskDetailsResponse();
                taskDetails.setId(taskDetailsResponse.getId());
                taskDetails.setText(taskDetailsResponse.getText());
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
