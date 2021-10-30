package ai.andromeda.mlstarter.api.vision

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ObjectDetectionApi {

    @FormUrlEncoded
    @POST("/yolo/v5/")
    suspend fun analyzeImage(
        @Field("image") image: String
    ): String?

}