package com.example.auser.getokhttp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getMethod(View v){
        //Log.d("test1","ttt");
        OkHttpClient client =new OkHttpClient();
        String param="userid=12345556778&temperature=13";///要輸入資料庫的東西
        Request request=new Request.Builder()
                .url("http://192.168.58.018:8080/code/android_api/get_api_return.php?"+param)
                .build();
        Call call=client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("test2","fail");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"連線失敗",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("test3","ok");
                String json=response.body().string();
                Log.d("test4",json);
                int flag =json.compareTo("0");
                Log.d("test_flag",flag+"");
                if(flag==0){
                    Log.d("test5","0");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"新增失敗,帳號已存在",Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    Log.d("test6","1");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"新增成功",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });



    }
}
