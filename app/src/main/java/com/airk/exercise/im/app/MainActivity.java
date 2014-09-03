package com.airk.exercise.im.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.InjectView;


public class MainActivity extends Activity {
    @InjectView(R.id.msg_input)
    EditText mInput;
    @InjectView(R.id.msg_send)
    Button mSend;
    @InjectView(R.id.listview)
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
