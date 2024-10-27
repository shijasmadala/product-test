package com.news.product.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.news.product.R

object LoadingScreen {
    private lateinit var dialog: Dialog
    private fun displayLoadingWithText(
        context: Context,
        text: String = "Loading Please wait . . .",
        cancelable: Boolean = false,
    ) { // function -- context(parent (reference))
        if (!LoadingScreen::dialog.isInitialized || !dialog.isShowing) {
            dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.layout_loading_screen)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(cancelable)
            showLoading()
        }
    }

    private fun showLoading() {
        if (LoadingScreen::dialog.isInitialized && !dialog.isShowing) {
            dialog.show()
        }
    }

    private fun hideLoading() {
        if (LoadingScreen::dialog.isInitialized && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun operateDialog(
        visibility: Boolean,
        context: Context,
        text: String = "Loading Please wait . . .",
    ) {
        if (visibility) {
            displayLoadingWithText(context, text)
        } else {
            hideLoading()
        }
    }
}
