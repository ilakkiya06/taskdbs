package com.kireate.task.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kireate.task.response.TaskDetailsResponse;
import com.kireate.task.retrofit.ApiRequest;
import com.kireate.task.retrofit.RetrofitRequest;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailsRepository {

    private static final String TAG = TaskDetailsRepository.class.getSimpleName();

    private ApiRequest apiRequest;

    public TaskDetailsRepository() {

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

    }

    public LiveData<TaskDetailsResponse> getTaskDetails(int id) {

        Log.d(TAG,"task_id:: " + id);

        final MutableLiveData<TaskDetailsResponse> data = new MutableLiveData<>();
        apiRequest.getTaskDetailsById(id).enqueue(new Callback<TaskDetailsResponse>() {
            @Override
            public void onResponse(Call<TaskDetailsResponse> call, Response<TaskDetailsResponse> response) {

                Log.d(TAG, "on Response:: " + response);

                if (response.body() != null) {

                    data.setValue(response.body());

                    Log.d(TAG, "task_description:: " + response.body().getText());
                    Log.d(TAG, "task_id:: " + response.body().getId());

                }

                else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<TaskDetailsResponse> call, Throwable t) {

                data.setValue(null);
            }
        });

        return data;
    }
}
