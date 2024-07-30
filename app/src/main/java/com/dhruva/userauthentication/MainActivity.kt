package com.dhruva.userauthentication

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dhruva.userauthentication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.fullNameEt.onFocusChangeListener = this
        mBinding.emailEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this
        mBinding.confirmPasswordEt.onFocusChangeListener = this
    }

    private fun validateFullName(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.fullNameEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full name is required"
        }

        if (errorMessage != null) {
            mBinding.fullNameTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateEmail(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.emailEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Email ID is required"
        }
        else if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = "Email ID is invalid"
        }

        if (errorMessage != null) {
            mBinding.emailTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePassword(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.passwordEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        }
        else if (value.length < 6) {
            errorMessage = "Password must be longer than 6 characters"
        }

        if (errorMessage != null) {
            mBinding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.confirmPasswordEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Confirm Password is required"
        }
        else if (value.length < 6) {
            errorMessage = "Confirm Password must be longer than 6 characters"
        }

        if (errorMessage != null) {
            mBinding.confirmPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePasswordAndConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val password: String = mBinding.passwordEt.text.toString()
        val confirmPassword: String = mBinding.confirmPasswordEt.text.toString()
        if (password != confirmPassword) {
            errorMessage = "Confirm password doesn't match with Password"
            mBinding.confirmPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when(view.id) {
                R.id.fullNameEt -> {
                    if (hasFocus) {
                        if (mBinding.fullNameTil.isErrorEnabled) {
                            mBinding.fullNameTil.isErrorEnabled = false
                        }
                    }
                    else {
                        validateFullName()
                    }
                }
                R.id.emailEt -> {
                    if (hasFocus) {
                        if (mBinding.emailTil.isErrorEnabled) {
                            mBinding.emailTil.isErrorEnabled = false
                        }
                    }
                    else {
                        validateEmail()
                    }
                }
                R.id.passwordEt -> {
                    if (hasFocus) {
                        if (mBinding.passwordTil.isErrorEnabled) {
                            mBinding.passwordTil.isErrorEnabled = false
                        }
                    }
                    else {
                        if (validatePassword() && mBinding.passwordEt.text!!.isNotEmpty() && validateConfirmPassword() && validatePasswordAndConfirmPassword()) {
                            if (mBinding.confirmPasswordTil.isErrorEnabled) {
                                mBinding.confirmPasswordTil.isErrorEnabled = false
                            }
                            mBinding.confirmPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
                R.id.confirmPasswordEt -> {
                    if (hasFocus) {
                        if (mBinding.confirmPasswordTil.isErrorEnabled) {
                            mBinding.confirmPasswordTil.isErrorEnabled = false
                        }
                    }
                    else {
                        if (validatePassword() && validateConfirmPassword() && validatePasswordAndConfirmPassword()) {
                            if (mBinding.passwordTil.isErrorEnabled) {
                                mBinding.passwordTil.isErrorEnabled = false
                            }
                            mBinding.confirmPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}