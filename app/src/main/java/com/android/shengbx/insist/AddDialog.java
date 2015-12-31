package com.android.shengbx.insist;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by shengbx on 12/31/15.
 */
public class AddDialog extends AlertDialog{


    protected AddDialog(Context context) {
        super(context);
    }

    protected AddDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected AddDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
}
