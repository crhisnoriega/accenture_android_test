package com.imdb.core.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class FlowUseCase<P, R> {

    private val _trigger = MutableStateFlow(true)

    val resultFlow: Flow<R> = _trigger.flatMapLatest {
        performAction(this.params)
    }

    suspend fun launch(params: P) {
        this.params = params
        _trigger.emit(!(_trigger.value))
    }

    private var params: P? = null

    protected abstract suspend fun performAction(params: P?): Flow<R>
}