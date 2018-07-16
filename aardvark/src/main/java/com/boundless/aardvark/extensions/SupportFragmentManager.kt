package com.boundless.aardvark.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun FragmentManager.replaceFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    tag: String = "DEFAULT",
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val transaction = beginTransaction().replace(containerViewId, fragment, tag)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun FragmentManager.addFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    tag: String = "DEFAULT",
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val transaction = beginTransaction().add(containerViewId, fragment, tag)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun FragmentManager.removeFragment(
    fragment: Fragment,
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val transaction = beginTransaction().remove(fragment)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun FragmentManager.removeFragment(
    tag: String,
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val fragment = findFragmentByTag(tag) ?: return
  this.removeFragment(fragment, addToBackStack, backStackTag)
}

fun FragmentManager.clearBackStack() {
  popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}
