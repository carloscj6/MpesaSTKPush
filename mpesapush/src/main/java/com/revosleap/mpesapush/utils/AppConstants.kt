package com.revosleap.mpesapush.utils


object AppConstants {

    const val CONNECT_TIMEOUT = 60 * 1000
    /**
     * Connection Read timeout duration
     */
    const val READ_TIMEOUT = 60 * 1000
    /**
     * Connection write timeout duration
     */
    const val WRITE_TIMEOUT = 60 * 1000
    /**
     * Base URL
     */
    const val BASE_URL = "https://sandbox.safaricom.co.ke/"


    //STKPush Properties
    const val BUSINESS_SHORT_CODE = "174379"
    const val PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"
    const val TRANSACTION_TYPE = "CustomerPayBillOnline"
    const val PARTYB = "174379"
    const val CALLBACKURL =
        "https://spurquoteapp.ga/pusher/pusher.php?title=stk_push&message=result&push_type=individual&regId="

}
