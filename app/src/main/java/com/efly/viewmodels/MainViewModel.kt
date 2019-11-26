package com.efly.viewmodels

import android.content.Intent
import android.location.Geocoder
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.efly.MyApplication.Companion.appComponent
import com.efly.MyApplication.Companion.context
import com.efly.base.BaseViewModel
import com.efly.models.Address
import com.efly.models.CustomAttribute
import com.efly.models.SelectedRegion
import com.efly.repositories.HomeRepository
import com.efly.repositories.LocalRepository
import com.efly.utils.*
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: HomeRepository
    val openScreen: MutableLiveData<Int> = MutableLiveData()

    var selectedRegion = SelectedRegion("", 0.0, 0.0)
    var lat: Double = 0.0
    var lng: Double = 0.0
    var regionName: String = ""

    var startStoresFragment = false

    init {
        appComponent.inject(this)
    }

    fun checkProcess(process: Int) {
        if (process == EXTRA_LOGGED_OUT_FROM_STORE) {

            if (selectedRegion != null) {
                LocalRepository.setAddressValues(lat, lng)

                LocalRepository.setIntoSharedPref(
                    KEY_LAST_SELECTED_REGION,
                    Gson().toJson(selectedRegion)
                )
            }

        }
    }

    fun getLastSelectedRegion(): SelectedRegion? {
        selectedRegion = LocalRepository.getLastSelectedRegion()!!
        return selectedRegion
    }

    fun handleAddressResult(
        process: Int,
        latitude: Double,
        longitude: Double,
        data: Intent
    ) { // radwa when does this called
        this.lng = longitude
        this.lat = latitude

        when (process) {
            EXTRA_LOGGED_OUT -> {
//                startAddressFragment = false
//                startStoresFragment = true

                LocalRepository.setAddressValues(lat, lng)
                selectedRegion = SelectedRegion(regionName, lat, lng)
                LocalRepository.setIntoSharedPref(
                    KEY_LAST_SELECTED_REGION,
                    Gson().toJson(selectedRegion)
                )

                var address = ActivityUtil.getAddressFromLocation(lat, lng)
                openScreen.value = EXTRA_LOGGED_OUT

//                view.startActionUpdateAddressService(address)
            }

            EXTRA_LOGGED_IN -> {
//                startStoresFragment = false
//                startAddressFragment = true
//                val MyAddress = data.getParcelableExtra<Parcelable>(Address)
//                if (MyAddress != null) {
//                    //                    selectedZipCode = AddressMigrator.fromMyAddress(data.getParcelableExtra(ADDRESS), context);
//                    selectedAddressModel =
//                        AddressMigrator.fromMyAddress(data.getParcelableExtra<T>(Address), context)
//
//
//                } else {
//                    // The user has selected a place. Extract the name and address.
//                    val latlng = LatLng(latitude, longitude)
//                    // The user has selected a place. Extract the name and address.
//                    getAddressDetails(latlng)
//                }
            }
            REQUEST_LAT_LNG_PICKER -> {
//                startAddressFragment = false
//                startStoresFragment = true
//                address = com.eflyrx.app.data.model.input.Address()
//                //                    address.setLat(lat);
//                //                    address.setLon(lng);
//
//                address = getAddressFromLocation(latitude, longitude)
//                saveLocation(lat, lng)
//
//                //                setLastSelectedZipCode(selectedZipCode);
//
//                //                view.showStoresView(selectedZipCode);
//                setLastSelectedRegion(SelectedRegion(regionName, lat, lng))
//                view.showAddressDetails(regionName, lat, lng)
//
//                view.showStoresView(lat, lng, regionName)
//                view.startActionUpdateAddressService(address)

            }
        }
    }

    fun onResumeFragments() {
        val selectedRegion = getLastSelectedRegion()

        if (selectedRegion != null) {
            // setLastSelectedRegion(SelectedRegion(regionName, lat, lng))

//            view.showStoresView(region!!.getLat(), region!!.getLng(), region!!.getName())
        }
        if (startStoresFragment && lat != 0.0 && lng != 0.0) {

            startStoresFragment = false
        }
//        else if (startAddressFragment && selectedAddressModel != null) { // radwa check this
//            view.openAddressFragment()
//            startAddressFragment = false
//        }
    }



}
