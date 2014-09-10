package com.airk.exercise.im.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.airk.exercise.im.app.R;
import com.airk.exercise.im.app.bean.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2014/9/3.
 *
 * RecyclerView Adapter
 */
public class HolderAdapter extends RecyclerView.Adapter {
    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.user_icon)
        ImageView mIcon;
        @InjectView(R.id.user_msg)
        TextView mMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
    private List<ChatUtil> mChats = new ArrayList<ChatUtil>();
    private final int INCOMING = 1;
    private final int OUTGOING = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == INCOMING) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_left_msg, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_right_msg, parent, false);
        }
        return new ViewHolder(v);
    }

    public int addChatMsg(List<ChatUtil> list) {
        mChats.addAll(list);
        notifyDataSetChanged();
        return mChats.size();
    }

    public int addChatMsg(ChatUtil msg) {
        mChats.add(msg);
        notifyDataSetChanged();
        return mChats.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mChats == null || mChats.size() == 0) {
            return;
        }
        ((ViewHolder) holder).mMsg.setText(mChats.get(position).msg);
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     * <p/>
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>. Type codes need not be contiguous.
     */
    @Override
    public int getItemViewType(int position) {
        ChatUtil chat = mChats.get(position);
        return chat.incoming ? INCOMING : OUTGOING;
    }
}
