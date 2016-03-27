package com.grability.android.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grability.android.test.config.Config;
import com.grability.android.test.database.AppDatabaseHelper;
import com.grability.android.test.vo.ApplicationVO;
import com.grability.android.test.vo.CategoryVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;


public class SplashActivity extends Activity {

    private static String TAG="SplashActivity";
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(savedInstanceState==null) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
            anim.reset();
            RelativeLayout l = (RelativeLayout) findViewById(R.id.ly_splash);
            l.clearAnimation();
            l.startAnimation(anim);


        }
        ProgressBar pgBar=(ProgressBar) findViewById(R.id.pb_splash);
        pgBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimary),
                android.graphics.PorterDuff.Mode.SRC_IN);

        settings = getSharedPreferences(GrabilityApplication.STORE_APPS_PREFERENCES_KEY, 0);
        Boolean jsonLoaded = settings.getBoolean(GrabilityApplication.PREF_JSON_LOADED,false);


        if(!jsonLoaded) {
            loadStoreApplicationsJSON();
        }
        else{
            startMainActivity();
        }
    }

    private void loadStoreApplicationsJSON() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.STORE_APPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new DBSaveAppsDataAsyncTask().execute(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "Store Apps JSON Response Error" + error.getMessage());
                //TODO Mostrar alerta
            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public class DBSaveAppsDataAsyncTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            fillDatabase(params[0]);
            return true;
        }

        private void fillDatabase(String jsonString) {
            final AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(SplashActivity.this);
            appDatabaseHelper.clearCategories();
            appDatabaseHelper.clearApplications();
            try {
                JSONObject json= new JSONObject(jsonString);
                JSONObject feedObj = json.getJSONObject("feed");
                if(feedObj!=null){
                    JSONArray entries=feedObj.getJSONArray("entry");
                    int entriesCount=entries.length();
                    for(int i = 0 ; i < entriesCount; i++){
                        JSONObject entry=(JSONObject) entries.get(i);

                        String appName=entry.getJSONObject("im:name").getString("label");
                        int appID=entry.getJSONObject("id").getJSONObject("attributes").getInt("im:id");


                        JSONArray appImages=entry.getJSONArray("im:image");
                        String appImageAURL="";
                        String appImageBURL="";
                        String appImageCURL="";
                        if(appImages.get(0)!=null){
                            appImageAURL=((JSONObject) appImages.get(0)).getString("label");
                        }
                        if(appImages.get(1)!=null){
                            appImageBURL=((JSONObject) appImages.get(1)).getString("label");
                        }
                        if(appImages.get(2)!=null){
                            appImageCURL=((JSONObject) appImages.get(2)).getString("label");
                        }

                        String appSummary=entry.getJSONObject("summary").getString("label");
                        String appTitle=entry.getJSONObject("title").getString("label");
                        String appArtist=entry.getJSONObject("im:artist").getString("label");
                        String appReleaseDate=entry.getJSONObject("im:releaseDate").getJSONObject("attributes").getString("label");

                        JSONObject appCategoryAttrs=entry.getJSONObject("category").getJSONObject("attributes");
                        int appCategoryID=appCategoryAttrs.getInt("im:id");
                        String appCategoryName=appCategoryAttrs.getString("label");

                        CategoryVO category=new CategoryVO(appCategoryID,appCategoryName);
                        appDatabaseHelper.addCategory(category);

                        ApplicationVO app=new ApplicationVO(appID, appCategoryID, appName, appImageAURL, appImageBURL, appImageCURL, appSummary, appTitle, appArtist, appReleaseDate);
                        appDatabaseHelper.addApplication(app);


                        Log.v(TAG, "Aplication entry "+appName);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                //TODO Mostrar alerta
            }



        }

        @Override
        protected void onPostExecute(Boolean result) {

            super.onPostExecute(result);
            setJSONAsLoaded();
            startMainActivity();
        }


    }
    private void setJSONAsLoaded(){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(GrabilityApplication.PREF_JSON_LOADED, true);

        // Commit the edits!
        editor.commit();
    }
    private void startMainActivity(){
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               openMainActivity();
            }
        },1500);

    }

    private void openMainActivity(){
        Intent mainActivityIntent=new Intent(this,MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

}
