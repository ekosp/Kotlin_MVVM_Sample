package com.ekosp.mvvmlivedata

data class Hitung (
            var total: Int = 0,
            var pajak: Int = 0,
            var jumlahTotal: Int = 0,
            var kembalian: Int = 0

) {
    override fun toString(): String {
        return "Hitung(total=$total, pajak=$pajak, jumlahTotal=$jumlahTotal, kembalian=$kembalian)"
    }
}