package com.ekosp.mvvmlivedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ekosp.mvvmlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainActivityCallbacks {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, MainViewModel.Factory(this)).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivityCallback = this

    }

    override fun hitungHarga(view: View) {
        observeHitung(
            binding.edtSubTotal.text.toString().toInt(),
            binding.edtDiskon.text.toString().toInt()
        )
    }

    override fun hitungKembalian(view: View) {
        var bayar = 0
        if (binding.edtJumlahTotal.text.toString() != "Bayar" || binding.edtbayar.text.toString().isNotEmpty())
            bayar = binding.edtbayar.text.toString().toInt()

        observeKembalian(
            binding.edtJumlahTotal.text.toString().toInt(),
            bayar
        )
    }

    private fun observeHitung(subTotal: Int, diskon: Int) {
        viewModel.calculateHitung(subTotal, diskon).observe(this, Observer { hitung ->
            binding.setVariable(BR.vm, hitung)
            binding.executePendingBindings()

        })
    }

    private fun observeKembalian(total: Int, bayar: Int) {
        viewModel.hitungKembalian(total, bayar).observe(this, Observer { hitung ->
            binding.setVariable(BR.km, hitung)
            binding.executePendingBindings()

        })
    }
}
