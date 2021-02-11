package com.teenpatti.queendemo.model;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teenpatti.queendemo.R;


public class ShowToast {

    public static Toast toast;

    public ShowToast(Context context, String info, String type) {

        if (type.equals("error")) {
            if (toast != null)
                toast.cancel();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) ((Activity) context).findViewById(R.id.toast_layout_root));

            ImageView image = (ImageView) layout.findViewById(R.id.image);

            LinearLayout llback = layout.findViewById(R.id.llback);
//            llback.setBackgroundColor(context.getResources().getd(R.color.msg_error_color));
            llback.setBackgroundResource(R.drawable.custom_toast_back);

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(info);
            text.setTextColor(context.getResources().getColor(R.color.messg));

            toast = new Toast(context.getApplicationContext());
//          toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER, 0, 0);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        } else {

            if (toast != null)
                toast.cancel();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) ((Activity) context).findViewById(R.id.toast_layout_root));


            ImageView image = (ImageView) layout.findViewById(R.id.image);


            LinearLayout llback = layout.findViewById(R.id.llback);
//            llback.setBackgroundColor(context.getResources().getd(R.color.msg_error_color));
            llback.setBackgroundResource(R.drawable.custom_toast_back123);

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(info);

            text.setTextColor(context.getResources().getColor(R.color.messg2));
            toast = new Toast(context.getApplicationContext());
//          toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER, 0, 0);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }

    }
}
