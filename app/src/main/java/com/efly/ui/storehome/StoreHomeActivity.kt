package com.efly.ui.storehome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.efly.R
import com.efly.base.BaseActivity
import com.efly.models.CategoryModel
import com.efly.models.ProductModel
import com.efly.models.StoreItem
import com.efly.ui.productdetails.ProductDetailsActivity
import com.efly.ui.products.ProductsActivity
import com.efly.ui.products.ProductsAdapter
import com.efly.utils.*
import com.efly.viewmodels.CartViewModel
import com.efly.viewmodels.StoresViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_store_home.*
import kotlinx.android.synthetic.main.layout_store_card.*
import kotlinx.android.synthetic.main.toolbar_home.*
import kotlinx.android.synthetic.main.toolbar_home.cart_icon
import kotlinx.android.synthetic.main.toolbar_home.cart_item_count
import kotlinx.android.synthetic.main.toolbar_home.cart_view
import kotlinx.android.synthetic.main.toolbar_home.toolbar
import kotlinx.android.synthetic.main.toolbar_home.toolbar_back_btn

class StoreHomeActivity : BaseActivity(), ProductsAdapter.ProductsListListener {


    lateinit var viewModel: StoresViewModel
    lateinit var cartViewModel: CartViewModel
    internal var scrollRange = -1

    lateinit var adapterBestOfferList: ProductsAdapter
    lateinit var adapterBestSellingList: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_home)

        viewModel = ViewModelProviders.of(this).get(StoresViewModel::class.java)
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        initAppBar()

//        showStoreInfo(LocalRepository.getSavedStore())

        cart_view.setOnClickListener { openCartView() }
        cart_item_count.setOnClickListener { openCartView() }
        cart_icon.setOnClickListener { openCartView() }


        loadingObserve(viewModel, progress_bar)
        errorObserve(viewModel)
        viewModel.storeItem.observe(this, Observer {
            showStoreInfo(it)

        })

        var storeId = intent.getStringExtra(ARG_STORE_ID)
        viewModel.getStoreInfo(storeId)

    }

    private fun initAppBar() {

        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange()
                }
                scrollRange = appBarLayout.getTotalScrollRange()
                val percentage =
                    Math.abs(verticalOffset).toFloat() / appBarLayout.getTotalScrollRange()
                //                mLinearLayoutRestaurantInfo.setAlpha(1 - percentage);
                //                mLinearLayoutCenterData.setAlpha(1 - percentage);

                if (scrollRange + verticalOffset == 0) {
                    toolbar_back_btn.setImageDrawable(resources.getDrawable(R.drawable.ic_back))
                    //                    searchImgBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_search));
                    cart_icon.setImageDrawable(resources.getDrawable(R.drawable.ic_cart))
                    toolbar_title.setVisibility(View.VISIBLE)
                    toolbar_title.setTextColor(resources.getColor(R.color.textColor))

                    isShow = true
                    btn_categories.setVisibility(View.VISIBLE)

                } else if (isShow) {
                    toolbar_back_btn.setImageDrawable(resources.getDrawable(R.drawable.ic_back_white))
                    //                    searchImgBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_white));
                    cart_icon.setImageDrawable(resources.getDrawable(R.drawable.ic_cart_white))
                    isShow = false
                    toolbar_title.setVisibility(View.GONE)
                    toolbar_title.setTextColor(resources.getColor(R.color.white))


                    btn_categories.setVisibility(View.GONE)
                }
            }
        })
        setSupportActionBar(toolbar)

        initCollapsingToolbar()

    }

    private fun initCollapsingToolbar() {

        //setCollapsingToolbarLayoutTitle(currentRestaurantInfo.name());
        appbar.setExpanded(true)
        try {
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val width = display.width
            appbar.setLayoutParams(
                CoordinatorLayout.LayoutParams(
                    width,
                    (width / 1.7).toInt()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

                try {
                    if (scrollRange + verticalOffset == 0) {

                        //  setCollapsingToolbarLayoutTitle(currentRestaurantInfo.name());

                        isShow = true
                    } else if (isShow) {
                        //  setCollapsingToolbarLayoutTitle(currentRestaurantInfo.name());

                        //                    Typeface font = Typer.set(MenuActivity.this).getFont(Font.);
                        //                    collapsingToolbar.setExpandedTitleTypeface(font);
                        isShow = false
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }

            }
        })
    }

    private fun showStoreInfo(storesItem: StoreItem?) {
        if (storesItem != null) {

            adapterBestOfferList = ProductsAdapter(
                storesItem.offersProducts!!, this
            )
            best_selling_list.adapter = adapterBestOfferList

//        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        bestSellingList.setLayoutManager(gridLayoutManager);
//        bestSellingList.addItemDecoration(new GridSpacingItemDecoration(2, 10, true, 0));
//            adapterBestSellingList = ProductsAdapter(
//                storesItem.featuredProducts!!, this
//            )
//            best_offer_list.adapter = adapterBestSellingList

            best_selling_list.setNestedScrollingEnabled(false)
            best_offer_list.setNestedScrollingEnabled(false)

            Glide.with(this).load(storesItem!!.logo).into(store_img)

            Glide.with(this).load(storesItem!!.storeBnr).into(store_bnr)

            store_name.setText(storesItem!!.storeName)
            toolbar_title.setText(storesItem!!.storeName)


            //            if (!storesItem.isActive()) {
            //                storeClosed.setVisibility(View.VISIBLE);
            //            } else {
            //                storeClosed.setVisibility(View.GONE);
            //            }

            val workHours = resources.getString(
                R.string.label_store_work_time,
                storesItem!!.from,
                storesItem!!.to
            )

            store_opening_time.setText(workHours)

            val deliveryPeriod = resources.getString(
                R.string.label_store_delivery_period,
                storesItem!!.delivery
            )

            store_delivery_period.setText(deliveryPeriod)

            val feeText = resources.getString(
                R.string.label_store_fees,
                fees
            )

            store_fees.setText(feeText)

            //            if (storesItem.isHasOffers()) {
            //                storeHasOffers.setVisibility(View.VISIBLE);
            //            } else {
            store_has_offers.setVisibility(View.INVISIBLE)
            //            }

            //            String paymentMethods = android.text.TextUtils.join(", ", storesItem.getPayment());
            val paymentMethods = "stripe"
            val fullPaymentMethodsText = resources.getString(
                R.string.label_store_payment_type,
                paymentMethods
            )

            store_payment_type.setText(fullPaymentMethodsText)

        }
    }

    fun onCategorySelected(category: CategoryModel, index: Int) {
        //        navigation.setCurrentItem(2);
        //        categoryTabsFragment = CategoryTabsFragment.newInstance(index);
        //
        //        if (!(getSupportFragmentManager().findFragmentById(R.id.content) instanceof
        //                OffersFragment)) {
        //            ActivityUtil.setRootView(categoryTabsFragment,
        //                    getSupportFragmentManager(),
        //                    R.id.content,
        //                    ProductsListFragment.TAG, this);
        //        }

        //        Intent intent = new Intent(HomeActivity.this, SubCategoriesActivity.class);
        val intent = Intent(this@StoreHomeActivity, ProductsActivity::class.java)
        intent.putExtra(ARG_CAT_INDEX, index)
        intent.putExtra(ARG_CAT_NAME, category.name)
        intent.putExtra(ARG_CAT_ID, category.categoryId)
        startActivity(intent)

    }


    override fun onSubtractFromCart(model: ProductModel?, position: Int): Boolean {
        return cartViewModel.subtractFromCart(model!!)
    }


    override fun onAddToCart(model: ProductModel?, position: Int, first: Boolean): Boolean {
        return cartViewModel.addToCart(model!!, first)
    }

    override fun onViewDetails(model: ProductModel?, position: Int) {
        val args = Bundle()
        args.putParcelable(ARG_PRODUCT, model)
        args.putInt(ARG_POSITION, position)
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_DATA, args)
        startActivity(intent)

    }

}
