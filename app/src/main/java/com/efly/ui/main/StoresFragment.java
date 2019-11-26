//package com.efly.ui.main;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.constraint.Group;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//
//import com.efly.base.BaseFragment;
//import com.eflyrx.app.base.BaseActivity;
//import com.eflyrx.app.base.BaseFragment;
//import com.eflyrx.app.R;
//import com.eflyrx.app.cart.CartContract;
//import com.eflyrx.app.cart.CartPresenter;
//import com.eflyrx.app.data.model.StoreItem;
//import com.eflyrx.app.data.model.response.CartInfoResponse;
//import com.eflyrx.app.data.source.MyRepository;
//import com.eflyrx.app.data.source.local.MyLocalDataSource;
//import com.eflyrx.app.data.source.remote.MyRemoteDataSource;
//import com.eflyrx.app.home.HomeActivity;
//import com.eflyrx.app.utils.LangUtils;
//import com.eflyrx.app.utils.schedulers.SchedulerProvider;
//import com.pushwoosh.PushManager;
//import com.pushwoosh.SendPushTagsCallBack;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link OnStoreFragmentListener} interface
// * to handle interaction events.
// */
//public class StoresFragment extends BaseFragment implements
//        StoreRecyclerAdapter.StoreListListener, FiltersRecyclerViewAdapter.StoreFilterCallback, CartContract.CartView {
//
//    private static final String TAG = "StoresFragment";
//    private static final String ARG_LAT = "lat";
//    private static final String ARG_LNG = "lng";
//    private static final String ARG_ZIP_CODE = "zipCode";
//
//    ImageView ivBanner;
////
////    public static final int CODE_CART_WILL_BE_INVALIDATED = 0xA1;
//
//    @BindView(R.id.toolbar_search_et)
//    EditText toolbarSearchEt;
//    @BindView(R.id.group_search)
//    Group mViewSearch;
//    //UI
//    @BindView(R.id.store_progress)
//    ProgressBar storeProgressView;
//    @BindView(R.id.list)
//    RecyclerView list;
//    @BindView(R.id.store_main_form)
//    View storeMainForm;
//
//
//    private Map<String, Object> tags;
//    private StoreRecyclerAdapter storeRecyclerAdapter;
//    private StoreContract.PresenterI presenter;
//    private OnStoreFragmentListener listener;
//    private double lat;
//    private double lng;
//    String zipCode;
//    private LinearLayoutManager layoutManager;
//    private DividerItemDecoration dividerItemDecoration;
//
//    private StoreItem selectedStoreItem;
//
//    public StoresFragment() {
//        // Required empty public constructor
//    }
//
//    public static StoresFragment newInstance(double lat, double lng) {
//        StoresFragment fragment = new StoresFragment();
//        Bundle bundle = new Bundle();
//        bundle.putDouble(ARG_LAT, lat);
//        bundle.putDouble(ARG_LNG, lng);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            zipCode = getArguments().getString(ARG_ZIP_CODE);
//            lat = getArguments().getDouble(ARG_LAT, 0);
//            lng = getArguments().getDouble(ARG_LNG, 0);
//        }
//
//        presenter = new StorePresenterI(this, SchedulerProvider.getInstance(),
//                MyRepository.getInstance(MyLocalDataSource.getInstance
//                                (getActivity().getApplicationContext()),
//                        MyRemoteDataSource.getInstance(getActivity().getApplicationContext())));
//        cartPresenter = new CartPresenter(this, SchedulerProvider.getInstance()
//                , MyRepository.getInstance(MyLocalDataSource.getInstance
//                (getContext()), MyRemoteDataSource.getInstance(getContext())));
//
//        tags = new HashMap<>();
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_stores, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        layoutManager = new LinearLayoutManager(getContext());
//        dividerItemDecoration = new DividerItemDecoration(list.getContext(),
//                layoutManager.getOrientation());
//
//        // radwa, u should remove this.
//        lat = 40.7792047;
//        lng = -73.9734726;
//        if (lat != 0 && lng != 0) {
//            presenter.loadStores(lat, lng);
//        }
//
//        toolbarSearchEt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
////        filterView.setOnClickListener(v -> {
////            if (filterList.getVisibility() == View.VISIBLE) {
////                filterList.setVisibility(View.GONE);
////
////                if (storeRecyclerAdapter != null) {
////                    storeRecyclerAdapter.clearFilters();
////                }
////            } else {
////                filterList.setVisibility(View.VISIBLE);
////            }
////        });
//
////        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
////            @Override
////            public void onTabSelected(TabLayout.Tab tab) {
////                if (tab.getPosition() == 0) {
////                    if (storeRecyclerAdapter != null) {
////                        storeRecyclerAdapter.clearFilters();
////                    }
////                    return;
////                }
////                onFilterSelected(tab.getText().toString());
////            }
////
////            @Override
////            public void onTabUnselected(TabLayout.Tab tab) {
////
////            }
////
////            @Override
////            public void onTabReselected(TabLayout.Tab tab) {
////
////            }
////        });
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnStoreFragmentListener) {
//            listener = (OnStoreFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnStoreFragmentListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }
//
//    @Override
//    public void showProgress(final boolean show, boolean hideMainForm) {
//        if (isAdded()) {
//            storeProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//        }
//    }
//
//
//    @Override
//    public void showError(int errorCode) {
//        switch (errorCode) {
//            case BaseActivity.CODE_CART_WILL_BE_INVALIDATED:
//                String message = getString(R.string.prompt_invalidate_cart,
//                        presenter.previouslySelectedStoreName());
//                showError(message,
//                        getString(R.string.title_invalidate_cart));
//                break;
//        }
//    }
//
//    @Override
//    public void showError(String message, String title) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(R.string.action_okay, (dialogInterface, i) -> {
//                    cartPresenter.invalidateCart();
//                    cartPresenter.invalidateCart();
//                    presenter.saveStoreInfo(selectedStoreItem, LangUtils.getLanguage(getContext()));
//                })
//                .setNegativeButton(R.string.action_cancel, null);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    @Override
//    public void startHome(String imageUrl) {
//
//        try {
//            PushManager.sendTags(getContext(), tags, new SendPushTagsCallBack() {
//                @Override
//                public void taskStarted() {
//                    Log.d(TAG, "sending tags");
//                }
//
//                @Override
//                public void onSentTagsSuccess(Map<String, String> map) {
//                    Log.d(TAG, "tags are sent");
//                }
//
//                @Override
//                public void onSentTagsError(Exception e) {
//                    e.printStackTrace();
//                    Log.e(TAG, "failed to send tags " + e.toString());
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e(TAG, "failed to send tags " + e.toString());
//        }
//
//        if (listener != null)
//            listener.onSavedStore(imageUrl, ivBanner);
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        presenter.unsubscribe();
//
//        if (getActivity() instanceof MainActivity) {
//            ((MainActivity) getActivity()).setSearchingForRegion(false);
//        }
//    }
//
//    @Override
//    public void setPresenter(StoreContract.PresenterI presenter) {
//        this.presenter = presenter;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        presenter.unsubscribe();
//    }
//
//
//    @Override
//    public void showStoreList(List<StoreItem> stores, String regionName) {
//        if (stores == null) {
//            return;
//        }
//
//        //((StoreHomeActivity) getActivity()).showRegionView();
//
//        list.removeItemDecoration(dividerItemDecoration);
//
//        List<StoreItem> onlyStores = new ArrayList<>();
//        for (StoreItem object : stores)
//            //if (object instanceof StoreItem)
//            onlyStores.add(object);
//        storeRecyclerAdapter = new StoreRecyclerAdapter(onlyStores,
//                getContext(), this);
//        list.setAdapter(storeRecyclerAdapter);
//
//    }
//
//    @Override
//    public void showFilters(List<String> filters) {
//        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
//        //  filterList.setLayoutManager(staggeredGridLayoutManager);
//        //  filterList.setAdapter(new FiltersRecyclerViewAdapter(filters, getContext(), this));
//
////        filters.add(0, getString(R.string.all_text));
////        for (String value : filters)
////            tabs.addTab(tabs.newTab().setText(value));
//    }
//
//    @Override
//    public String getHypermarketHeaderString() {
//        return getString(R.string.header_hypermarket);
//    }
//
//    @Override
//    public String getSuperMarketHeaderString() {
//        return getString(R.string.header_supermarket);
//    }
//
//
//    @Override
//    public void onStoreSelected(StoreItem storesItem, ImageView ivBanner) {
//        this.selectedStoreItem = storesItem;
//        this.ivBanner = ivBanner;
//
//        presenter.saveStoreInfo(selectedStoreItem, LangUtils.getLanguage(getContext()));
//    }
//
//    public void filter(String s) {
//        if (storeRecyclerAdapter != null) {
//            storeRecyclerAdapter.getFilter().filter(s);
//        }
//    }
//
//    @Override
//    public void onFilterSelected(String type) {
//        if (storeRecyclerAdapter != null) {
//            storeRecyclerAdapter.filterByType(type);
//        }
//    }
//
//    @Override
//    public void showCartInfo(CartInfoResponse cartInfoResponse) {
//
//    }
//
//    public interface OnStoreFragmentListener {
//        void onSavedStore(String imageUrl, ImageView ivBanner);
//    }
//
//    public void toggleSearchView() {
//        Log.i("a", "toggleSearch");
//        if (mViewSearch.getVisibility() == View.VISIBLE) {
//            hideSearch();
//        } else
//            mViewSearch.setVisibility(View.VISIBLE);
//    }
//
//
//    public void hideSearch() {
//        mViewSearch.setVisibility(View.GONE);
//        toolbarSearchEt.setText("");
//
//
//        View v = getActivity().getCurrentFocus();
//        if (v != null) {
//            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
//                    Context.INPUT_METHOD_SERVICE);
//            if (inputManager != null)
//                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        }
//    }
//
//}
//
//
