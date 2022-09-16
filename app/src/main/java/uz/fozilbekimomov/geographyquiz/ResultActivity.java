package uz.fozilbekimomov.geographyquiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    public static final String KEY_TRUES = "Tog'ri";
    public static final String KEY_MISTAKES = "Notog'ri";


    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();

        int trues = bundle.getInt(KEY_TRUES);
        int mistakes = bundle.getInt(KEY_MISTAKES);


        textView = findViewById(R.id.result_data);

        textView.setText("Tog'ri: " + trues + "\n" + "Notog'ri: " + mistakes + "\n");


    }
}