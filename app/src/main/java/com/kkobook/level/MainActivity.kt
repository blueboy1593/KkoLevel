package com.kkobook.level

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.kkobook.level.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainBinding
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        tiltView = findViewById(R.id.tiltView)
        tiltView = TiltView(this)
        binding.tiltView.addView(tiltView)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d("MainActivity", "onChanged: x: ${event.values[0]}, y: ${event.values[1]}, z: ${event.values[2]}")
            tiltView.onSensorEvent(event)
            binding.xText.text = "X: " + event.values[0] * 20 + "º"
            binding.yText.text = "Y: " + event.values[0] * 20 + "º"
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Not yet implemented
    }
}