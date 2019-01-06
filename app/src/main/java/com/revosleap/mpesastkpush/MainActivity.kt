package com.revosleap.mpesastkpush

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.revosleap.mpesapush.mpesa.MpesaPay
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPay.setOnClickListener {
            MpesaPay.sendPush("0720273199","1","174379")


        }

    }


}
