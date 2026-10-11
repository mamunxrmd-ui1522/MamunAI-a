package com.mamunai.assistant
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
class FloatingService: Service() {
 private var panel: LinearLayout?=null
 override fun onBind(intent: Intent?): IBinder?=null
 override fun onStartCommand(intent: Intent?, flags:Int, startId:Int):Int {
  if(panel!=null) return START_NOT_STICKY
  val wm=getSystemService(WINDOW_SERVICE) as WindowManager
  val v=LinearLayout(this).apply { orientation=1; setPadding(16,12,16,12); setBackgroundColor(0xEE202124.toInt()) }
  v.addView(TextView(this).apply { text="Mamun AI • WAIT"; textSize=18f; setTextColor(-1) })
  v.addView(TextView(this).apply { text="ডেমো প্যানেল; লাইভ চার্ট পড়ে না।"; setTextColor(-1) })
  v.addView(Button(this).apply { text="বন্ধ করুন"; setOnClickListener { stopSelf() } })
  val t=if(Build.VERSION.SDK_INT>=26) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else @Suppress("DEPRECATION") WindowManager.LayoutParams.TYPE_PHONE
  val p=WindowManager.LayoutParams(-2,-2,t,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,PixelFormat.TRANSLUCENT).apply { gravity=Gravity.TOP or Gravity.END; x=20; y=180 }
  wm.addView(v,p); panel=v
  return START_NOT_STICKY
 }
 override fun onDestroy() { panel?.let { (getSystemService(WINDOW_SERVICE) as WindowManager).removeView(it) }; panel=null; super.onDestroy() }
}