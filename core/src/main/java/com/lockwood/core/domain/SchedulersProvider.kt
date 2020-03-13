package com.lockwood.core.domain

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun io(): Scheduler

    fun mainThread(): Scheduler

}