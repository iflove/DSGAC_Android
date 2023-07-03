package com.anroidz.dsgac

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.anroidz.dsgac.databinding.ActivityLogoBinding


class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vb = ActivityLogoBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.apply {
            appVersion.text = resources.getString(R.string.logo_app_name)
            appVersion.text = "V${getVersionName()}"
        }

        Handler(mainLooper) {
            if (isFinishing) {
                return@Handler false
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            true
        }.sendEmptyMessageDelayed(1, 500)
    }

    private fun getVersionName(): String? {
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun getVersionCode(): Int {
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }


}