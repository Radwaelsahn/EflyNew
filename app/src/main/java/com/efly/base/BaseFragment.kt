package com.efly.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.efly.R
import com.google.gson.Gson

/**
 * Created by Radwa Elsahn on 12/17/2018.
 */
abstract class BaseFragment : Fragment() {

    var baseActivity: AppCompatActivity? = null
        private set

    lateinit var toast: Toast
    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toast = Toast.makeText(view.context, "", Toast.LENGTH_LONG)
    }

    fun loadingObserve(viewModel: BaseViewModel, progressBar: ProgressBar?) {
        (activity as BaseActivity).loadingObserve(viewModel, progressBar)
    }

    fun errorObserve(viewModel: BaseViewModel) {
//        (activity as BaseActivity).errorObserve(viewModel)
        viewModel.errorMessage.observe(this, Observer {
            toast!!.cancel()
            if (it != null) {
                toast = Toast.makeText(activity, it, Toast.LENGTH_LONG)
                toast.show()
            }
        })


    }


    fun navigateTo(context: Context, screen: Class<out Activity>) {
        val intent = Intent(context, screen)
        startActivity(intent)
    }

}
