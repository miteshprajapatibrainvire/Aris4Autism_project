package com.example.aris4autism_project

interface IOnBackPressed {
    fun onBackPressed(): Boolean
    abstract fun getItemSeleted(deleteId: Boolean)
}