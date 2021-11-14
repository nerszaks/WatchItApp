package com.yz.wachitapp.navigation

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.yz.common.BundleKey
import com.yz.presentation.navigation.Navigator
import com.yz.wachitapp.R
import javax.inject.Inject

// TODO
class NavigatorImpl @Inject constructor() : Navigator {

    override fun replaceToScreen(
        from: Fragment,
        to: Fragment,
        data: Parcelable?
    ) {
        to.arguments = bundleOf(BundleKey.ScreenData.name to data)
        from.requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, to)
            .addToBackStack(to::class.java.name)
            .commit()
    }
}