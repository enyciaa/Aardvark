package com.boundless.aardvark

abstract class MotherException(
    override val message: String,
    customStackTrace: Array<StackTraceElement>? = null
) : Exception() {

  init {
    customStackTrace?.let {
      stackTrace = it
    }
  }
}

class EmptyListException(message: String) : MotherException(message)

class NonFatalException(
    message: String,
    stackTraceElements: Array<StackTraceElement>
) : MotherException(message, stackTraceElements)
