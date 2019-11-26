package com.efly.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.efly.R
import com.efly.base.BaseActivity
import com.efly.ui.main.MainActivity
import com.efly.utils.ActivityUtil
import com.efly.utils.PROCESS
import com.efly.utils.REQUEST_LAT_LNG_PICKER
import com.efly.viewmodels.SplashViewModel


class SplashActivity : BaseActivity() {
    private val mWaitHandler = Handler()
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityUtil.setFullScreen(this)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)


//        token = FirebaseInstanceId.getInstance().token
//        Log.d("Token", "Token value " + token!!)

        loadingObserve(viewModel, null)
        errorObserve(viewModel)
        successObserve()
        mWaitHandler.postDelayed({
            try {
                viewModel.start()

            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }, 3000)




    }


    private fun successObserve() {
        viewModel.guest.observe(this, Observer {
            if (it != null && it == true) {
                continueAsGuest()
            }
        })

        viewModel.openScreen.observe(this, Observer { it ->
            openStoreActivity(it)
        })
    }

    fun continueAsGuest() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(PROCESS, REQUEST_LAT_LNG_PICKER)
        startActivity(intent)
        finish()
    }


    fun openAccountActivity() {
//        val intent = Intent(this@SplashActivity, AccountActivity::class.java)
//        intent.putExtra(
//            AccountActivity.ARG_PROCESS, AccountActivity
//                .PROCESS_LOGIN_PROMPT_AFTER_SPLASH
//        )
//        startActivity(intent)
//        finish()

    }


    fun openStoreActivity(process: Int) {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra(PROCESS, process)
        startActivity(intent)
        finish()
    }


    override fun onResume() {
        super.onResume()

//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(
//            this,
//            OnSuccessListener<InstanceIdResult> { instanceIdResult ->
//                val newToken = instanceIdResult.token
//                Log.e("newToken", newToken)
//            })
    }

    companion object {


        val TAG = "SplashActivity"
    }
}
