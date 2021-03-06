package com.tgcenter.demo.richox;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.richox.base.CommonBuilder;
import com.richox.base.EventCallback;
import com.richox.base.InitCallback;
import com.richox.base.RichOX;
import com.tgcenter.demo.R;
import com.tgcenter.demo.ads.base.BaseActivity;
import com.tgcenter.demo.richox.activity.RichOXCommonActivity;
import com.tgcenter.demo.richox.activity.RichOXH5Activity;
import com.tgcenter.demo.richox.activity.RichOXNormalStrategyActivity;
import com.tgcenter.demo.richox.activity.RichOXSectActivity;
import com.tgcenter.demo.richox.activity.RichOXShareActivity;
import com.tgcenter.demo.richox.activity.RichOXStageStrategyActivity;
import com.tgcenter.demo.richox.activity.RichOXStrategyFissionActivity;
import com.tgcenter.demo.richox.constance.Constants;
import com.we.modoo.ModooHelper;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

public class RichOXMainActivity extends BaseActivity {
    private final String TAG = "RichDemo";

    private TextView mCommonActivity;
    private TextView mH5Activity;
    private TextView mStrategyActivity;
    private TextView mStrategyActivity2;
    private TextView mNormalStrategyActivity;
    private TextView mShareActivity;
    private TextView mSectActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richox_main);
        initView();
        initWeChat();
        initRichOXCommon();
    }


    private void initView() {
        mCommonActivity = findViewById(R.id.demo_activity_richox_common);
        mCommonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXCommonActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mH5Activity = findViewById(R.id.demo_activity_richox_h5);
        mH5Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXH5Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mStrategyActivity = findViewById(R.id.demo_activity_richox_strategy);
        mStrategyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXStrategyFissionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mStrategyActivity2 = findViewById(R.id.demo_activity_richox_strategy2);
        mStrategyActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXStageStrategyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mNormalStrategyActivity = findViewById(R.id.demo_activity_richox_normal_strategy);
        mNormalStrategyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXNormalStrategyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mShareActivity = findViewById(R.id.demo_activity_richox_share);
        mShareActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXShareActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mSectActivity = findViewById(R.id.demo_activity_richox_sect);
        mSectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RichOXMainActivity.this, RichOXSectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void initRichOXCommon() {
        RichOX.setTestMode(true);
        CommonBuilder builder = new CommonBuilder.Builder()
                .setAppId(Constants.APP_ID)
                .setAppKey("nnDlq82zDl")
                .setHostUrl("https://api-d418.freeqingnovel.com")
//                .setDeviceId("60fe37e31ca34a17_eee595b23659d099995c682fd6b879d2") // S2AEZL
//                .setDeviceId("fission_tong_test_1")
//                .setDeviceId("fission_test_invitation")
//                .setDeviceId("fission_test_invitation_2")
                .setDeviceId("test_zly_user_1")
//                .setDeviceId("10100386-10100386")
                .setPlatformId("S150")
                .setChannel("test")
                .build();
        RichOX.init(getApplicationContext(), builder, new InitCallback() {
            @Override
            public void onSuccess() {
                Log.d(Constants.TAG, "Init success");
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.d(Constants.TAG, "Init result code is : " + code + " msg is : " + msg);
            }
        });
        RichOX.registerEventCallback(new EventCallback() {
            @Override
            public void onEvent(String name) {
                Log.d("event", "the name is " + name);
                // 自行打点
                // AppLog.onEventV3(name);
            }

            @Override
            public void onEvent(String name, String value) {
                Log.d("event", "the name is " + name + " and the value is " + value);
                try {
                    JSONObject object = new JSONObject();
                    object.putOpt(name, value);
                    // 自行打点
                    // AppLog.onEventV3(name, object);
                } catch (Exception e) {

                }

            }

            @Override
            public void onEvent(String name, Map<String, Object> map) {
                Log.d("event", "the name is " + name + " and the map is " + map.toString());
                try {
                    JSONObject object = new JSONObject();
                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        object.putOpt(key, map.get(key));
                    }
                    // 自行打点
                    // AppLog.onEventV3(name, object);
                } catch (Exception e) {

                }
            }

            @Override
            public void onEventJson(String name, String jsonString) {
                try {
                    // 自行打点
                    // AppLog.onEventV3(name, new JSONObject(jsonString));
                } catch (Exception e) {

                }
            }
        });
        RichOX.genDefaultDeviceId(this);
        ModooHelper.init(this);
    }

    private void initWeChat() {
        ModooHelper.init(this);
    }
}
