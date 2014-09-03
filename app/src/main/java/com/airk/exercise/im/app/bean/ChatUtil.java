package com.airk.exercise.im.app.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.airk.exercise.im.app.R;

/**
 * Created by kevin on 14-9-3.
 *
 * Chat util for listview adapter
 */
public class ChatUtil {
    public Drawable icon;
    public String msg;

    public ChatUtil(Drawable icon, String msg) {
        this.icon = icon;
        this.msg = msg;
    }

    public ChatUtil(Context context, int resId, String msg) {
        icon = context.getResources().getDrawable(resId);
        this.msg = msg;
    }

    public ChatUtil(Context context, String msg) {
        this(context, R.drawable.ic_action_person, msg);
    }
}
