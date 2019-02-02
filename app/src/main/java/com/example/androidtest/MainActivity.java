package com.example.androidtest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.curl.jmodule.Commander;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends Activity {

    private static final String CMD = "curl https://news.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        start();
                    }
                }
        );
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void start() {
        if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            test();
        }
    }

    private void test() {
        final Handler handler = new Handler();
        final Commander commander = new Commander();
        commander.init(this.getExternalCacheDir().getAbsolutePath() + File.separator + "terminal.txt", "/sdcard/cacert.pem");
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        final String value = commander.execute(CMD);

                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        ((TextView) findViewById(R.id.text)).setText(value);
                                    }
                                }
                        );
                    }
                }
        ).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1000) {
            test();
        }
    }
}
