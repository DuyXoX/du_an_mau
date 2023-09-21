package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Wellcome extends AppCompatActivity {
    private static final long DELAY_TIME = 3000; // 3 giây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Wellcome.this, MainActivity.class);
                startActivity(intent);
                finish(); // Đóng Activity hiện tại để không quay lại khi nhấn nút Back
            }
        }, DELAY_TIME);
    }
}