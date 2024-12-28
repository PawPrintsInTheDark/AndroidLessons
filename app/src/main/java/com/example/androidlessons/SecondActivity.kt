package com.example.androidlessons

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidlessons.databinding.ActivitySecondBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.location.Purpose
import com.yandex.mapkit.location.SubscriptionSettings
import com.yandex.mapkit.location.UseInBackground
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setApiKey(savedInstanceState)
        MapKitFactory.initialize(this)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapObjectCollection = binding.mapView.map.mapObjects

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            requestSingleLocationUpdate()
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestSingleLocationUpdate()
            } else {
                Log.e("Permission", "Разрешение на доступ к местоположению отклонено")
            }
        }
    }


    fun createBitmapFromVector(art: Int): Bitmap?{
        val drawable = ContextCompat.getDrawable(this,art)?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0,0, canvas.width,canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun requestSingleLocationUpdate() {
        val locationManager = MapKitFactory.getInstance().createLocationManager()

        locationListener = object : LocationListener {
            override fun onLocationUpdated(location: com.yandex.mapkit.location.Location) {
                Log.d("LocationUpdate", "Location updated: ${location.position.latitude}, ${location.position.longitude}")
val marker =createBitmapFromVector(R.drawable.ic_black_pin)
                if (::placemarkMapObject.isInitialized) {
                    mapObjectCollection.remove(placemarkMapObject)
                }

                val currentLocation = Point(location.position.latitude, location.position.longitude)
                placemarkMapObject = mapObjectCollection.addPlacemark(
                    currentLocation,
                        ImageProvider.fromBitmap(marker)
                )
                placemarkMapObject.opacity = 0.5f

                binding.mapView.map.move(
                    CameraPosition(currentLocation, 16.5f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 5f),
                    null
                )
            }

            override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
                Log.e("LocationStatus", locationStatus.toString())
            }
        }

        val subscriptionSettings = SubscriptionSettings(
            UseInBackground.ALLOW,
            Purpose.GENERAL,
        )

        locationManager.subscribeForLocationUpdates(subscriptionSettings, locationListener)
    }

    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey") ?: false
        if (!haveApiKey) MapKitFactory.setApiKey(Utils.MAPKIT_API_KEY)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        val locationManager = MapKitFactory.getInstance().createLocationManager()
        locationManager.unsubscribe(locationListener)
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}
