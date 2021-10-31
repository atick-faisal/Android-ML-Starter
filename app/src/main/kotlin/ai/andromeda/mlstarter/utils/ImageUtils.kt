package ai.andromeda.mlstarter.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.orhanobut.logger.Logger
import java.io.ByteArrayOutputStream

fun convertBitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(
        Bitmap.CompressFormat.PNG,
        100,
        byteArrayOutputStream
    )
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun convertBase64ToBitmap(image: String): Bitmap? {
    return try {
        val decodedString = Base64.decode(image, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } catch (e: Exception) {
        Logger.e(e.toString())
        null
    }
}