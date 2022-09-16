package uz.fozilbekimomov.geographyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView play1, settings1, info1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();
        play1.setOnClickListener(this);
//        settings1.setOnClickListener(this);
        info1.setOnClickListener(this);

    }

    private void loadView() {

        play1=findViewById(R.id.start_quiz);
//        settings1=findViewById(R.id.settings);
        info1=findViewById(R.id.info);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start_quiz: {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.info: {
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivity(intent);
                break;
            }

        }

    }

}