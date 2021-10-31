package ai.andromeda.mlstarter.api.vision

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ObjectDetectionApi {

    @GET("yolo/v5")
    suspend fun checkServer(): String?

    @FormUrlEncoded
    @POST("yolo/v5")
    suspend fun analyzeImage(
        @Field("image") image: String
    ): String?

}