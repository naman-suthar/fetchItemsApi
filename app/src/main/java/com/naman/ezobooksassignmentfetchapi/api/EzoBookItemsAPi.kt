package com.naman.ezobooksassignmentfetchapi.api

import com.naman.ezobooksassignmentfetchapi.model.APIResult
import retrofit2.http.GET

interface EzoBookItemsAPi {
    @GET("kappa/image/task")
    suspend fun getItemTasks(): APIResult
}