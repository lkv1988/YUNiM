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
    public boolean incoming;

    public ChatUtil(Drawable icon, String msg, boolean incoming) {
        this.icon = icon;
        this.msg = msg;
        this.incoming = incoming;
    }

    public ChatUtil(Context context, int resId, String msg, boolean incoming) {
        icon = context.getResources().getDrawable(resId);
        this.msg = msg;
        this.incoming = incoming;
    }

    public ChatUtil(Context context, String msg, boolean incoming) {
        this(context, R.drawable.ic_action_person, msg, incoming);
    }
}
