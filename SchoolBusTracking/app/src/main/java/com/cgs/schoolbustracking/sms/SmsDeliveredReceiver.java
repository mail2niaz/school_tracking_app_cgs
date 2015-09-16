package com.cgs.schoolbustracking.sms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Niaz on 7/22/2015.
 */


public class SmsDeliveredReceiver extends BroadcastReceiver {
    private final String TAG = "SmsDeliveredReceiver";

    @Override
    public void onReceive(Context context, Intent arg1) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "SMS delivered", Toast.LENGTH_SHORT).show();
                decodeMessage(arg1);
                break;
            case Activity.RESULT_CANCELED:
                Toast.makeText(context, "SMS not delivered", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void decodeMessage(Intent intent) {
        {
            Bundle extras = intent.getExtras();

            String strMessage = "";

            if (extras != null) {
                Object[] smsextras = (Object[]) extras.get("pdus");

                for (int i = 0; i < smsextras.length; i++) {
                    SmsMessage smsmsg = SmsMessage.createFromPdu((byte[]) smsextras[i]);

                    String strMsgBody = smsmsg.getMessageBody().toString();
                    String strMsgSrc = smsmsg.getOriginatingAddress();

                    strMessage += "SMS from " + strMsgSrc + " : " + strMsgBody;

                    Log.i(TAG, strMessage);
                }

            }

        }
    }
}
