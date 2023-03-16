package com.example.aris4autism_project.fragment

import com.example.aris4autism_project.network.HttpErrorCode

abstract class FragmentBaseWrapper {
    abstract fun somethingWentWrong()

    abstract fun noInternet()

    abstract fun onRetryClicked(retryButtonType: String)

    abstract fun dataNotFound(message: String?, messageCode: String?)

    abstract fun verifyUser(message: String)

    abstract fun newVersionFound()

    abstract fun unAuthorizationUser(message: String?, messageCode: String?)

    open fun httpFailedHandler(code: Int, message: String?, messageCode: String?) {
        handleException(code, message, messageCode)
    }

    private fun handleException(code: Int, message: String?, messageCode: String?) {
        when (code) {
            HttpErrorCode.EMPTY_RESPONSE.code -> dataNotFound(message, messageCode)
            HttpErrorCode.NEW_VERSION_FOUND.code -> newVersionFound()
            HttpErrorCode.UNAUTHORIZED.code -> unAuthorizationUser(message, messageCode)
            HttpErrorCode.NO_CONNECTION.code -> noInternet()
            else -> somethingWentWrong()
        }
    }
}