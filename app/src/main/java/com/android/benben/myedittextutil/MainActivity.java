package com.android.benben.myedittextutil;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends BaseActivity {
    private EditText mEt, mEt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt = (EditText) findViewById(R.id.main_et1);
        mEt1 = (EditText) findViewById(R.id.main_et2);
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.main_et1, R.id.main_et2};
        return ids;
    }
}
