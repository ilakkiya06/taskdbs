package com.kireate.task.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kireate.task.repository.TaskDetailsRepository;
import com.kireate.task.response.TaskDetailsResponse;

public class TaskDetailsViewModel extends AndroidViewModel {

    private TaskDetailsRepository taskDetailsRepository;


    private LiveData<TaskDetailsResponse> taskDetailsResponseLiveData;



    public TaskDetailsViewModel(@NonNull Application application, String param) {
        super(application);

        taskDetailsRepository = new TaskDetailsRepository();
        this.taskDetailsResponseLiveData = taskDetailsRepository.getTaskDetails(Integer.parseInt(param));
    }


    public LiveData<TaskDetailsResponse> getTaskDetails() {

        return taskDetailsResponseLiveData;
    }


}
