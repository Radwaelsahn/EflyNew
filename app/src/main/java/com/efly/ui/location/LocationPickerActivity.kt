package com.efly.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsClient
import com.efly.R
import com.efly.utils.ACTION_APPLICATION_DETAILS_SETTINGS_CODE
import com.efly.utils.ActivityUtil
import com.efly.utils.DEFAULT_LAT
import com.efly.utils.DEFAULT_LNG
import com.facebook.FacebookSdk
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import timber.log.Timber
import java.util.*

class LocationPickerActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_picker)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(DEFAULT_LAT, DEFAULT_LNG)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMapClickListener { latLng ->
            // Creating a marker
            var markerOptions = MarkerOptions()

            // Setting the position for the marker
            markerOptions.position(latLng)

            // Setting the title for the marker.
            // This will be displayed on taping the marker
            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)

            // Clears the previously touched position
            googleMap.clear();

            // Animating to the touched position
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            // Placing a marker on the touched position
            googleMap.addMarker(markerOptions)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            LocationRequest.PRIORITY_HIGH_ACCURACY ->
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        Timber.d("onActivityResult: GPS Enabled by user")
                        Handler().postDelayed({
                            try {
                                requestLocationPermissions()

                            } catch (ignored: Exception) {
                                ignored.printStackTrace()
                            }
                        }, 2000)
                    }
                    Activity.RESULT_CANCELED ->
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(
                            FacebookSdk.getApplicationContext(),
                            "GPS required to show you near by heroes ",
                            Toast.LENGTH_LONG
                        ).show()
                }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun requestLocationPermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        try {
                            if (ActivityUtil.checkGPS(this@LocationPickerActivity)) {
                                val mFusedLocationClient =
                                    LocationServices.getFusedLocationProviderClient(this@LocationPickerActivity)
                                mFusedLocationClient.lastLocation
                                    .addOnSuccessListener { location ->
                                        // GPS location can be null if GPS is switched off
                                        if (location != null) {
                                            val currentLatLng =
                                                LatLng(location.latitude, location.longitude)
                                            mMap.addMarker(MarkerOptions().position(currentLatLng))
                                            mMap.moveCamera(
                                                CameraUpdateFactory.newLatLng(
                                                    currentLatLng
                                                )
                                            )
                                            mMap.animateCamera(
                                                CameraUpdateFactory.newLatLngZoom(
                                                    currentLatLng
                                                    , 14.toFloat()
                                                )
                                            )
                                        } else
                                            Handler().postDelayed({
                                                try {
                                                    requestLocationPermissions()

                                                } catch (ignored: Exception) {
                                                    ignored.printStackTrace()
                                                }
                                            }, 2000)
                                    }
                                    .addOnFailureListener { e ->
                                        e.printStackTrace()
                                    }
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(
                    FacebookSdk.getApplicationContext(),
                    "Error occurred! ",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            .onSameThread()
            .check()
    }

    fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton("Cancel", { dialog, which ->
            dialog.cancel()
        })
        builder.show()

    }

    fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", CustomTabsClient.getPackageName(this, null), null)
        intent.data = uri
        startActivityForResult(intent, ACTION_APPLICATION_DETAILS_SETTINGS_CODE)
    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        // Add a marker in Sydney, Australia, and move the camera.
//
//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            val from = 2000
//            val to = 600
//            val date = Date()
//            val c = Calendar.getInstance()
//            c.time = date
//            val t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE)
//            val isBetween =
//                to > from && t >= from && t <= to || to < from && (t >= from || t <= to)
//
//            var success = false
//
//            if (isBetween)
//                success = googleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                        activity, R.raw.map_night_style
//                    )
//                )
//
//            if (!success) {
//                Timber.d("Style parsing failed.")
//            }
//        } catch (e: Resources.NotFoundException) {
//            Timber.d("e ->  ${e.message}")
//        }
//        requestLocationPermissions()
//    }
}
