package uz.fozilbekimomov.geographyquiz;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;

public class QuizActivity_old extends AppCompatActivity {

    private final ArrayList<QuestionData> data = new ArrayList<>();
    private final int MAX_TIME = 20;
    private final int MIN_TIME = 1;
    private final int DELTA_TIME = 1;
    boolean isFinished = false;
    private SeekBar seekBar;
    private TextView currentView, totalView, finishButton, checkButton, questionView;
    private RadioGroup answerGroup;
    private RadioButton variantA;
    private RadioButton variantB;
    private RadioButton variantC;
    private QuestionManager manager;
    private boolean isAnswered = false;
    private CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        loadViews();
        setStateViews();
        setListener();
        loadData();
        manager = new QuestionManager(data);
        startQuiz();
    }

    private ValueAnimator valueAnimator;

    private void startQuiz() {

        questionView.setText(manager.getQuestion());
        variantA.setText(manager.getVariantA());
        variantB.setText(manager.getVariantB());
        variantC.setText(manager.getVariantC());

        currentView.setText(String.valueOf(manager.getCurrentLevel()));
        totalView.setText(String.valueOf(manager.getTotal()));

        valueAnimator = new ValueAnimator();

        valueAnimator.setDuration(20_000);
        valueAnimator.setFloatValues(100,1);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.d("QuizActivityTAG", "onTick: "+valueAnimator.getAnimatedFraction());
                int percentage = 100-(int) ((valueAnimator.getAnimatedFraction() * 100) % 100);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    seekBar.setProgress(percentage,true);
                }else {
                    seekBar.setProgress(percentage);
                }
            }
        });
        valueAnimator.start();


      /*  if (timer != null) {
            timer.cancel();
        }



        timer = new CountDownTimer(100_000, 1_000) {
            @Override
            public void onTick(long current) {
                Log.d("QuizActivityTAG", "onTick: "+current);
                double progress = current * 1.0 / 100_000 * 100;
                seekBar.setProgress((int) progress);
            }

            @Override
            public void onFinish() {

            }
        };

        timer.start();*/

    }

    private void clearView() {
        variantA.setBackgroundResource(R.drawable.radio_back);
        variantB.setBackgroundResource(R.drawable.radio_back);
        variantC.setBackgroundResource(R.drawable.radio_back);

        answerGroup.clearCheck();
    }

    private void loadData() {
        data.add(new QuestionData(
                "Dunyodagi eng baland cho'qqi nomini tanlang.",
                "Everest",
                "Jomulumba",
                "Everest",
                "Hazrati Sulton"
        ));
        data.add(new QuestionData(
                "Dunyodagi eng uzun daryo nomini tanlang.",
                "Nil",
                "Nil",
                "Amazonka",
                "Ganga"
        ));
        data.add(new QuestionData(
                "Dunyodagi eng sho'r ko'l nomini tanlang.",
                "O'lik dengiz",
                "Sho'rko'l",
                "Boltiq",
                "O'lik dengiz"
        ));
        data.add(new QuestionData(
                "Eng katta Okean nomini tanlang..",
                "Tinch okeani",
                "Tinch okeani",
                "Hind okeani",
                "Atlantika"
        ));
        data.add(new QuestionData(
                "Eng kichik davlat nomini tanlang.",
                "Vatikan",
                "Shir-Lanka",
                "Monaco",
                "Vatikan"
        ));
    }

    private void setListener() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean hasPressed = variantA.isChecked() || variantB.isChecked() || variantC.isChecked();

                if (isFinished) {
                    finish();
                } else {
                    if (hasPressed) {

                        if (isAnswered) {
                            if (!manager.isFinish()) {
                                clearView();
                                startQuiz();
                                checkButton.setText("Check");
                            } else {
                                isFinished = true;
                                checkButton.setText("Result");
                            }
                            isAnswered = false;
                        } else {
                            RadioButton button = findViewById(answerGroup.getCheckedRadioButtonId());
                            String answer = button.getText().toString();
                            boolean isTrue = manager.checkAnswer(answer);

                            if (isTrue) {
                                button.setBackgroundResource(R.drawable.radio_back_green);
                            } else {
                                button.setBackgroundResource(R.drawable.radio_back_red);
                            }
                            checkButton.setText("Next");
                            isAnswered = true;
                        }

                    } else {
                        Toast.makeText(QuizActivity_old.this, "Choose one of answers !!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    private void loadViews() {

        seekBar = findViewById(R.id.state_view);
        currentView = findViewById(R.id.current_question);
        totalView = findViewById(R.id.total_question);

        finishButton = findViewById(R.id.finish_test);
        checkButton = findViewById(R.id.check_answer);
        questionView = findViewById(R.id.question_view);

        answerGroup = findViewById(R.id.answer_group);

        variantA = findViewById(R.id.variant_a);
        variantB = findViewById(R.id.variant_b);
        variantC = findViewById(R.id.variant_c);



    }

    private void setStateViews() {
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }
}