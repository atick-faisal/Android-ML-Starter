package ai.andromeda.mlstarter.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import java.util.concurrent.TimeUnit.SECONDS

@Module(
    includes = [
        InterceptorModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {

    private const val TIME_OUT = 60L

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, SECONDS)
            .readTimeout(TIME_OUT, SECONDS)
            .writeTimeout(TIME_OUT, SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

}