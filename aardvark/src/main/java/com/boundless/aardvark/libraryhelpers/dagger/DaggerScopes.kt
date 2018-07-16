package com.boundless.aardvark.libraryhelpers.dagger

import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApplication

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment
