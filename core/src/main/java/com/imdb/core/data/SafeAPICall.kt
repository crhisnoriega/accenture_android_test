package com.imdb.core.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

suspend inline fun <T> safeAPICallLiveData(
    crossinline request: suspend () -> Flow<T>,
): Flow<Resources> = flow {
    request.invoke()
        .flowOn(Dispatchers.IO)
        .catch { throwable ->
            when (throwable) {
                is HttpException -> {
                    when (throwable.code()) {
                        400, 401 -> emit(Resources.Error(throwable))
                    }
                }
            }

        }.collect {
            emit(Resources.Success<T>(it))
        }
}