package com.ekosp.mvvmlivedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context

class HitungRepository {

    companion object {
        private var hitungRepository: HitungRepository? = null
        private var context: Context? = null

        fun getInstance (context: Context): HitungRepository {
            this.context = context
            if (hitungRepository == null) hitungRepository = HitungRepository()
            return hitungRepository!!
        }
    }

    fun calculate(subtotal: Int, diskon: Int): LiveData <Hitung> {
        val hitungResult = MutableLiveData <Hitung>()
        val total = subtotal - (subtotal * diskon)/100
        val pajak = 10
        val jumlahTotal= total + (total * pajak)/100

        val m = Hitung(total, pajak, jumlahTotal)
        hitungResult.value= m

        return hitungResult
    }

    fun hitungKembalian(total: Int, bayar: Int): LiveData <Hitung> {
        val hitungResult = MutableLiveData <Hitung>()
        val kembalian = bayar - total

        val m = Hitung(0,0,0,kembalian)
        hitungResult.value= m

        return hitungResult
    }
}