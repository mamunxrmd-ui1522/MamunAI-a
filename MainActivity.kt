package com.mamunai.assistant
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
class MainActivity : Activity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
  val l=LinearLayout(this).apply { orientation=1; setPadding(32,40,32,32) }
  l.addView(TextView(this).apply { text="Mamun AI"; textSize=26f })
  l.addView(TextView(this).apply { text="এটি প্রাথমিক ডেমো। এখনো লাইভ চার্ট বিশ্লেষণ বা নির্ভরযোগ্য ট্রেডিং সিগন্যাল দেয় না।"; textSize=16f })
  l.addView(Button(this).apply { text="Overlay permission দিন"; setOnClickListener { startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))) } })
  l.addView(Button(this).apply { text="Floating AI চালু করুন"; setOnClickListener { if(Settings.canDrawOverlays(this@MainActivity)) startService(Intent(this@MainActivity,FloatingService::class.java)) else startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))) } })
  setContentView(l)
 }
}