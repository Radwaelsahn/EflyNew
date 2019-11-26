package com.efly.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import androidx.lifecycle.ViewModelProviders
import com.efly.R
import com.efly.ui.login.register.AccountActivity
import com.efly.utils.ActivityUtil
import com.efly.viewmodels.AccountViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar_back.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)


        val sp = Html.fromHtml(getString(R.string.login_to_your_account))
        tv_lbl_login1.setText(sp)

        toolbar_back_btn.setOnClickListener { onBackPressed() }
        tv_forget_password.setOnClickListener {
            //            title = getString(R.string.title_reset_password)
//            val resetPasswordFragment = ResetPasswordFragment()
//            ActivityUtil.addFragmentToActivity(
//                supportFragmentManager, resetPasswordFragment, R
//                    .id.fragment, this
//            )

        }
        tv_register.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
            finish()
        }
        continue_as_tv.setOnClickListener {
            //            val intent = Intent(this, ZipActivity::class.java)
//            intent.putExtra(Constants.PROCESS, Constants.EXTRA_LOGGED_OUT)
//            startActivity(intent)
        }
        btn_login.setOnClickListener {
            ActivityUtil.hideInputType(this)
            val username = et_user_name.getText().toString().trim({ it <= ' ' })
            val password = et_password.getText().toString().trim({ it <= ' ' })
            validate(username, password)
        }
    }


    fun validate(username: String, password: String) {

        var isValid = true
        if (TextUtils.isEmpty(username)) {
            isValid = false
            et_user_name.setError(getString(R.string.error_field_required))
        }
        if (TextUtils.isEmpty(password)) {
            isValid = false
            et_password.setError(getString(R.string.error_field_required))
        } else if (password.length < 8) {
            isValid = false
            et_password.setError(getString(R.string.error_password_number))
        }
        if (!isValid) {
            return
        }
        viewModel.login(username, password)
    }

}
