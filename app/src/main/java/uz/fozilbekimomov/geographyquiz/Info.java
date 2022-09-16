package uz.fozilbekimomov.geographyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Info extends AppCompatActivity implements View.OnClickListener{

    private Button strelka11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        loadView();
        strelka11.setOnClickListener(this);
    }

    private void loadView() {
        strelka11=findViewById(R.id.strelka1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.strelka1: {
            finish();
            break;
        }
    }
}}