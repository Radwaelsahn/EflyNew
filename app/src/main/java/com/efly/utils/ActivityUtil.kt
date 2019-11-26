package com.efly.utils

import android.app.Activity
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.efly.MyApplication
import com.efly.models.Address
import com.efly.models.CustomAttribute
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import java.util.*

class ActivityUtil {
    companion object {
        fun setFullScreen(activity: Activity) {
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        fun hideInputType(activity: Activity) {
            if (activity.getCurrentFocus() != null) {
                val inputMethodManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
                )
            }
        }

        fun checkGPS(mContext: Context): Boolean {
            val lm = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)

            if (!isGpsEnabled) {
                askforGPSPermission(mContext)
                return false
            }
            return true
        }

        private fun askforGPSPermission(context: Context) {

            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)


            val result =
                LocationServices.getSettingsClient(context).checkLocationSettings(builder.build())



            result.addOnCompleteListener { task ->
                try {
                    task.getResult(ApiException::class.java)
                    // All location settings are satisfied. The client can initialize location
                    // requests here.
                } catch (exception: ApiException) {
                    when (exception.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                val resolvable = exception as ResolvableApiException
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                resolvable.startResolutionForResult(
                                    context as Activity?,
                                    LocationRequest.PRIORITY_HIGH_ACCURACY
                                )
                            } catch (e: Exception) {
                                // Ignore the error.
                            }
                    }

                }
            }
        }

         fun getAddressFromLocation(
            latitude: Double,
            longitude: Double
        ): Address {
            val address = Address()
            val geocoder: Geocoder
            val streets = ArrayList<String>()

            val cusAtt = ArrayList<CustomAttribute>()

            try {

                cusAtt.add(CustomAttribute("longitude", longitude.toString() + ""))
                cusAtt.add(CustomAttribute("latitude", latitude.toString() + ""))

                geocoder = Geocoder(MyApplication.context, Locale.getDefault())

                val addressesStr = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1
                ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                val addressText =
                    addressesStr[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                val city = addressesStr[0].locality
                val state = addressesStr[0].adminArea
                val country = addressesStr[0].countryName
                val postalCode = addressesStr[0].postalCode
                val knownName = addressesStr[0].featureName

                streets.add(addressText)
                address.street = streets
                address.city = city
                address.postcode = postalCode

            } catch (e: Exception) {
                cusAtt.add(CustomAttribute("longitude", 0.0.toString() + ""))
                cusAtt.add(CustomAttribute("latitude", 0.0.toString() + ""))
                streets.add("street")
                address.street = streets
                address.city = "city"
            }

            address.custom_attributes = cusAtt

            return address


        }



    }
}