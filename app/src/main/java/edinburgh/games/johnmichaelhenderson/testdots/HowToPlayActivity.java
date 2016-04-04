package edinburgh.games.johnmichaelhenderson.testdots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class HowToPlayActivity extends Activity {
    TextView TV2,scoreTV;
    Button Bt1,Bt2,Bt3,Bt4,Bt5;
    RelativeLayout layout;
    int score;
    protected int xScreenSize;
    protected int yScreenSize;
    private final int WIDTHBUFFER = 200;
    private final int HEIGHTBUFFER = 170;
    private final int BUTTONHW = 150;
    private final int MINWIDTHPARAM = 16;
    private final int MINHEIGHTPARAM = 66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        layout = (RelativeLayout) findViewById(R.id.helpLayout);
        Bt1 = (Button) findViewById(R.id.helpButton1);
        Bt2 = (Button) findViewById(R.id.helpButton2);
        TV2 = (TextView) findViewById(R.id.helpInstruction2TV);
        scoreTV = (TextView) findViewById(R.id.helpScoreTV);

        final ViewTreeObserver observer = layout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //getting height and width of screen
                yScreenSize = layout.getHeight();
                xScreenSize = layout.getWidth();


                resetViews();

                firstPhase();
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });


    }

    private void resetViews(){
        Bt1.setVisibility(View.INVISIBLE);
        Bt2.setVisibility(View.INVISIBLE);

        Bt1.setEnabled(false);
        Bt2.setEnabled(false);


        Bt1.setText("");
        Bt2.setText("");



        TV2.setText("");
        scoreTV.setText("");
    }

    private void firstPhase() {
        TV2.setText("Welcome to the Coontdoon, tap anywhere to continue..");

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                secondPhase();
            }
        });
    }

    private void secondPhase(){
        layout.setOnClickListener(null);
        Bt1.setVisibility(View.VISIBLE);
        Bt1.setEnabled(true);
        Bt1.setText("1");
        setButtonBackground(Bt1, 2);
        TV2.setText("The aim of the game is to hit the squares, like this one, in numerical order and before they turn red. Hit the square to continue..");
        Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                thirdPhase();
            }
        });
    }

    private void thirdPhase(){
        Bt2.setVisibility(View.VISIBLE);
        Bt2.setEnabled(true);
        Bt2.setText("2");
        setButtonBackground(Bt2, 3);
        TV2.setText("Once clicked, a new square will appear. Hit the square to continue..");
        Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                fourthPhase();
            }
        });
    }

    private void fourthPhase(){
        scoreTV.setText("This is where your score will appear, you get one point for every correctly selected square. Click anywhere to continue..");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                fifthPhase();
            }
        });
    }

    private void fifthPhase(){
        layout.setOnClickListener(null);
        TV2.setText("Let's try a practice game, try to score 5. Click anywhere to start..");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                sixthPhase();
            }
        });
    }

    private void sixthPhase(){
        layout.setOnClickListener(null);
        minigame();

    }

    private void minigame(){

        score = 0;
        Bt1.setEnabled(true);
        Bt2.setEnabled(true);

        Bt1.setVisibility(View.INVISIBLE);
        Bt2.setVisibility(View.INVISIBLE);
        scoreTV.setGravity(Gravity.CENTER);
        scoreTV.setTextSize(40);
        showButton();

    }

    private void lostGame(){
        TV2.setText("Too slow! Let's try again, tap anywhere to continue..");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                sixthPhase();
            }
        });
    }

    private void seventhPhase(){
        TV2.setText("You did it, now you're ready for the real game. Tap anywhere to go back to the menu..");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(HowToPlayActivity.this, MenuActivity.class);
                startActivity(goHome);
            }
        });
    }

    private void showButton() {
        Log.i("showButton", "methodCalled");

            scoreTV.setText(String.valueOf(score));

            Log.i("showButton", "Button 1 being shown");
            final Button bt = new Button(this);
            setPosition(bt);
            setButtonBackground(bt, 6);


            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("showButton", "Button 1 clicked");
                    score++;
                    bt.setEnabled(false);
                    bt.setVisibility(View.INVISIBLE);

                    if (score < 5) {

                        showButton();
                    } else {
                       resetViews();
                        seventhPhase();
                    }


                }
            });


    }

    private void setPosition(Button bt1) {
        bt1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        //add/subtract buffers to keep away from edges
        int maxWidthParam = xScreenSize - WIDTHBUFFER;
        int maxHeightParam = yScreenSize - HEIGHTBUFFER;

        Random random = new Random();
        int xTestValue = random.nextInt((maxWidthParam-MINWIDTHPARAM)+1) +MINWIDTHPARAM;
        int yTestValue = random.nextInt((maxHeightParam-MINHEIGHTPARAM)+1) +MINHEIGHTPARAM;
        bt1.setText(String.valueOf(score + 1));
        bt1.setX(xTestValue);
        bt1.setY(yTestValue);
        bt1.setTextSize(25);
        bt1.setTextColor(ContextCompat.getColor(this, R.color.white));

        bt1.setWidth(BUTTONHW);
        bt1.setHeight(BUTTONHW);
        layout.addView(bt1);

    }





    private void setButtonBackground(final Button button,final int phase) {
        Log.i("setButtonBackground","method called");
        new CountDownTimer(4000, 4000 / 19) {
            int colourIterator = 1;
            @Override
            public final void onTick(final long millisUntilFinished) {
                switch (colourIterator) {
                    case 1:
                        button.setBackgroundResource(R.drawable.background1);
                        break;
                    case 2:
                        button.setBackgroundResource(R.drawable.background2);
                        break;
                    case 3:
                        button.setBackgroundResource(R.drawable.background3);
                        break;
                    case 4:
                        button.setBackgroundResource(R.drawable.background4);
                        break;
                    case 5:
                        button.setBackgroundResource(R.drawable.background5);
                        break;
                    case 6:
                        button.setBackgroundResource(R.drawable.background6);
                        break;
                    case 7:
                        button.setBackgroundResource(R.drawable.background7);
                        break;
                    case 8:
                        button.setBackgroundResource(R.drawable.background8);
                        break;
                    case 9:
                        button.setBackgroundResource(R.drawable.background9);
                        break;
                    case 10:
                        button.setBackgroundResource(R.drawable.background10);
                        break;
                    case 11:
                        button.setBackgroundResource(R.drawable.background11);
                        break;
                    case 12:
                        button.setBackgroundResource(R.drawable.background12);
                        break;
                    case 13:
                        button.setBackgroundResource(R.drawable.background13);
                        break;
                    case 14:
                        button.setBackgroundResource(R.drawable.background14);
                        break;
                    case 15:
                        button.setBackgroundResource(R.drawable.background15);
                        break;
                    case 16:
                        button.setBackgroundResource(R.drawable.background16);
                        break;
                    case 17:
                        button.setBackgroundResource(R.drawable.background17);
                        break;
                    case 18:
                        button.setBackgroundResource(R.drawable.background18);
                        break;
                    case 19:
                        button.setBackgroundResource(R.drawable.background19);
                        break;
                }

                colourIterator++;
            }

            @Override
            public void onFinish() {
                switch(phase){
                    case 6:
                        if(button.isEnabled()){
                            layout.removeView(button);
                            lostGame();
                            break;
                        }else {
                            layout.removeView(button);
                        }

                }

            }
        }.start();
    }



}
