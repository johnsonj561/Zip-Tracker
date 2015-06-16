package com.puttey.pustikins.ziptracker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider{

    private GPSTracker mGPSTracker;
    private AddressLocation mAddressLocation;
    private double mLatitude;
    private double mLongitude;
    private static String mZip = "11111";
    private RemoteViews remoteViews;
    private ComponentName watchWidget;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        Log.d("Location 1: ", mLatitude + " " + mLongitude);

        //Obtain current location
        mGPSTracker = new GPSTracker(context);
        mLongitude = mGPSTracker.getLongitude();
        mLatitude = mGPSTracker.getLatitude();
        mAddressLocation = new AddressLocation();
        mAddressLocation.getAddressFromLocation(mLatitude, mLongitude, context, new GeocoderHandler());
        Log.d("Location 2: ", mLatitude + " " + mLongitude);
        Log.d("Location 2: mZip = ", mZip);

        //Attach remoteView
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        watchWidget = new ComponentName(context, NewAppWidget.class);

        //Assign intent to refresh_button - allows user to refresh zip code
        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.refresh_button, pendingIntent);
        remoteViews.setTextViewText(R.id.appwidget_text, mZip);

        //Update Widget manager
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    /**
     *Geocode Handler object to append address to mLocationLabel
     */
    private class GeocoderHandler extends Handler{
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    mZip = bundle.getString("address");
                    Log.d("Location zip case 1: ", mZip);
                    break;
                default:
                    mZip = "ERROR";
                    Log.d("Location zip case 2: ", mZip);
            }
        }
    }
}

