package com.example.musicademi.ui.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


interface Scope: CoroutineScope {
    var job: Job
    val uiDispatcher: CoroutineDispatcher
    override val coroutineContext: CoroutineContext
        get() = uiDispatcher + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope(){
        job.cancel()
    }

    class Iml(override val uiDispatcher: CoroutineDispatcher) : Scope{
        override lateinit var job: Job
    }
}