package uz.safargo.driver.core.helpers.sms_retriever


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.google.android.gms.common.api.CommonStatusCodes
import android.os.Bundle
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.phone.SmsRetriever


import com.google.android.gms.common.api.Status
import java.util.regex.Pattern

class SmsBroadcastReceiver(private val onSmsReceived: (String) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras: Bundle? = intent.extras
            val status: Status? = extras?.get(SmsRetriever.EXTRA_STATUS) as? Status
            when (status?.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message: String? = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE)
                    message?.let {
                        val code = SmsRetrieverHelper.extractVerificationCode(it)
                        onSmsReceived(code ?: "0000")
                    }
                }

                CommonStatusCodes.TIMEOUT -> {

                }
            }
        }
    }
}

object SmsRetrieverHelper {
    fun startSmsRetriever(context: Context) {
        val client = SmsRetriever.getClient(context)
        val task = client.startSmsRetriever()

        task.addOnSuccessListener {

        }

        task.addOnFailureListener {

        }
    }

    fun extractVerificationCode(message: String): String? {

        val pattern = Pattern.compile("\\b\\d{4}\\b")
        val matcher = pattern.matcher(message)

        return if (matcher.find()) {
            matcher.group(0)
        } else {
            null
        }
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RegisterSmsReceiver(onSmsReceived: (String) -> Unit) {

    val context = LocalContext.current
    SmsRetrieverHelper.startSmsRetriever(context)
    val smsReceiver = remember { SmsBroadcastReceiver(onSmsReceived) }

    DisposableEffect(Unit) {
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)

        context.registerReceiver(smsReceiver, intentFilter, Context.RECEIVER_EXPORTED)

        onDispose {

            context.unregisterReceiver(smsReceiver)
        }
    }
}


