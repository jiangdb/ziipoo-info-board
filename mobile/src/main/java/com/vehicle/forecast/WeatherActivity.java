package com.vehicle.forecast;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.SeekBar;

import android.os.Handler;
import android.os.Message;

import com.google.gson.JsonObject;
import com.vehicle.framework.api.ApiCallback;
import com.vehicle.framework.api.ApiError;
import com.vehicle.framework.api.models.Today;
import com.vehicle.framework.api.requests.EtouchWeatherRequest;
import com.vehicle.framework.api.requests.HelpWeatherRequest;
import com.vehicle.framework.api.responses.EtouchWeatherResponse;
import com.vehicle.framework.api.responses.HelpWeatherResponse;
import com.vehicle.utils.Constants;
import com.vehicle.utils.TodayUtil;


public class WeatherActivity extends AppCompatActivity {
    private static String TAG = WeatherActivity.class.getSimpleName();
    private static long ThreeHours = 3 * 3600 * 1000;
    private static int TenSeconds = 10 * 1000;
    private ViewFlipper vp;
    private View view0;
    private View view1;
    private ImageView mIVType;
    private TextView mTemp;
    private TextView mTempUp;
    private TextView mTempDown;
    private TextView mWdsd;
    private TextView mHumidity;
    private TextView mCity;

    private SeekBar mSeekbar;
    private TextView mDestination;
    private TextView mTime;
    private TextView mDistance;

    private ImageView mIVType1;
    private TextView mTemp1;
    private TextView mTempUp1;
    private TextView mTempDown1;
    private TextView mWdsd1;
    private TextView mHumidity1;
    private TextView mCity1;

    private SeekBar mSeekbar1;
    private TextView mDestination1;
    private TextView mTime1;
    private TextView mDistance1;

    private android.os.Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    getWeather("上海");
                    break;
               default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable weathertask = new Runnable() {
        @Override
        public void run() {
            mHanlder.sendEmptyMessage(1);
            mHanlder.postDelayed(this, ThreeHours);//延迟3小时,再次执行task本身,实现了循环的效果
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_main);
        vp = (ViewFlipper) findViewById(R.id.vp);
        LayoutInflater factory = LayoutInflater.from(WeatherActivity.this);

        view0 = factory.inflate(R.layout.activity_weather0, null);
        view1 = factory.inflate(R.layout.activity_weather1, null);
        getView0();
        getView1();

        fillView();

        getWeather("上海");

        mHanlder.postDelayed(weathertask,ThreeHours);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHanlder.removeCallbacks(weathertask);

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_H && event.isLongPress()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.launcher3", "com.android.launcher3.Launcher"));
            startActivity(intent);
            return true;
        }else if (event.getKeyCode() == KeyEvent.KEYCODE_S && event.isLongPress()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
            startActivity(intent);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void getView0() {
        mIVType = view0.findViewById(R.id.iv_type);
        mTemp = view0.findViewById(R.id.tv_temp);
        mTempUp = view0.findViewById(R.id.tv_temp_up);
        mTempDown = view0.findViewById(R.id.tv_temp_down);
        mWdsd = view0.findViewById(R.id.tv_windspeed);
        mHumidity = view0.findViewById(R.id.tv_humidity);
        mCity = view0.findViewById(R.id.tv_city);

        mSeekbar = view0.findViewById(R.id.seekbar);
        mSeekbar.setEnabled(false);
        mSeekbar.setMax(100);//
        mSeekbar.setProgress(50);//
        mDestination = view0.findViewById(R.id.tv_dest);
        mTime = view0.findViewById(R.id.tv_time);
        mDistance = view0.findViewById(R.id.tv_distance);
    }

    private void getView1() {
        mIVType1 = view1.findViewById(R.id.iv_type);
        mTemp1 = view1.findViewById(R.id.tv_temp);
        mTempUp1 = view1.findViewById(R.id.tv_temp_up);
        mTempDown1 = view1.findViewById(R.id.tv_temp_down);
        mWdsd1 = view1.findViewById(R.id.tv_windspeed);
        mHumidity1 = view1.findViewById(R.id.tv_humidity);
        mCity1 = view1.findViewById(R.id.tv_city);

        mSeekbar1 = view1.findViewById(R.id.seekbar);
        mSeekbar1.setEnabled(false);
        mSeekbar1.setMax(100);//
        mSeekbar1.setProgress(50);//
        mDestination1 = view1.findViewById(R.id.tv_dest);
        mTime1 = view1.findViewById(R.id.tv_time);
        mDistance1 = view1.findViewById(R.id.tv_distance);
    }

    private void fillView() {
        vp.addView(view0);
        vp.addView(view1);
        vp.setAutoStart(true);
        vp.setFlipInterval(TenSeconds);
        vp.startFlipping();
        vp.setOutAnimation(this, R.anim.push_up_out);
        vp.setInAnimation(this, R.anim.push_down_in);
    }

    private void getHelpWeather(String city) {
        HelpWeatherRequest.load(city,new ApiCallback<HelpWeatherResponse>() {
            @Override
            public void onResponse(HelpWeatherResponse helpWeatherResponse) {
                if (helpWeatherResponse != null){
                    if (helpWeatherResponse.getStatus().equals("0")){
                        //get the weather
                        String city= helpWeatherResponse.getCity();
                        String humidity = helpWeatherResponse.getHumidity();
                        String wdsd = helpWeatherResponse.getWindSpeed();
                        Today.getInstance().setCity(city)
                                .setHumidity(humidity)
                                .setWindSpeed(wdsd);
                        mCity.setText(TodayUtil.getCityWithParent(city));
                        mHumidity.setText(humidity);
                        mWdsd.setText(Html.fromHtml(wdsd));

                        mCity1.setText(TodayUtil.getCityWithParent(city));
                        mHumidity1.setText(humidity);
                        mWdsd1.setText(Html.fromHtml(wdsd));
                        if (wdsd.equals("")){
                            mWdsd.setText("0km/h");
                            mWdsd1.setText("0km/h");
                        }
                    }
                }
            }

            @Override
            public void onError(ApiError error) {
                Toast toast = Toast.makeText(WeatherActivity.this,error.message,Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });
    }

    private void getWeatherByCity(String city) {
        EtouchWeatherRequest.load(city,new ApiCallback<EtouchWeatherResponse>() {
            @Override
            public void onResponse(EtouchWeatherResponse etouchResponse) {
                if (etouchResponse != null){
                    if (etouchResponse.getStatus().equals("1000")){
                        JsonObject today = etouchResponse.getToday();
                        String high = today.get(Constants.TODAY_HIGH).getAsString();
                        String low = today.get(Constants.TODAY_LOW).getAsString();
                        String type = today.get(Constants.TODAY_TYPE).getAsString();
                        String wendu = etouchResponse.getWenDu();
                        Today.getInstance().setHighTemperature(high)
                                .setLowTemperature(low)
                                .setWeatherType(type)
                                .setCurrentTemperature(wendu);
                        mTempUp.setText(TodayUtil.getHighTemperature(high));
                        mTempDown.setText(TodayUtil.getLowTemperature(low));
                        mTemp.setText(wendu);
                        mIVType.setImageResource(TodayUtil.getWeatherTypeImage(type));

                        mTempUp1.setText(TodayUtil.getHighTemperature(high));
                        mTempDown1.setText(TodayUtil.getLowTemperature(low));
                        mTemp1.setText(wendu);
                        mIVType1.setImageResource(TodayUtil.getWeatherTypeImage(type));


                    }
                }
            }

            @Override
            public void onError(ApiError error) {
                Toast toast = Toast.makeText(WeatherActivity.this,error.message,Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });
    }

    private void getWeather(String city) {
        getHelpWeather(city);
        getWeatherByCity(city);
    }

    private void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
