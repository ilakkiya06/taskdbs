package com.kireate.task.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kireate.task.response.TaskListResponse;
import com.kireate.task.retrofit.ApiRequest;
import com.kireate.task.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {

    private static final String TAG = TaskRepository.class.getSimpleName();

    private ApiRequest apiRequest;


    public TaskRepository() {

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

    }


    public LiveData<List<TaskListResponse>> getTaskList() {

        final MutableLiveData<List<TaskListResponse>> data = new MutableLiveData<>();
        apiRequest.getTaskList().enqueue(new Callback<List<TaskListResponse>>() {
            @Override
            public void onResponse(Call<List<TaskListResponse>> call, Response<List<TaskListResponse>> response) {

                Log.d(TAG, "on Response:: " + response);

                if (response.body() != null) {

                    data.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<TaskListResponse>> call, Throwable t) {

                Log.e(TAG, "network_error ::" + t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

}
