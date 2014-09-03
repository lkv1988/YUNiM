package com.airk.exercise.im.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.airk.exercise.im.app.adapter.HolderAdapter;

public class MainActivity extends Activity {
    @InjectView(R.id.msg_input)
    EditText mInput;
    @InjectView(R.id.msg_send)
    Button mSend;
    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(new HolderAdapter());
    }
}
