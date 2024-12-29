package com.example.androidlessons

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidlessons.Utils.ZOOM_BOUDARY
import com.example.androidlessons.databinding.ActivitySecondBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.location.Purpose
import com.yandex.mapkit.location.SubscriptionSettings
import com.yandex.mapkit.location.UseInBackground
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.places.panorama.PanoramaService.SearchSession
import com.yandex.mapkit.search.Address
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.mapkit.search.ToponymObjectMetadata
import com.yandex.mapkit.traffic.TrafficLayer
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider

class SecondActivity : AppCompatActivity(), CameraListener {

    lateinit var searchManager: SearchManager
    lateinit var searchSession: Session


    private lateinit var binding: ActivitySecondBinding
    private lateinit var trafficLayer: TrafficLayer
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject
    private lateinit var fLocationClient: FusedLocationProviderClient
    private val tapListener = GeoObjectTapListener { geoObjectTapEvent ->
        val selectionMetaData: GeoObjectSelectionMetadata = geoObjectTapEvent
            .geoObject
            .metadataContainer
            .getItem(GeoObjectSelectionMetadata::class.java)
        binding.mapView.mapWindow.map.selectGeoObject(selectionMetaData)
        false
    }
    private var zoomValue = 16.5f

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setApiKey(savedInstanceState)
        MapKitFactory.initialize(this)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapObjectCollection = binding.mapView.map.mapObjects
        requestLocation()
        binding.mapView.map.addCameraListener(this)
        binding.mapView.map.addTapListener(tapListener)
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)
        binding.mapView.mapWindow.map.addInputListener(inputListener)

        trafficLayer = MapKitFactory.getInstance().createTrafficLayer(binding.mapView.mapWindow)

        binding.trafficButton.setOnClickListener {
            if (trafficLayer.isTrafficVisible) {
                trafficLayer.isTrafficVisible = false
                Toast.makeText(this, "Пробки отключены", Toast.LENGTH_SHORT).show()
            } else {
                trafficLayer.isTrafficVisible = true
                Toast.makeText(this, "Пробки включены", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
            return
        }
        fLocationClient = LocationServices.getFusedLocationProviderClient(baseContext)
        fLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        )
            .addOnCompleteListener {
                updateMapLocation(it.result)
            }
    }

    private fun updateMapLocation(location: Location) {
        if (::placemarkMapObject.isInitialized) {
            mapObjectCollection.remove(placemarkMapObject)
        }
        val moscowPoint = Point(55.7558, 37.6173)
        val currentLocation = moscowPoint
        placemarkMapObject = mapObjectCollection.addPlacemark(
            currentLocation,
            ImageProvider.fromBitmap(createBitmapFromVector(R.drawable.ic_black_pin))
        )
        placemarkMapObject.opacity = 0.5f

        binding.mapView.mapWindow.map.move(
            CameraPosition(currentLocation, 16.5f, 0.0f, 0.0f),
            Animation(Animation.Type.LINEAR, 5f),
            null
        )
    }

    private fun requestPermissions() {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perms ->
            if (perms.values.all { it }) {
                requestLocation()
            } else {
                Toast.makeText(this, "Разрешение отклонено.", Toast.LENGTH_SHORT).show()
            }
        }.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun createBitmapFromVector(art: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(this, art) ?: return null
        return Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        ).apply {
            val canvas = Canvas(this)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
        }
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
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onCameraPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        cameraUpdateReason: CameraUpdateReason,
        finished: Boolean
    ) {
        if (finished) {
            when {
                cameraPosition.zoom >= ZOOM_BOUDARY && zoomValue <= ZOOM_BOUDARY -> {
                    placemarkMapObject.setIcon(ImageProvider.fromBitmap(createBitmapFromVector(R.drawable.ic_red_pin)))
                }

                cameraPosition.zoom <= ZOOM_BOUDARY && zoomValue >= ZOOM_BOUDARY -> {
                    placemarkMapObject.setIcon(ImageProvider.fromBitmap(createBitmapFromVector(R.drawable.ic_blue_pin)))

                }
            }
            zoomValue = cameraPosition.zoom
        }
    }

    private val searchListener = object : Session.SearchListener {
        override fun onSearchResponse(response: Response) {
            val street = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull() { it.kinds.contains(Address.Component.Kind.STREET) }
                ?.name ?: "Информация не найдена"

            Toast.makeText(applicationContext, street, Toast.LENGTH_SHORT).show()
        }

        override fun onSearchError(p0: Error) {}

    }

    private val inputListener = object : InputListener {
        override fun onMapTap(map: Map, point: Point) {
            searchSession = searchManager.submit(point,20, SearchOptions(),searchListener)
        }

        override fun onMapLongTap(p0: Map, p1: Point) {}

    }

}
