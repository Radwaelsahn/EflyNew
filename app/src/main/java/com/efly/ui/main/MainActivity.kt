package com.efly.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.efly.R
import com.efly.base.BaseActivity
import com.efly.models.Address
import com.efly.repositories.LocalRepository
import com.efly.ui.location.LocationPickerActivity
import com.efly.ui.storehome.StoreHomeActivity
import com.efly.utils.*
import com.efly.viewmodels.CartViewModel
import com.efly.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : BaseActivity() {

    private val addedAddress: Address? = null

    lateinit var myViewPageStateAdapter: MyViewPageStateAdapter

    lateinit var viewModel: MainViewModel
    lateinit var cartViewModel: CartViewModel
    private var process = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        if (savedInstanceState == null) {
            if (intent.extras != null) {
                process = intent.extras!!.getInt(PROCESS)

                viewModel.checkProcess(process)

            }
        }

//        if (viewModel.selectedRegion.lat == 0.0)
////            startActivity(Intent(this,LoginActivity::class.java))
//            startLocationPicker()

        loadingObserve(viewModel, null)
        errorObserve(viewModel)
        successObserver()

        setStatePageAdapter()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                changeTabIcon(tab, true)

                pager.currentItem = tab.position
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                val count = fm.backStackEntryCount
                if (count >= 1) {
                    supportFragmentManager.popBackStack()
                }
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                changeTabIcon(tab, false)
                // setCategoriesAdapter();


            }

            override fun onTabReselected(tab: TabLayout.Tab) {

                //   viewPager.notifyAll();
            }
        })
        tabs.getTabAt(0)?.select()
        pager.setOffscreenPageLimit(0)

        initViews()

    }

    private fun initViews() {
        cart_view.setOnClickListener { openCartView() }
        cart_item_count.setOnClickListener { openCartView() }
        cart_icon.setOnClickListener { openCartView() }

        tv_toolbar_region_name.setOnClickListener { onRegionNameClicked() }
    }

    private fun successObserver() {
        viewModel.openScreen.observe(this, Observer { it ->
            when (it) {
                EXTRA_LOGGED_OUT -> handleLogoutProcess()
                EXTRA_LOGGED_OUT_FROM_STORE -> showStoresView(
                    viewModel.lat,
                    viewModel.lng,
                    viewModel.selectedRegion?.name
                )
                REQUEST_LAT_LNG_PICKER -> startLocationPicker()
                EXTRA_LOGGED_IN -> handleLoginProess()
                else -> handleLoginProess()
            }

        })
    }

    fun handleLoginProess() {
        pickLastSelectedAddress()
    }

    private fun handleLogoutProcess() {
        startLocationPicker()
    }

    fun hideSearchAndRegion() {
        if (search_region_view != null)
            search_region_view.setVisibility(View.GONE)
    }

    fun showSearchAndRegion() {
        if (search_region_view != null)
            search_region_view.setVisibility(View.VISIBLE)
    }


    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        process = savedInstanceState?.getInt(PROCESS)!!
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PROCESS, process)
    }


    private fun pickLastSelectedAddress() {
        viewModel.getLastSelectedRegion()
        if (viewModel.selectedRegion == null) {
//            addressListFragment = AddressListFragment()
//            ActivityUtil.addFirstFragmentToActivity(
//                supportFragmentManager, addressListFragment,
//                R.id.content, this
//            )

        } else {
            tv_toolbar_region_name.setText(viewModel.selectedRegion?.getName())
        }
    }

//    private void pickLastSelectedZipCode() {
//        String zipCode = presenter.getSelectedZipCode();
////        if (selectedRegion == null) {
////            addressListFragment = new AddressListFragment();
////            ActivityUtil.addFirstFragmentToActivity(getSupportFragmentManager(), addressListFragment,
////                    R.id.content, this);
////
////        } else {
//        regionNameView.setText(zipCode);
//        storeFragment = StoreFragment.newInstance(zipCode);//selectedRegion.getLat(), selectedRegion.getLng());
//        ActivityUtil.addFirstFragmentToActivity(getSupportFragmentManager(),
//                storeFragment, R.id.content, this);
////        }
//    }


    fun onRegionNameClicked() {
        hideSearchAndRegion()
        if (LocalRepository.isLoggedIn()) {

//            addressListFragment = AddressListFragment()
//            ActivityUtil.addFirstFragmentToActivity(
//                supportFragmentManager, addressListFragment,
//                R.id.content, this@StoreHomeActivity
//            )

        } else {
            startLocationPicker()
        }

    }

    fun showRegionView() {
        /*if (regionNameView.getVisibility() == View.GONE) {
            regionNameView.setVisibility(View.VISIBLE);
        }*/


    }

    fun onSavedStore(imageUrl: String, ivBanner: ImageView) {

        val intent = Intent(this, StoreHomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
        //        intent.putExtra(EXTRA_IMAGE_KEY, imageUrl);
        //        ActivityOptionsCompat options = ActivityOptionsCompat.
        //                makeSceneTransitionAnimation(this, (View) ivBanner, "profile");
        //        startActivity(intent, options.toBundle());

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)

    }


    fun onAddressSelectedSuccessfully(addressModel: Address) {
//        presenter.handleOnAddressSelectedSuccessfully(addressModel)
    }

    fun onAddNewAddressSelected() {
        //        switch (process){
        //            case EXTRA_LOGGED_IN:
        startLocationPicker()
        //            case EXTRA_LOGGED_OUT:
        //            case EXTRA_LOGGED_OUT_FROM_STORE:
        //                Intent loginIntent = new Intent(this,AccountActivity.class);
        //                startActivity(loginIntent);
        //        }

    }

    fun openAddressDetails(addressModel: Address) {
//        presenter.handleOnAddressSelectedSuccessfully(addressModel)
    }

    fun startLocationPicker() {
        var intent = Intent(this, LocationPickerActivity::class.java)
        startActivityForResult(intent, REQUEST_NEW_ADDRESS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) {
            return
        }

        if (requestCode == REQUEST_LAT_LNG_PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                val latitude = data.getDoubleExtra(KEY_LAT, 0.0)
                val longitude = data.getDoubleExtra(KEY_LNG, 0.0)
                LocalRepository.setAddressValues(latitude, longitude)

            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (supportFragmentManager.backStackEntryCount === 0) {
                    finish()
                }
            }
        } else if (requestCode == REQUEST_NEW_ADDRESS) {
            if (resultCode == Activity.RESULT_OK) {
                val latitude = data.getDoubleExtra(KEY_LAT, 0.0)
                val longitude = data.getDoubleExtra(KEY_LNG, 0.0)

                viewModel.handleAddressResult(process, latitude, longitude, data)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (supportFragmentManager.backStackEntryCount == 0) {
                    finish()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun showStoresView(lat: Double, lng: Double, regionName: String?) {
//        if (regionName != null && lat != 0.0 && lng != 0.0) {
//            LocalRepository.setLastSelectedRegion(SelectedRegion(regionName, lat, lng))
//        }
        showSearchAndRegion()
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        viewModel.onResumeFragments()
    }

    override fun onResume() {
        super.onResume()
        //updateCartView(presenter.getCart(), cartItemCount, null);
        cartViewModel.getCartInfo()
        showSearchAndRegion()
    }

    fun openAddressFragment() {

//        val intent = Intent(this, NewAddressActivity::class.java)
//
//        val add = presenter.getSelectedAddressModel()
//        intent.putExtra(
//            Constants.ARG_ADDRESS_MODEL,
//            Parcels.wrap(presenter.getSelectedAddressModel())
//        )
//        intent.putExtra(LATITUDE, presenter.getLat())
//        intent.putExtra(LONGITUDE, presenter.getLng())
//        intent.putExtra(Constants.ARG_IS_FOR_NEW_ADDRESS, true)
//        intent.putExtra(Constants.ARG_IS_LOGGED_IN, process == Constants.EXTRA_LOGGED_IN)
//
//        startActivity(intent)
    }


    fun showCreatedAt(createdAt: String) {
        //        navMemberSince.setText(String.format("Member Since %s", createdAt));
    }

    fun showLogin() {
        //        navTvLogin.setVisibility(View.VISIBLE);
        //        navLoggedInHeader.setVisibility(View.GONE);
        //  navLoggedInForm.setVisibility(View.GONE);
    }

    internal fun openLoginActivity() {
//        val intent = Intent(this, AccountActivity::class.java)
//        intent.putExtra(AccountActivity.ARG_PROCESS, AccountActivity.PROCESS_LOGIN)
//        startActivity(intent)
    }


    //    @OnClick({R.id.tv_edit_profile, R.id.nav_order_history_view, R.id.tv_shipping_address, R.id.tv_logout, R.id.tv_contact_us, R.id.toolbar_search_btn})
//    fun onViewClicked(view: View) {
//        val intent: Intent
//        if (view.id == R.id.tv_logout) {
//            if (presenter.isLoggedIn()) {
//                val dialog = AlertDialog.Builder(this)
//                dialog.setTitle(R.string.confirm_logout)
//                dialog.setMessage(R.string.logout_confirm_message)
//                dialog.setPositiveButton(
//                    com.schibstedspain.leku.R.string.yes,
//                    { dialogInterface, i -> presenter.logout() })
//
//                dialog.setNegativeButton(
//                    com.schibstedspain.leku.R.string.no,
//                    { dialogInterface, i ->
//
//                    })
//
//                dialog.show()
//            } else
//                openLoginActivity()
//        }
//
//        if (presenter.isLoggedIn()) {
//            when (view.id) {
//                //            case R.id.nav_tv_login:
//                //                intent = new Intent(this, AccountActivity.class);
//                //                intent.putExtra(AccountActivity.ARG_PROCESS, AccountActivity.PROCESS_LOGIN);
//                //                startActivity(intent);
//                //                break;
//
//                R.id.tv_edit_profile -> {
//                    intent = Intent(this, AccountActivity::class.java)
//                    intent.putExtra(
//                        AccountActivity.ARG_PROCESS,
//                        AccountActivity.PROCESS_EDIT_ACCOUNT
//                    )
//                    startActivity(intent)
//                }
//                R.id.nav_order_history_view -> {
//                    intent = Intent(this, OrderHistoryActivity::class.java)
//                    val bundle = Bundle()
//                    bundle.putInt(
//                        OrderHistoryActivity.ORDER_KEY,
//                        OrderHistoryFragment.MODE_ORDER_HISTORY
//                    )
//                    intent.putExtra(OrderHistoryActivity.ORDER_BUNDLE_KEY, bundle)
//                    startActivity(intent)
//                }
//                R.id.tv_shipping_address ->
//                    //                    drawerLayout.closeDrawer(GravityCompat.START);
//                    onRegionNameClicked()
//
//
//                R.id.tv_contact_us -> showContactUsDialog()
//            }//                case R.id.toolbar_search_btn:
//            //                    storeFragment.toggleSearchView();
//            //                    break;
//        } else
//            Toast.makeText(this, "please login", Toast.LENGTH_SHORT).show()
//    }

    private fun showContactUsDialog() {
        //        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        //        dialog.setMessage(R.string.label_communication_method);
        //        dialog.setPositiveButton(R.string.action_live_chat, (dialogInterface, i) -> {
        //            Intercom.client().displayMessenger();
        //        });
        //        dialog.setNegativeButton(R.string.action_call_us, (dialogInterface, i) -> {

        val phone = "22022007"
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
        //        });

        //        dialog.show();
    }

//    public void changeLanguage(String lng) {
//        presenter.changeLanguage(lng);
//    }


    fun showAddressDetails(regionName: String, lat: Double, lng: Double) {
        tv_toolbar_region_name.setText(regionName)
        showStoresView(lat, lng, regionName)
    }
//    public void showAddressDetails(String zipCode) {
//        regionNameView.setText(zipCode);
//        showStoresView(zipCode);
//    }

    fun showRegion(regionName: String) {
        tv_toolbar_region_name.setText(regionName)
    }

    fun showLocationPicker() {
        startLocationPicker()
    }

    fun startActionUpdateAddressService(address: Address) {
//        AddressUpdaterIntentService.startActionUpdateAddress(this, address)
    }


    override fun onStop() {
        super.onStop()
//        if (accountUpdateSubscription.isUnsubscribed()) {
//            accountUpdateSubscription.unsubscribe()
//        }
    }

    fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        //        if (checkedId == rbArabic.getId()) {
        //            changeLanguage("ar");
        //        } else {
        //            changeLanguage("en");
        //        }
    }


//    fun showCartInfo(cartInfoResponse: CartInfoResponse) {
//        updateCartView(cartInfoResponse.getItems_count(), 0, cartItemCount, null)
//    }


    private fun setStatePageAdapter() {
        myViewPageStateAdapter = MyViewPageStateAdapter(supportFragmentManager)

        viewModel.getLastSelectedRegion()
        myViewPageStateAdapter.addFragment(
            StoresFragment.newInstance(
                viewModel.selectedRegion?.getLat()!!,
                viewModel.selectedRegion?.getLng()!!
            ), "Home"
        )
        myViewPageStateAdapter.addFragment(StoresFragment(), "Orders")
        myViewPageStateAdapter.addFragment(StoresFragment(), "Orders")
//        myViewPageStateAdapter.addFragment(OrderHistoryFragment(), "Orders")
//        myViewPageStateAdapter.addFragment(CategoriesFragment(), "Category")
//        myViewPageStateAdapter.addFragment(ContactFragment(), "Contacts")
//        myViewPageStateAdapter.addFragment(AccountFragment(), "Account")

        pager.setAdapter(myViewPageStateAdapter)
        tabs.setupWithViewPager(pager, true)
        setTabIcons()

        tabs.getTabAt(0)?.select()

        pager.setOffscreenPageLimit(0)


    }


    private fun setTabIcons() {
        changeTabIcon(tabs.getTabAt(0)!!, true)
        changeTabIcon(tabs.getTabAt(1)!!, false)
        changeTabIcon(tabs.getTabAt(2)!!, false)
//        changeTabIcon(tabs.getTabAt(3), false)
//        changeTabIcon(tabs.getTabAt(4), false)
        //        changeTabIcon(tabs.getTabAt(3)!!, true)
    }

    private fun changeTabIcon(tab: TabLayout.Tab, selected: Boolean) {
        when (tab.position) {
            0 -> tab.setIcon(if (selected) R.drawable.ic_notification else R.drawable.ic_notification)
            1 -> tab.setIcon(if (selected) R.drawable.ic_profile else R.drawable.ic_profile_selected)
            2 -> tab.setIcon(
                if (selected) R.drawable.ic_notification
                else R.drawable.ic_profile_selected
            )
//            3 -> tab.setIcon(if (selected) R.drawable.contact_us else R.drawable.contact_us)
//            4 -> tab.setIcon(if (selected) R.drawable.profile else R.drawable.profile)
        }

    }

}
