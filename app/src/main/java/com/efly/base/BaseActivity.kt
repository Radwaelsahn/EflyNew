package com.efly.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.efly.repositories.LocalRepository
import com.efly.ui.storehome.StoreHomeActivity
import com.efly.utils.EXTRA_LOGGED_IN
import com.efly.utils.EXTRA_LOGGED_OUT_FROM_STORE
import com.efly.utils.PROCESS

abstract class BaseActivity : AppCompatActivity() {

    val TAG = "BaseActivity"

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        // toast = Toast.makeText(this, "awaiting", Toast.LENGTH_LONG)

    }


    fun loadingObserve(viewModel: BaseViewModel, progressBar: ProgressBar?) {
        if (progressBar != null)
            viewModel.loadingVisibility.observe(this, Observer {
                progressBar.visibility = it
            })
    }

    fun errorObserve(viewModel: BaseViewModel) {
        viewModel.errorMessage.observe(this, Observer {

            if (it != null) {
                var toast = Toast.makeText(applicationContext, it, Toast.LENGTH_LONG)
                toast.show()
            }
        })

    }

    fun navigateTo(screen: Class<out Activity>, list: List<Pair<String, String>>?) {
        val intent = Intent(this, screen)
        if (!list.isNullOrEmpty()) {
            for (i in list) {
                intent.putExtra(i.first, i.second)
            }
        }
        startActivity(intent)
    }

    fun openCartView() {
//        val cart = cartViewModel.getCartInfoModel()
//
//        if (cart != null && cart!!.getItems_count() > 0) {
//            startActivity(Intent(this, ConfirmOrderActivity::class.java))
//            //finish();
//        }
    }


//    override fun onBackPressed() {
//        val fragments = supportFragmentManager
//        val homeFrag = fragments.findFragmentByTag(TAG)
//        if (fragments.backStackEntryCount > 1) {
//            // We have fragments on the backStack that are poppable
//            fragments.popBackStackImmediate()
//        } else if (homeFrag == null || !homeFrag.isVisible) {
//            // We aren't showing the home screen, so that is the next stop on the back journey
//            //navigation.setCurrentItem(0);
//        } else {
//            // We are already showing the home screen, so the next stop is out of the app.
//            //supportFinishAfterTransition();
//
//            val intent = Intent(this, StoreHomeActivity::class.java)
//            if (LocalRepository.isLoggedIn()) {
//                intent.putExtra(PROCESS, EXTRA_LOGGED_IN)
//            } else
//                intent.putExtra(PROCESS, EXTRA_LOGGED_OUT_FROM_STORE)
//        }
////            else {
////                intent.putExtra(PROCESS, EXTRA_LOGGED_OUT)
////            }
//        startActivity(intent)
//    }


}



