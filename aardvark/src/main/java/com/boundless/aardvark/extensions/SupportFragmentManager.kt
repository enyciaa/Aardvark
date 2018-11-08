package com.boundless.aardvark.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun androidx.fragment.app.FragmentManager.replaceFragment(
        @IdRes containerViewId: Int,
        fragment: androidx.fragment.app.Fragment,
        tag: String = "DEFAULT",
        addToBackStack: Boolean = false,
        backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val transaction = beginTransaction().replace(containerViewId, fragment, tag)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun androidx.fragment.app.FragmentManager.addFragment(
        @IdRes containerViewId: Int,
        fragment: androidx.fragment.app.Fragment,
        tag: String = "DEFAULT",
        addToBackStack: Boolean = false,
        backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val transaction = beginTransaction().add(containerViewId, fragment, tag)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun androidx.fragment.app.FragmentManager.removeFragment(
        fragment: androidx.fragment.app.Fragment,
        addToBackStack: Boolean = false,
        backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val transaction = beginTransaction().remove(fragment)

  if (addToBackStack)
    transaction.addToBackStack(backStackTag)

  transaction.commit()
}

fun androidx.fragment.app.FragmentManager.removeFragment(
    tag: String,
    addToBackStack: Boolean = false,
    backStackTag: String = "DEFAULT_BACKSTACK_TAG"
) {
  val fragment = findFragmentByTag(tag) ?: return
  this.removeFragment(fragment, addToBackStack, backStackTag)
}

fun androidx.fragment.app.FragmentManager.clearBackStack() {
  popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
}
