package com.airk.exercise.im.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.airk.exercise.im.app.R;
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

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardActivity.class));
            }
        });
    }
}
