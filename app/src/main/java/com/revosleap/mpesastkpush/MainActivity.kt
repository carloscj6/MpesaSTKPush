package com.revosleap.mpesastkpush

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.revosleap.mpesapush.models.TransactionDetails
import com.revosleap.mpesapush.mpesa.MpesaPay
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPay.setOnClickListener {
            val details= TransactionDetails(
                "07xxxxxxxx",
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
                "120",
                "174379",
                "APPLES3",
                "https://spurquoteapp.ga/pusher/pusher.php?title=stk_push&message=result&push_type=individual&regId=",
                "174379",
                "174379",
                "fruits",
                "CustomerPayBillOnline"
            )
            MpesaPay.sendPush(details)

            MpesaPay.sendPush("07xxxxxxxx",
                "100",
                "174379",
                "174379",
                "APPLES3",
                "fruits",
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
                "https://spurquoteapp.ga/pusher/pusher.php?title=stk_push&message=result&push_type=individual&regId="
            )


        }

    }


}
