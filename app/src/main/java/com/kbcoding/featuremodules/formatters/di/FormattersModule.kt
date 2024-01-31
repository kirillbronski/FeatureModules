package com.kbcoding.featuremodules.formatters.di

import com.kbcoding.featuremodules.formatters.PriceFormatter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.featuremodules.formatters.DateTimeFormatter
import com.kbcoding.featuremodules.formatters.DefaultDateTimeFormatter
import com.kbcoding.featuremodules.formatters.DefaultPriceFormatter

@Module
@InstallIn(SingletonComponent::class)
interface FormattersModule {

    @Binds
    fun bindDateTimeFormatter(
        formatter: DefaultDateTimeFormatter
    ): DateTimeFormatter

    @Binds
    fun bindPriceFormatter(
        formatter: DefaultPriceFormatter
    ): PriceFormatter
}