package com.boundless.aardvark.libraryhelpers.dagger

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class DaggerSupportDialogFragment : androidx.fragment.app.DialogFragment(), HasSupportFragmentInjector {

  @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = childFragmentInjector
}
