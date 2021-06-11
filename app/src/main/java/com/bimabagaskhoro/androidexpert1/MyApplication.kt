package com.bimabagaskhoro.androidexpert1

import android.app.Application
import com.bimabagaskhoro.androidexpert1.di.useCaseModule
import com.bimabagaskhoro.androidexpert1.di.viewModelModule
import com.bimabagaskhoro.submission.core.di.databaseModule
import com.bimabagaskhoro.submission.core.di.networkModule
import com.bimabagaskhoro.submission.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}