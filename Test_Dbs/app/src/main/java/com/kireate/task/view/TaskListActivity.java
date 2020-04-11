package com.kireate.task.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kireate.task.R;
import com.kireate.task.adapter.TaskListAdapter;
import com.kireate.task.adapter.TaskItemClickListener;
import com.kireate.task.response.TaskListResponse;
import com.kireate.task.view_model.TaskListViewModel;

import java.util.ArrayList;


public class TaskListActivity extends AppCompatActivity implements TaskItemClickListener {

    private ArrayList<TaskListResponse> taskResponses = new ArrayList<>();
    TaskListViewModel taskListViewModel;
    RecyclerView recyclerView;

    private ProgressBar progressCircular;
    private LinearLayoutManager layoutManager;
    private TaskListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getTaskList();

    }


    @Override
    public void onListItemClick(int position) {

        Toast.makeText(getApplicationContext(), taskResponses.get(position).getTitle(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(TaskListActivity.this, TaskDetailsActivity.class);
        i.putExtra("id", taskResponses.get(position).getId());
        i.putExtra("title_name", taskResponses.get(position).getTitle());
        i.putExtra("avatar_img", taskResponses.get(position).getAvatar());

        startActivity(i);


    }


    private void init() {
        progressCircular = findViewById(R.id.progress_circular);
        recyclerView = findViewById(R.id.rvTaskList);

        layoutManager = new LinearLayoutManager(TaskListActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        // adapter
        adapter = new TaskListAdapter(TaskListActivity.this, taskResponses, this);
        recyclerView.setAdapter(adapter);


        taskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);
    }

    private void getTaskList() {
        taskListViewModel.getTaskList().observe(this, taskList -> {
                    Toast.makeText(this, "Task list size:" + taskList.size(), Toast.LENGTH_SHORT).show();

                    taskResponses.addAll(taskList);
                    progressCircular.setVisibility(View.GONE);

                    adapter.notifyDataSetChanged();


                }
        );
    }


}
