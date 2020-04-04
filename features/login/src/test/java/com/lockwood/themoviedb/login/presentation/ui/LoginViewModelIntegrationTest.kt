package com.lockwood.themoviedb.login.presentation.ui

import com.lockwood.test.extensions.disableTestMode
import com.lockwood.test.extensions.enableTestMode
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginViewModelIntegrationTest : Spek({

    //region Setup
    beforeGroup { enableTestMode() }
    afterGroup { disableTestMode() }
    //endregion

    Feature("Login") {

    }

})