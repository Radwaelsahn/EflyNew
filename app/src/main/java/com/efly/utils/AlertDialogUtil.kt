package com.efly.utils

import android.app.AlertDialog
import android.content.Context
import com.efly.R


/**
 * Created by vezikon on 9/21/17.
 */

object AlertDialogUtil {

    fun showError(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
            .setPositiveButton(R.string.action_okay, null)

        val dialog = builder.create()
        dialog.show()
    }
}
