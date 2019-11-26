package com.efly.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.efly.R
import com.efly.base.BaseFragment
import com.efly.models.StoreItem
import com.efly.ui.storehome.StoreHomeActivity
import com.efly.utils.ARG_STORE_ID
import com.efly.utils.KEY_LAT
import com.efly.utils.KEY_LNG
import com.efly.utils.KEY_ZIP
import com.efly.viewmodels.StoresViewModel
import kotlinx.android.synthetic.main.fragment_stores.*

class StoresFragment : BaseFragment(), StoreRecyclerAdapter.StoreListListener {


    lateinit var viewModel: StoresViewModel

    companion object {

        fun newInstance(lat: Double, lng: Double): StoresFragment {
            var fragment = StoresFragment()
            var bundle = Bundle()
            bundle.putDouble(KEY_LAT, lat)
            bundle.putDouble(KEY_LNG, lng)
            fragment.setArguments(bundle)
            return fragment;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StoresViewModel::class.java)

        loadingObserve(viewModel, progress_bar)
        errorObserve(viewModel)
        successObserver()
    }

    private fun successObserver() {
        viewModel.storesList.observe(this, Observer {

            var storeRecyclerAdapter = StoreRecyclerAdapter(
                it, activity!!, this
            )

            list.adapter = storeRecyclerAdapter
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.fragment_stores, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (getArguments() != null) {
            var zipCode = getArguments()?.getString(KEY_ZIP);
            var lat = getArguments()?.getDouble(KEY_LAT, 0.0)
            var lng = getArguments()?.getDouble(KEY_LNG, 0.0)

            viewModel.getStores(lat!!, lng!!)
        }
    }

    override fun onStoreSelected(storesItem: StoreItem, ivBanner: ImageView?) {
        viewModel.saveSelectedStore(storesItem)
        var intent = Intent(activity, StoreHomeActivity::class.java)
        intent.putExtra(ARG_STORE_ID,storesItem.storeCode)
        startActivity(intent)
    }

}