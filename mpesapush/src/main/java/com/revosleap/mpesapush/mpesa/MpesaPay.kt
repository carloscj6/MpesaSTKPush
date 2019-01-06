package com.revosleap.mpesapush.mpesa

import android.util.Log
import com.revosleap.mpesapush.models.TransactionModel
import com.revosleap.mpesapush.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MpesaPay {
    private var mApiClient= ApiClient()
    private var model=TransactionModel()

    fun sendPush(phoneNumber: String, cash: String, payBill:String,partyB:String,
                 accReference:String, transDesc:String,transactionType:String){
        model.phoneNumber=phoneNumber
        model.payBill= payBill
        model.partyB=partyB
        model.accReference=accReference
        model.description=transDesc
        model.type=transactionType
        model.cashAmount= cash
        getAccessToken(model)
    }
    fun sendPush(phoneNumber: String, cash: String, payBill:String,partyB:String,
                 accReference:String, transDesc:String){
        sendPush(phoneNumber,cash,payBill,partyB, accReference, transDesc,AppConstants.TRANSACTION_TYPE)
    }
    fun sendPush(phoneNumber: String, cash: String, payBill:String,partyB:String,
                 accReference:String){
        sendPush(phoneNumber,cash,payBill,partyB, accReference, "Sale")
    }
    fun sendPush(phoneNumber: String, cash: String, payBill:String,partyB:String){
        sendPush(phoneNumber,cash,payBill,partyB, "Current Sale")
    }
    fun sendPush(phoneNumber: String, cash: String, payBill:String){
        sendPush(phoneNumber,cash,payBill,payBill)
    }

    internal fun performSTKPush( transactionModel: TransactionModel
    ) {
        val timestamp = Utils.timestamp
        val stkPush = STKPush(
            transactionModel.payBill!!,
            Utils.getPassword(transactionModel.payBill!!,
            AppConstants.PASSKEY, timestamp),
            timestamp,
            AppConstants.TRANSACTION_TYPE,
            transactionModel.cashAmount!!,
            Utils.sanitizePhoneNumber(transactionModel.phoneNumber!!),
            transactionModel.partyB!!,
            Utils.sanitizePhoneNumber(transactionModel.phoneNumber!!),
            AppConstants.CALLBACKURL,
            transactionModel.accReference!!, //The account reference
            transactionModel.description!!  //The transaction description
        )

        mApiClient.setGetAccessToken(false)

        mApiClient.mpesaService().sendPush(stkPush).enqueue(object : Callback<STKPush> {
            override fun onResponse(call: Call<STKPush>, response: Response<STKPush>) {
                try {
                    if (response.isSuccessful()) {
                        Log.v("Resp",response.body().toString())
                    } else {

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<STKPush>, t: Throwable) {

            }
        })
    }

    internal fun getAccessToken(transactionModel: TransactionModel) {
        mApiClient.setGetAccessToken(true)
        mApiClient.mpesaService().accessToken().enqueue(object : Callback<AccessToken> {
            override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {

                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body()!!.accessToken)
                    performSTKPush(transactionModel)
                }
            }

            override fun onFailure(call: Call<AccessToken>, t: Throwable) {

            }
        })
    }
}