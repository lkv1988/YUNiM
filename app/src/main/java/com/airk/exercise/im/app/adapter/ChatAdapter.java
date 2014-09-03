package com.airk.exercise.im.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.airk.exercise.im.app.bean.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 14-9-3.
 */
public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<ChatUtil> mChatsMsg = new ArrayList<ChatUtil>();

    public ChatAdapter(Context context, List<ChatUtil> lists) {
        mChatsMsg.addAll(lists);
        this.context = context;
    }

    public void addChatMsg(ChatUtil chat) {
        mChatsMsg.add(chat);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mChatsMsg.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
