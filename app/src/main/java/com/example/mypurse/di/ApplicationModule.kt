package com.example.mypurse.di

import android.content.Context
import com.example.mypurse.data.AppDatabase
import com.example.mypurse.data.TransactionDao
import com.example.mypurse.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTransactionDao(db: AppDatabase) = db.transactionDao()

    @Singleton
    @Provides
    fun provideRepository(localDataSource: TransactionDao) = TransactionRepository(localDataSource)
}