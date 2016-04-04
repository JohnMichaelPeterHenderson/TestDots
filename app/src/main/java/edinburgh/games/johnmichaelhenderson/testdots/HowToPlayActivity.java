package edinburgh.games.johnmichaelhenderson.testdots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class HowToPlayActivity extends Activity {
    TextView TV2,scoreTV;
    Button Bt1,Bt2,Bt3,Bt4,Bt5;
    RelativeLayout layout;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        layout = (RelativeLayout) findViewById(R.id.helpLayout);
        Bt1 = (Button) findViewById(R.id.helpButton1);
        Bt2 = (Button) findViewById(R.id.helpButton2);
        Bt3 = (Button) findViewById(R.id.helpButton3);
        Bt4 = (Button) findViewById(R.id.helpButton4);
        Bt5 = (Button) findViewById(R.id.helpButton5);


        TV2 = (TextView) findViewById(R.id.helpInstruction2TV);

        scoreTV = (TextView) findViewById(R.id.helpScoreTV);

        resetViews();

        firstPhase();

    }

    private void resetViews(){
        Bt1.setVisibility(View.INVISIBLE);
        Bt2.setVisibility(View.INVISIBLE);
        Bt3.setVisibility(View.INVISIBLE);
        Bt4.setVisibility(View.INVISIBLE);
        Bt5.setVisibility(View.INVISIBLE);

        Bt1.setEnabled(false);
        Bt2.setEnabled(false);
        Bt3.setEnabled(false);
        Bt4.setEnabled(false);
        Bt5.setEnabled(false);

        Bt1.setText("");
        Bt2.setText("");
        Bt3.setText("");
        Bt4.setText("");
        Bt5.setText("");


        TV2.setText("");
        scoreTV.setText("");
    }

    private void firstPhase() {
        TV2.setText("Welcome to the Coontdoon, touch anywhere to continue.");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                secondPhase();
            }
        });
    }

    private void secondPhase(){
        scoreTV.setText("The aim of the game is to hit the squares, like this one, in numerical order and before each turns red.");
        Bt1.setVisibility(View.VISIBLE);
        Bt1.setEnabled(true);
        Bt1.setText("1");
        setButtonBackground(Bt1, 2);
        TV2.setText("Hit the square to continue.");
        Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                thirdPhase();
            }
        });
    }

    private void thirdPhase(){
        scoreTV.setText("Once clicked, a new square will appear.");
        Bt2.setVisibility(View.VISIBLE);
        Bt2.setEnabled(true);
        Bt2.setText("2");
        setButtonBackground(Bt2, 3);
        TV2.setText("Hit the square to continue.");
        Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                fourthPhase();
            }
        });
    }

    private void fourthPhase(){
        scoreTV.setText("This is where your score will appear, you get one point for every correctly selected square.");
        TV2.setText("Click anywhere to continue.");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                fifthPhase();
            }
        });
    }

    private void fifthPhase(){
        scoreTV.setText("Next the timers will be started on each square when it appears, try to hit them before they turn red!");
        TV2.setText("Let's try to score 5, click anywhere to continue.");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViews();
                sixthPhase();
            }
        });
    }

    private void sixthPhase(){
        minigame();

    }

    private void minigame(){
        boolean[] buttonsList = {false,false,false,false,false};
        score = 0;
        Bt1.setEnabled(true);
        Bt2.setEnabled(true);
        Bt3.setEnabled(true);
        Bt4.setEnabled(true);
        Bt5.setEnabled(true);
        Bt1.setVisibility(View.INVISIBLE);
        Bt2.setVisibility(View.INVISIBLE);
        Bt3.setVisibility(View.INVISIBLE);
        Bt4.setVisibility(View.INVISIBLE);
        Bt5.setVisibility(View.INVISIBLE);
        showButton(buttonsList);

    }

    private void showButton(final boolean[] buttonsList) {
        Log.i("showButton","methodCalled");


            int buttonNumber = pickButton(buttonsList);
            switch (buttonNumber) {
                case 0:
                    Log.i("showButton","Button 1 being shown");
                    Bt1.setVisibility(View.VISIBLE);
                    setButtonBackground(Bt1, 6);
                    Bt1.setText(String.valueOf(score+1));
                    Bt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("showButton","Button 1 clicked");
                            score++;
                            buttonsList[0] =true;
                            checkIfFinished(buttonsList);
                            Bt1.setVisibility(View.INVISIBLE);
                            showButton(buttonsList);

                        }
                    });
                    break;
                case 1:
                    Log.i("showButton","Button 2 being shown");
                    Bt2.setVisibility(View.VISIBLE);
                    setButtonBackground(Bt2, 6);
                    Bt2.setText(String.valueOf(score+1));
                    Bt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("showButton","Button 2 clicked");
                            score++;
                            buttonsList[1] =true;
                            checkIfFinished(buttonsList);
                            Bt2.setVisibility(View.INVISIBLE);
                            showButton(buttonsList);

                        }
                    });
                    break;
                case 2:
                    Log.i("showButton","Button 3 being shown");
                    Bt3.setVisibility(View.VISIBLE);
                    setButtonBackground(Bt3, 6);
                    Bt3.setText(String.valueOf(score+1));
                    Bt3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("showButton","Button 3 clicked");
                            score++;
                            buttonsList[2] =true;
                            checkIfFinished(buttonsList);
                            Bt3.setVisibility(View.INVISIBLE);
                            showButton(buttonsList);

                        }
                    });
                    break;
                case 3:
                    Log.i("showButton","Button 4 being shown");
                    Bt4.setVisibility(View.VISIBLE);
                    setButtonBackground(Bt4, 6);
                    Bt4.setText(String.valueOf(score+1));
                    Bt4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("showButton","Button 4 clicked");
                            score++;
                            buttonsList[3] =true;
                            checkIfFinished(buttonsList);
                            Bt4.setVisibility(View.INVISIBLE);
                            showButton(buttonsList);
                        }
                    });
                    break;
                case 4:
                    Log.i("showButton","Button 5 being shown");
                    Bt5.setVisibility(View.VISIBLE);
                    setButtonBackground(Bt5, 6);
                    Bt5.setText(String.valueOf(score+1));
                    Bt5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("showButton","Button 5 clicked");
                            score++;
                            buttonsList[4] =true;
                            checkIfFinished(buttonsList);
                            Bt5.setVisibility(View.INVISIBLE);
                            showButton(buttonsList);
                        }
                    });
                    break;

            }

    }

    private void checkIfFinished(boolean[] buttonsList){
        Log.i("checkIfFinished","methodC called");
        int howManyTrue = 0;
        for(int i = 0;i<buttonsList.length;i++){
            if(buttonsList[i]==true){
                howManyTrue++;
            }
        }
        Log.i("checkIfFinished","score is "+String.valueOf(score));
        Log.i("checkIfFinished","howManyTrue is "+String.valueOf(howManyTrue));
        if(howManyTrue == 5){
            Log.i("checkIfFinished","Going home");
            Intent goHome = new Intent(HowToPlayActivity.this, MenuActivity.class);
            startActivity(goHome);
        }else{
            Log.i("checkIfFinished","Showing next button");
            scoreTV.setText(String.valueOf(howManyTrue));
            showButton(buttonsList);
        }
    }

    private int pickButton(boolean[] buttonsList) {
        Log.i("pickButton","method called");
        Random rand = new Random();

        //first find out how many are false, if only one then returh that value.
        int  remainingIndex = 0 ;
        int howManyFalse =0;
        for(int i = 0;i<buttonsList.length;i++){
            if(buttonsList[i] == false){
                remainingIndex = i;
                howManyFalse++;
            }
        }
        if(howManyFalse == 1){
            return remainingIndex;
        }else {
            int temp = rand.nextInt(5);
            if (buttonsList[temp] == false) {
                Log.i("pickButton","returned "+String.valueOf(temp));
                return temp;

            } else {
                return pickButton(buttonsList);
            }
        }
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
                        if(score>= Integer.valueOf(String.valueOf(button.getText()))){

                        }else {
                            scoreTV.setText("You were too slow, try again");
                            minigame();
                            break;
                        }

                }

            }
        }.start();
    }
}
