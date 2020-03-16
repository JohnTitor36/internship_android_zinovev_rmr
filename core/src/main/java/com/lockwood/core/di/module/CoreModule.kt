package com.lockwood.core.di.module

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.qualifier.EncryptedPreferences
import com.lockwood.core.di.qualifier.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: DaggerApplication): Context {
        return application.getApplicationContext()
    }

    @Provides
    @Singleton
    @Preferences
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @EncryptedPreferences
    fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
        return try {
            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
            val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                EncryptedSharedPreferences.create(
                    PREF_FILE_NAME,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            } else {
                context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            }
        } catch (e: NoClassDefFoundError) {
            // API < 23
            context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        }
    }

    companion object {

        private const val PREF_FILE_NAME = "com.lockwood.themoviedb.Preferences"
    }

}