package com.revosleap.mpesapush.models

import java.io.Serializable

class TransactionDetails(
    var phoneNumber: String?, var passKey:String?,
    var cashAmount:String?, var payBill:String?,
    var accReference:String?, var callBackUrl:String?,
    var partyA:String?, var partyB:String?,
    var description:String?, var type:String?): Serializable {
    constructor() : this(null,null,null,null,null,null,null,null,null,null)

}