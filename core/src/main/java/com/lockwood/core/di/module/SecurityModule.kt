package com.lockwood.core.di.module

import android.content.Context
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SecurityModule {

    companion object {

        private const val PREF_FILE_NAME = "the_movie_db_pref"
        private const val TINK_KEYSET_NAME = "the_movie_db_keyset"
        private const val MASTER_KEY_URI = "android-keystore://the_movie_db_master_key"
    }

    @Provides
    @Singleton
    fun provideRootBeer(context: Context): RootBeer {
        return RootBeer(context)
    }

    @Provides
    @Singleton
    fun provideKeysetHandle(context: Context): KeysetHandle {
        return AndroidKeysetManager.Builder()
            .withSharedPref(context, TINK_KEYSET_NAME, PREF_FILE_NAME)
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle
    }

}