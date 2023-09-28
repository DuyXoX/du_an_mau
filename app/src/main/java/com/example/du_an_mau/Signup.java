package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau.DAO.NguoiDungDAO;

public class Signup extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;
    private EditText username, password, passwordagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nguoiDungDAO = new NguoiDungDAO(this);

        TextView buttonLogin = findViewById(R.id.buttonLogin);

        Button btn_test = findViewById(R.id.btn_test);
        Button btnSignup = findViewById(R.id.btn_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordagain = findViewById(R.id.passwordagain);

//        chi dung duoc cau lenh nay trong giao dien Test
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String test = bundle.getString("test");
        String gioiTinh = bundle.getString("gioiTinh");
        username.setHint(test);
        password.setHint(gioiTinh);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onLoginButtonClick();
                String data = username.getText().toString();

                Intent intent = new Intent(Signup.this, Login.class); // Thay B.class bằng tên Activity B
                intent.putExtra("user", data);
//                Toast.makeText(Signup.this, data , Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String user = username.getText().toString();
//                String pass = username.getText().toString();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("user", user);
//                bundle.putString("pass", pass);
//                intent.putExtras(bundle);
//                setResult(1, intent);
//                finish();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String newUsername = username.getText().toString();
//                String newPassword = password.getText().toString();
//                String confirmPassword = passwordagain.getText().toString();
//
//                // Kiểm tra tính hợp lệ của dữ liệu (ví dụ: kiểm tra mật khẩu và xác nhận mật khẩu)
//                if (!newPassword.equals(confirmPassword)) {
//                    Toast.makeText(Signup.this, "Mật khẩu và xác nhận mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Kiểm tra xem tên đăng nhập đã tồn tại trong cơ sở dữ liệu chưa
//                boolean isUserExists = nguoiDungDAO.CheckUser(newUsername, newPassword);


//                if (isUserExists) {
//                    Toast.makeText(Signup.this, "Tên đăng nhập đã tồn tại.", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Thêm tài khoản mới vào cơ sở dữ liệu (cần viết phương thức thêm tài khoản trong DAO)
//                    boolean isSignUpSuccess = nguoiDungDAO.Ad(newUsername, newPassword);
//
//                    if (isSignUpSuccess) {
//                        Toast.makeText(Signup.this, "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
//                        // Chuyển hướng đến màn hình đăng nhập hoặc màn hình khác tùy theo quy trình đăng ký của bạn.
//                    } else {
//                        Toast.makeText(Signup.this, "Đăng ký thất bại.", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });
    }

//    public void onLoginButtonClick() {
//        Intent intent = new Intent(Signup.this, Login.class); // Thay B.class bằng tên Activity B
//        startActivity(intent);
//    }
}