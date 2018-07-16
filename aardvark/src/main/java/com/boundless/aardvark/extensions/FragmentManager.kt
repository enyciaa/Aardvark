package com.boundless.aardvark.extensions

import android.app.Fragment
import android.app.FragmentManager

fun FragmentManager.removeFragment(
    fragment: Fragment,
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT"
) {
  val transaction = beginTransaction().remove(fragment)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun FragmentManager.removeFragment(
    tag: String,
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT"
) {
  val fragment = findFragmentByTag(tag) ?: return
  this.removeFragment(fragment, addToBackStack, backStackTag)
}

fun FragmentManager.clearBackStack() {
  popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}
