package com.shawon.yousuf.recyclerviewcardviewdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shawon.yousuf.recyclerviewcardviewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.buttonList)
    Button buttonList;
    @Bind(R.id.buttonGrid)
    Button buttonGrid;



    private String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    private void onButtonListClick(){

        startActivity(ListItemActivity.class);

    }

    private void onButtonGridClick(){

    }


    private void startActivity(Class<? extends Activity> mStartActivityClass){

        Intent intent = new Intent(this, mStartActivityClass);
        startActivity(intent);
    }




    @OnClick({R.id.buttonList, R.id.buttonGrid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonList:
                onButtonListClick();
                break;
            case R.id.buttonGrid:
                onButtonGridClick();
                break;
        }
    }
}
