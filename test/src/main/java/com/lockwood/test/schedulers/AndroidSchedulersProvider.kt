package com.lockwood.test.schedulers

import com.lockwood.core.schedulers.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulersProvider constructor(
    private val io: Scheduler = Schedulers.trampoline(),
    private val mainThread: Scheduler = Schedulers.trampoline()
) : SchedulersProvider {

    override fun io(): Scheduler = io

    override fun mainThread(): Scheduler = mainThread
}