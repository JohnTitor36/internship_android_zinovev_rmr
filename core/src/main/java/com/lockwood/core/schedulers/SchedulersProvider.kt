package com.lockwood.core.schedulers

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun io(): Scheduler

    fun mainThread(): Scheduler

}