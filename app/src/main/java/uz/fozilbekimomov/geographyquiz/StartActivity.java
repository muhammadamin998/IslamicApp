package uz.fozilbekimomov.geographyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button easy1, normal1, hard1, strelka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        loadView();
        strelka.setOnClickListener(this);
        easy1.setOnClickListener(this);
        normal1.setOnClickListener(this);
        hard1.setOnClickListener(this);

    }

    private void loadView() {

        easy1=findViewById(R.id.easy);
        normal1=findViewById(R.id.normal);
        hard1=findViewById(R.id.hard);
        strelka=findViewById(R.id.strelka);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.easy: {
                Intent intent = new Intent(StartActivity.this, QuizActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.normal: {
                Intent intent = new Intent(StartActivity.this, QuizNormal.class);
                startActivity(intent);
                break;
            }
            case R.id.hard: {
                Intent intent = new Intent(StartActivity.this, QuizHard.class);
                startActivity(intent);
                break;
            }
            case R.id.strelka: {
                finish();
                break;
            }
        }



    }}