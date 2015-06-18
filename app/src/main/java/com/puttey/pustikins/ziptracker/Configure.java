package com.puttey.pustikins.ziptracker;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;


public class Configure extends Activity{

    private ImageButton blueButton;
    private ImageButton greenButton;
    private ImageButton yellowButton;
    private ImageButton purpleButton;
    private ImageButton orangeButton;
    private ImageButton redButton;
    private int mAppWidgetId;
    private Context mContext;
    private RemoteViews mRemoteViews;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        setResult(RESULT_CANCELED);
        mContext = getApplicationContext();
        mRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.new_app_widget);

        Log.d("Debug", "In onCreate");

        //get intent that launched this activity to assign a Widget ID
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        //if no appwidget ID, terminate
        if(mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID){
            finish();
        }

        linkButtons();

        Log.d("Debug", "After linkButtons");



        Log.d("Debug", "After updateAppWidget()");

    }

    /**
     * Set return result and finish activity
     */
    private void returnIntent(){
        Log.d("Debug", "In returnIntent - 1");
        //update App Widget with RemoteViews layout
        AppWidgetManager mAppWidgetManager = AppWidgetManager.getInstance(mContext);
        mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);

        Log.d("Debug", "In returnIntent -2");
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    /**
     * Link buttons to layout and attach onClick listeners
     */
    private void linkButtons(){
        blueButton = (ImageButton) findViewById(R.id.blueButton);
        blueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRemoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.drawable.widget_background_blue_xs);
                returnIntent();
            }
        });

        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        yellowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRemoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.drawable.widget_background_yellow_xs);
                returnIntent();
            }
        });

        greenButton = (ImageButton) findViewById(R.id.greenButton);
        greenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRemoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.drawable.widget_background_green_xs);
                returnIntent();
            }
        });

        orangeButton = (ImageButton) findViewById(R.id.orangeButton);
        orangeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRemoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.drawable.widget_background_orange_xs);
                returnIntent();
            }
        });

        purpleButton = (ImageButton) findViewById(R.id.purpleButton);
        purpleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRemoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.drawable.widget_background_purple_xs);
                returnIntent();
            }
        });

        redButton = (ImageButton) findViewById(R.id.redButton);
        redButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRemoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.drawable.widget_background_red_xs);
                returnIntent();
            }
        });



    }



}
