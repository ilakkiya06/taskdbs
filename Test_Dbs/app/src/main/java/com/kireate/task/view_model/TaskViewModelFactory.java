package com.kireate.task.view_model;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TaskViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public TaskViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new TaskDetailsViewModel(mApplication, mParam);
    }
}
