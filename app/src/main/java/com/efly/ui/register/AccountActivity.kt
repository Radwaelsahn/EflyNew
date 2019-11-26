package com.efly.ui.login.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.util.Patterns
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProviders
import com.efly.R
import com.efly.models.Address
import com.efly.models.Customer
import com.efly.models.inputs.CustomerBasic
import com.efly.models.inputs.RegisterInput
import com.efly.models.inputs.UpdateAccountInput
import com.efly.repositories.LocalRepository
import com.efly.utils.ARG_EDIT_ACCOUNT
import com.efly.utils.ActivityUtil
import com.efly.utils.KEY_LAT
import com.efly.viewmodels.AccountViewModel
import kotlinx.android.synthetic.main.activity_register.*
import java.util.ArrayList

class AccountActivity : AppCompatActivity() {

    lateinit var viewModel: AccountViewModel

    var isEditAccount = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)

        val sp = Html.fromHtml(getString(R.string.create_new_user))
        registration_view_title.setText(sp)

//        if (intent != null) {
//            isEditAccount = intent.extras!!.getBoolean(ARG_EDIT_ACCOUNT,false)
//        }

        if (isEditAccount) {
            viewModel.checkAccountInfo()
            et_password.setHint(R.string.hint_old_password)
            et_confirm_password.setHint(R.string.hint_new_password)
        } else {
            val sp = Html.fromHtml(getString(R.string.create_new_user))
            registration_view_title.setText(sp)
        }

        email_sign_in_button.setOnClickListener({ view1 ->
            validateData(
                et_firstname.getText().toString().trim({ it <= ' ' }),
                et_lastname.getText().toString().trim({ it <= ' ' }),
                et_mobile.getText().toString().trim({ it <= ' ' }),
                et_stakeholder.getText().toString().trim({ it <= ' ' }),
                et_email.getText().toString().trim({ it <= ' ' }),
                et_password.getText().toString().trim({ it <= ' ' }),
                et_confirm_password.getText().toString().trim({ it <= ' ' }),
                isEditAccount,
                et_zip_code.getText().toString().trim({ it <= ' ' }),
                et_insurance_no.getText().toString().trim({ it <= ' ' })
            )
        })

    }

    fun validateData(
        firstname: String,
        lastname: String,
        phone: String,
        stakholder: String,
        email: String,
        password: String,
        confirmPassword: String,
        isEditAccount: Boolean,
        zipcode: String,
        insurance: String
    ) {


        var isValid = true
        if (TextUtils.isEmpty(firstname)) {
            isValid = false
            et_firstname.setError(getString(R.string.error_field_required))
        }
        if (TextUtils.isEmpty(lastname)) {
            isValid = false
            et_lastname.setError(getString(R.string.error_field_required))
        }
        if (TextUtils.isEmpty(zipcode)) {
            isValid = false
            et_zip_code.setError(getString(R.string.error_invalid_zipcode))
        }
        if (TextUtils.isEmpty(phone)) {
            isValid = false
            et_mobile.setError(getString(R.string.error_field_required))

        }
        //        else if (!phone.matches("[0-9]+") || phone.length() != 10) {
        //            isValid = false;
        //            view.showInvalidPhone();
        //        }
        if (email.isEmpty()) {
            isValid = false
            et_email.setError(getString(R.string.error_field_required))
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            et_email.setError(getString(R.string.error_invalid_email))
        }
        if (!isEditAccount) {
            if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                if (TextUtils.isEmpty(password)) {
                    isValid = false
                    et_password.setError(getString(R.string.error_field_required))
                }
                if (TextUtils.isEmpty(confirmPassword)) {
                    isValid = false
                    et_confirm_password.setError(getString(R.string.error_field_required))
                }

            } else if (!password.equals(confirmPassword, ignoreCase = true)) {
                isValid = false
                et_confirm_password.setError(getString(R.string.error_password_mismatch))
            }
        }
        if (!isValid) {
            return
        }

        val customer = Customer()
        customer.setEmail(email)
        customer.setGender("1")
        customer.setFirstname(firstname)//.replaceAll("\\s+", " "));
        customer.setLastname(lastname)

        var address = Address()

        if (LocalRepository.getFromSharedPref(KEY_LAT) != "")
            address = ActivityUtil.getAddressFromLocation(
                LocalRepository.getFromSharedPref(KEY_LAT)!!.toDouble(),
                LocalRepository.getFromSharedPref(KEY_LAT)!!.toDouble()
            )

        address.postcode = zipcode
        address.telephone = phone
        address.firstname = firstname
        address.lastname = lastname
        //        address.setCountry_id("US");

        val addresses = ArrayList<Address>()
        addresses.add(address)
        customer.setAddresses(addresses)
        if (!isEditAccount) {
            val registerInput = RegisterInput(customer, password)
            viewModel.register(registerInput)
        } else {
            val registerInput = RegisterInput(customer, "")

            val input = UpdateAccountInput()
            val customerBasic = CustomerBasic()
            customerBasic.setEmail(customer.getEmail())
            customerBasic.setFirstname(customer.getFirstname())
            customerBasic.setLastname(customer.getLastname())
            input.setCustomer(customerBasic)
            viewModel.editAccount(input)
        }

    }
}
