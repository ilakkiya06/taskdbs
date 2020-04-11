package com.kireate.task.retrofit;

import com.kireate.task.response.TaskListResponse;
import com.kireate.task.response.TaskDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiRequest {

    @GET("/article")
    Call<List<TaskListResponse>> getTaskList(

    );

    @GET("/article/{id}")
    Call<TaskDetailsResponse> getTaskDetailsById(

            @Path("id") int id

    );
}
