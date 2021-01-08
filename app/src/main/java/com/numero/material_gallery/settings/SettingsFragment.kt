package com.numero.material_gallery.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult
import com.numero.material_gallery.R
import com.numero.material_gallery.core.applySystemWindowInsetsPadding

class SettingsFragment : Fragment(R.layout.fragment_settings), PreferenceFragment.SettingsFragmentListener {

    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialFadeThrough()
        enterTransition = MaterialFadeThrough()

        appUpdateManager = AppUpdateManagerFactory.create(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FragmentContainerView>(R.id.container).applySystemWindowInsetsPadding(applyTop = true)

        childFragmentManager.beginTransaction()
            .replace(R.id.container, PreferenceFragment.newInstance())
            .commit()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                doUpdate(appUpdateInfo)
            }
        }
    }

    override fun doUpdate(appUpdateInfo: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, UPDATE_REQUEST_CODE)
    }

    companion object {
        private const val UPDATE_REQUEST_CODE = 1
    }
}