package com.ekosp.mvvmlivedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class MainViewModel(val context: Context) : ViewModel() {

    fun calculateHitung(subTotal: Int, diskon: Int): LiveData<Hitung> {
        return HitungRepository.getInstance(context).calculate(subTotal, diskon)
    }

    fun hitungKembalian(total: Int, bayar: Int): LiveData<Hitung> {
        return HitungRepository.getInstance(context).hitungKembalian(total, bayar)
    }

    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(context) as T
        }
    }
}