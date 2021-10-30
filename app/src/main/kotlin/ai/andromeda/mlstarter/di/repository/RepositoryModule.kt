package ai.andromeda.mlstarter.di.repository

import ai.andromeda.mlstarter.api.vision.ObjectDetectionApiImpl
import ai.andromeda.mlstarter.repository.vision.ObjectDetectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindObjectDetectionRepository(
        objectDetectionApiImpl: ObjectDetectionApiImpl
    ): ObjectDetectionRepository

}