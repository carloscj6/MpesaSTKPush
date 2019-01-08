# MpesaSTKPush
Lipa na Mpesa STK Push library Android. 
## Setup
In your `build.gradle` file add the following dependency;
```gradle
dependencies {
    'com.revosleap:mpesapush:1.0.0'
}
```
In your `gradle.properties` file, add the consumer and consumer secret keys given from Safaricom developer site.
Add as shown below;
```gradle
DARAJA_CONSUMER_KEY="xxxxxxxxx"
DARAJA_CONSUMER_SECRET="xxxxxxxxx"
```
Then add internet permission in your manifest as:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```
## Usage
This is pretty simple. After a successful setup, there are two ways to use the library.
### Method 1
```kotlin
MpesaPay.sendPush(...)
```
**Sample**
```kotlin
MpesaPay.sendPush("07xxxxxxxx", //number to send push to
                "100", //amount of cash
                "174379", //the paybill number
                "174379", //Receiving number
                "APPLES3", //Account number
                "fruits", //Description
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919", //passkey, provided by safaricom
                "https://spurquoteapp.ga/pusher/pusher.php?title=stk_push&message=result&push_type=individual&regId="// Your callback url

            )
```
### Method 2
Use the a given model class named `TransactionDetails`
>Kotlin
```kotlin
MpesaPay.sendPush(TransactionDetails(....))
```
**Sample**
```kotlin
val details= TransactionDetails(
                "07xxxxxxxx", //number to send push to
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919", //passkey, provided by safaricom
                "120", //amount of cash
                "174379", //the paybill number
                "APPLES3", //Account number
                "https://spurquoteapp.ga/pusher/pusher.php?title=stk_push&message=result&push_type=individual&regId=",
                "174379", //Sending number
                "174379", //Receiving number
                "fruits",
                "CustomerPayBillOnline" //Transaction type
            )
  ```
  >Java
  ```java
  MpesaPay.sendPush(new TransactionDetails(....))
  ```
  **Sample**
  ```java
  TransactionDetails details= new TransactionDetails(
                "07xxxxxxxx", //number to send push to
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919", //passkey, provided by safaricom
                "120", //amount of cash
                "174379", //the paybill number
                "APPLES3", //Account number
                "https://spurquoteapp.ga/pusher/pusher.php?title=stk_push&message=result&push_type=individual&regId=",
                "174379", //Sending number
                "174379", //Receiving number
                "fruits",
                "CustomerPayBillOnline" //Transaction type
            );
            
   MpesaPay.sendPush(details)
   ```
   and you are good to go. 
   Visit [Safaricom Documentation](https://developer.safaricom.co.ke/lipa-na-m-pesa-online/apis/post/stkpush/v1/processrequest)
   for more information about the given parameters.
   ## Credits
   Thanks to [This project](https://github.com/safaricom/LNMOnlineAndroidSample) for the inspiration to come up with this.
   ### Libraries used
   * Retrofit
   * Retrofit converter Gson
   * okhttp3 Logging interceptor
   


