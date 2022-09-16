package uz.fozilbekimomov.geographyquiz;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private final ArrayList<QuestionData> data = new ArrayList<>();
    private final int MAX_TIME = 20;
    private final int MIN_TIME = 1;
    private final int DELTA_TIME = 1;
    boolean isFinished = false;
    private ProgressView progressView;
    private TextView currentView, totalView, finishButton, checkButton, questionView;
    private RadioGroup answerGroup;
    private RadioButton variantA;
    private RadioButton variantB;
    private RadioButton variantC;
    private QuestionManager manager;
    private boolean isAnswered = false;
    private final CountDownTimer timer = null;
    private ValueAnimator valueAnimator;

    private Button button;

    private FrameLayout frameLayout;

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

    private void startQuiz() {

        questionView.setText(manager.getQuestion());
        variantA.setText(manager.getVariantA());
        variantB.setText(manager.getVariantB());
        variantC.setText(manager.getVariantC());

        currentView.setText(String.valueOf(manager.getCurrentLevel()));
        totalView.setText(String.valueOf(manager.getTotal()));

     /*   valueAnimator = new ValueAnimator();

        valueAnimator.setDuration(20_000);
        valueAnimator.setFloatValues(100, 1);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.d("QuizActivityTAG", "onTick: " + valueAnimator.getAnimatedFraction());
                int percentage = 100 - (int) ((valueAnimator.getAnimatedFraction() * 100) % 100);
                progressView.setProgress(percentage);
                progressView.progressAnimate();
            }
        });
        valueAnimator.start();
*/

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
        variantA.setBackgroundResource(R.drawable.yellow);
        variantA.setEnabled(true);
        variantB.setBackgroundResource(R.drawable.yellow);
        variantB.setEnabled(true);
        variantC.setBackgroundResource(R.drawable.yellow);
        variantC.setEnabled(true);
        answerGroup.clearCheck();

    }

    private void loadData() {
        data.add(new QuestionData(
                "Islomning ustuni nechta?",
                "5ta",
                "2ta",
                "6ta",
                "5ta"
        ));
        data.add(new QuestionData(
                "Taxoratda nechta farz bor?",
                "4ta",
                "6ta",
                "4ta",
                "5ta"
        ));
        data.add(new QuestionData(
                "Qiyomat kunida nimadan birinchi bo'lib so'ralamiz?",
                "Namozdan",
                "Taxoratdan",
                "Namozdan",
                "Sarflangan Vaqtlardan"
        ));
        data.add(new QuestionData(
                "namozdigi farzlardan nechitasi Namozdan oldin?",
                "6ta",
                "8ta",
                "6ta",
                "5ta"
        ));
        data.add(new QuestionData(
                "Safda turganda oldida bo'sh joy bo'lsaham o'tmaslik makruh amalmi?",
                "Ha, joy bo'lsa o'tish kerak.",
                "Ha, joy bo'lsa o'tish kerak.",
                "Yo'q, makruh emas",
                "Endi bilib olaman"
        ));
        data.add(new QuestionData(
                "Taxorat mumkin bo'lmagan suvlar!",
                "gul suvi",
                "Dengiz suvi",
                "gul suvi",
                "Qor suvi"
        ));
        data.add(new QuestionData(
                "Tayannumda nechita farz bor?",
                "4ta",
                "4ta",
                "5ta",
                "8ta"
        ));
        data.add(new QuestionData(
                "G'uslda nechta farz bor?",
                "3ta",
                "5ta",
                "3ta",
                "7ta"
        ));
        data.add(new QuestionData(
                "Payg'ambarlarga tushirilgan Ilohiy kitoblarni soni nechita?",
                "4ta",
                "6ta",
                "4ta",
                "10ta"
        ));
    }

    private void setListener() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int trueCount = manager.getTotalTrues();
                int falseCount = manager.getTotalFalse();

                int key = 1;

                Bundle bundle = new Bundle();
                bundle.putInt(ResultActivity.KEY_TRUES,trueCount);
                bundle.putInt(ResultActivity.KEY_MISTAKES, falseCount);

                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

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
                                checkButton.setText("Tanlash");
                            }
                            else {
                                isFinished = true;
                                checkButton.setText("Natijani ko'rish");

                                frameLayout.setVisibility(View.VISIBLE);

                            }
                            isAnswered = false;
                        } else {
                            RadioButton button = findViewById(answerGroup.getCheckedRadioButtonId());
                            String answer = button.getText().toString();
                            boolean isTrue = manager.checkAnswer(answer);

                            if (isTrue) {
                                button.setBackgroundResource(R.drawable.green);
                            } else {
                                button.setBackgroundResource(R.drawable.folse);
                            }

                            variantA.setEnabled(variantA.isChecked());
                            variantB.setEnabled(variantB.isChecked());
                            variantC.setEnabled(variantC.isChecked());

                            checkButton.setText("Keyingisi");
                            isAnswered = true;
                        }

                    } else {
                        Toast.makeText(QuizActivity.this, "Choose one of answers !!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    private void loadViews() {

        frameLayout = findViewById(R.id.finish_view);
        button = findViewById(R.id.button);

        progressView = findViewById(R.id.state_view);
        currentView = findViewById(R.id.current_question);
        totalView = findViewById(R.id.total_question);

        finishButton = findViewById(R.id.finish_test);
        checkButton = findViewById(R.id.check_answer);
        questionView = findViewById(R.id.question_view);

        answerGroup = findViewById(R.id.answer_group);

        variantA = findViewById(R.id.variant_a);
        variantB = findViewById(R.id.variant_b);
        variantC = findViewById(R.id.variant_c);

        progressView.setDuration(20_000);
        progressView.setMax(100);

    }

    private void setStateViews() {
        progressView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void openResult(View view) {
    }
}