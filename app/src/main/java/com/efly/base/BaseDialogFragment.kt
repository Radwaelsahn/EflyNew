package com.efly.base

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment

/**
 * Created by Radwa Elsahn on 5/12/2019.
 */
open class BaseDialogFragment : AppCompatDialogFragment() {
    lateinit var toast: Toast

    protected fun showProgressBar(progressBar: ProgressBar?, show: Boolean) {
        if (progressBar != null)
            if (show) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
    }

}
