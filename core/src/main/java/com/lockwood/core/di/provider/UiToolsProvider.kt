package com.lockwood.core.di.provider

import com.lockwood.core.snackbar.SnackbarMaker

interface UiToolsProvider {

    fun provideSnackbarMaker(): SnackbarMaker

}