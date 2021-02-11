package com.teenpatti.queendemo.files;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import com.teenpatti.queendemo.R;


public class DialogProgress {

    public Dialog DialogProgressInitialoz(Context context) {

        Dialog ddProgress;
        ddProgress = new Dialog(context);
        ddProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ddProgress.setContentView(R.layout.progress_bar);
        ddProgress.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparant)));
        ddProgress.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        ddProgress.getWindow().setGravity(Gravity.CENTER);

        return ddProgress;

    }

    public void show(Dialog dd) {
        dd.show();
    }

    public void dismiss(Dialog dd) {
        dd.dismiss();
    }

}
