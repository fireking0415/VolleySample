package org.fireking.volleysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStringRequest = findViewById(R.id.btnStringRequest);
        btnStringRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequestActivity.router(MainActivity.this);
            }
        });

        Button btnJsonRequest = findViewById(R.id.btnJsonRequest);
        btnJsonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonRequestActivity.router(MainActivity.this);
            }
        });

        Button btnImageRequest = findViewById(R.id.btnImageRequest);
        btnImageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoaderActivity.router(MainActivity.this);
            }
        });

        Button btnCustomRequest = findViewById(R.id.btnCustomRequest);
        btnCustomRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomRequestActivity.router(MainActivity.this);
            }
        });
    }
}
