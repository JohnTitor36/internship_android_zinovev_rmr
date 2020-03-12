package com.lockwood.core.di.component

import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }

}