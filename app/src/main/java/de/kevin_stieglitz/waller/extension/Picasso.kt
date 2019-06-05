package de.kevin_stieglitz.waller.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*

/**
 * Converts LiveData into a Flowable
 */
fun Context.cachedPicassoBitmap(url: String?): Bitmap? {
    if (url == null) return null

    val cachePath = this.cacheDir.absolutePath + "/picasso-cache/"

    val files = File(cachePath).listFiles()
    for (file in files) {
        val fname = file.getName()
        if (fname.contains(".") && fname.substring(fname.lastIndexOf(".")) == ".0") {
            try {
                val br = BufferedReader(FileReader(file) as Reader)
                if (br.readLine().equals(url)) {
                    val image_path = cachePath + fname.replace(".0", ".1")
                    if (File(image_path).exists()) {
                        return BitmapFactory.decodeFile(image_path)
                    }
                }
            } catch (e: FileNotFoundException) {
            } catch (e: IOException) {
            }

        }
    }
    return null
}
