package ai.andromeda.mlstarter.di.network

import ai.andromeda.mlstarter.api.vision.ObjectDetectionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object RestServiceModule {

    @Provides
    @Singleton
    fun provideObjectDetectionApi(retrofit: Retrofit): ObjectDetectionApi {
        return retrofit.create(ObjectDetectionApi::class.java)
    }

}