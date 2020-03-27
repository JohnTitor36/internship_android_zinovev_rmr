package com.lockwood.test.extensions

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.dsl.Root

@SuppressLint("RestrictedApi")
fun Root.enableTestMode() {
    ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
        override fun postToMainThread(runnable: Runnable) = runnable.run()
        override fun isMainThread(): Boolean = true
    })
}

@SuppressLint("RestrictedApi")
fun Root.disableTestMode() {
    ArchTaskExecutor.getInstance().setDelegate(null)
}