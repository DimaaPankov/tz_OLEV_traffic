package olev.traffic.tz

import android.os.Build
import android.text.TextUtils

class ModelPhone {
    companion object {
        fun getPhoneModel(): String? {

            val MANUFACTURER = Build.MANUFACTURER
            val MODEL = Build.MODEL
            return if (MODEL.startsWith(MANUFACTURER)) {

                workChar(MODEL)
            } else workChar(MANUFACTURER) + " " + MODEL
        }

        private fun workChar(string: String): String {
            if (TextUtils.isEmpty(string)) {


                return string
            }
            val chars = string.toCharArray()
            var dohave = true
            val stringBuilder = StringBuilder()
            for (char in chars) {
                if (dohave && Character.isLetter(char)) {
                    stringBuilder.append(char)
                    dohave = false
                    continue
                } else if (Character.isWhitespace(char)) {
                    dohave = true
                }
                stringBuilder.append(char)
            }
            return stringBuilder.toString()
        }
    }

}