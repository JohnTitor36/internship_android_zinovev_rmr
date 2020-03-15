package com.lockwood.themoviedb.user.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.user.presentation.ui.UserFragment
import dagger.Component

@Component
@FeatureScope
interface UserComponent : BaseFragmentComponent<UserFragment> {

    @Component.Builder
    interface Builder {

        fun build(): UserComponent

    }

}