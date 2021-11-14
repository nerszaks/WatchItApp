package com.yz.presentation.navigation

import android.os.Parcelable
import androidx.fragment.app.Fragment

interface Navigator {

    fun replaceToScreen(from: Fragment, to: Fragment, data: Parcelable? = null)

}