package com.takashi.android_libs.utils

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageConverter{
    companion object {
        fun convertToBase64(image: Bitmap): String{
            val bos = ByteArrayOutputStream()
            image.compress(CompressFormat.JPEG, 100, bos)
            return bos.toByteArray().let {
                Base64.encodeToString(it, Base64.DEFAULT)
            }
        }
    }
}
