package edu.pdx.cs410J.lakshmiy;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorPopup extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errorpopup);
                    TextView error_text = findViewById(R.id.Error);
                    Button errr_close = findViewById(R.id.err_button);
                    String msg = getIntent().getStringExtra("Error");
                    error_text.setText(msg);
                    errr_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ErrorPopup.this.finish();
                        }
                    });
    }
}
