package com.naman.ezobooksassignmentfetchapi.repository

import com.naman.ezobooksassignmentfetchapi.api.RetrofitInstance
import com.naman.ezobooksassignmentfetchapi.model.APIResult

class ItemsRepository {
    private val apiservice = RetrofitInstance.apiService

    suspend fun getAllItems(): APIResult{
        return apiservice.getItemTasks()
    }
}