package edinburgh.games.johnmichaelhenderson.testdots;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;



public class MainActivity extends AppCompatActivity {
    protected int xScreenSize;
    protected int yScreenSize;
    //screen leftedge buffers
    private final int WIDTHBUFFER = 180;
    private final int HEIGHTBUFFER = 150;

    //screen rightedge buffers
    private final int MINWIDTHPARAM = 0;
    private final int MINHEIGHTPARAM = 50;

    //button closeness buffers
    private final int HEIGHTCLOSENESSBUFFER = 180;
    private final int WIDTHCLOSENESSBUFFER = 180;

    //buttom dimensions
    private final int BUTTONHW = 150;

    //iterators
    private int nextButtonToBeCreated = 1;
    private int nextButtonToBePressed = 1;

    //holders for coordinates
    private HashMap<Integer,Integer> xPositions = new HashMap<>();
    private HashMap<Integer,Integer> yPositions = new HashMap<>();

    //Countdown
    private long startTime = 2500;
    private final long noIntervals = 19;
    private long dummyInterval = 100;
    private final int[] colourList =  new int[19];

    //counts how many times in a row app tries to find position.
    private int noAttempts = 0;
    private final double PERCENTAGEOFF = 0.95;
    TextView scoreTextView;

    final boolean[] isFirstButton = {true};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setColours();
        final RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.mainLayout);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText("Welcome to Countdown Dots");


        //finding out how large screen is using listener, do all actions within this
        final ViewTreeObserver observer = rlayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //getting height and width of screen
                yScreenSize = rlayout.getHeight();
                xScreenSize = rlayout.getWidth();
                System.out.println("Height value is " + String.valueOf(yScreenSize));
                System.out.println("Width value is " + String.valueOf(xScreenSize));


                initialiseButtonView(rlayout, xScreenSize, yScreenSize);
                rlayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });
    }

    private void initialiseButtonView(final RelativeLayout layout, final int maxWidth, final int maxHeight){
        Log.i("Method called ", "initialiseButtonView");
        final Button bt = new Button(this);

        setButtonPosition(layout, maxWidth, maxHeight, bt);
        //      set timer
        layout.addView(bt);
        if(nextButtonToBePressed != 1){
            setTimer(bt);
        }else{
            bt.setVisibility(View.GONE);
            new CountDownTimer(startTime,dummyInterval){
                @Override
                public final void onTick(final long millisUntilFinished)
                {

                }
                @Override
                public final void onFinish()
                {
                    scoreTextView.setText("Click 1 to start game");
                    bt.setVisibility(View.VISIBLE);
                }

            }.start();
        }


        printHashMap();
        //change
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(nextButtonToBePressed ==1){
                    scoreTextView.setText("Score:"+String.valueOf(nextButtonToBePressed));
                    nextButtonToBePressed++;
                    xPositions.remove(Integer.valueOf(String.valueOf(bt.getText())));
                    yPositions.remove(Integer.valueOf(String.valueOf(bt.getText())));
                    layout.removeView(bt);
                    for(int i= 0;i<4;i++) {
                        initialiseButtonView(layout, xScreenSize, yScreenSize);
                    }
                    printHashMap();
                }else

                if (Integer.valueOf(String.valueOf(bt.getText())) == nextButtonToBePressed) {
                    Log.i("Method called: onClick", "User clicked" + bt.getText());
                    scoreTextView.setText("Score:" + String.valueOf(nextButtonToBePressed));
                    nextButtonToBePressed++;
                    xPositions.remove(Integer.valueOf(String.valueOf(bt.getText())));
                    yPositions.remove(Integer.valueOf(String.valueOf(bt.getText())));
                    layout.removeView(bt);
                    initialiseButtonView(layout, xScreenSize, yScreenSize);
                    printHashMap();
                } else {
                    scoreTextView.setText("You got "+String.valueOf(nextButtonToBePressed-1));
                    layout.removeAllViews();
                    nextButtonToBePressed = 1;
                    nextButtonToBeCreated = 1;
                    xPositions.clear();
                    yPositions.clear();
                    initialiseButtonView(layout, xScreenSize, yScreenSize);

                }
            }
        });

    }

    private void setButtonPosition(RelativeLayout layout, int maxWidth, int maxHeight, final Button button){
        Log.i("Method called", "setButtonPosition");
        button.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        //add/subtract buffers to keep away from edges
        int maxWidthParam = maxWidth - WIDTHBUFFER;
        int maxHeightParam = maxHeight - HEIGHTBUFFER;

        setRandomPositions(maxWidthParam, maxHeightParam, button);

        button.setText(String.valueOf(nextButtonToBeCreated));
        button.setWidth(BUTTONHW);
        button.setHeight(BUTTONHW);


        nextButtonToBeCreated++;

    }

    private void setTimer(final Button button) {
        long newStartTime  = workOutStartTime();
        final int timerValue =Integer.valueOf(String.valueOf(button.getText()));
        new CountDownTimer(newStartTime, newStartTime/noIntervals)
        {
            int colourIterator =0;
            @Override
            public final void onTick(final long millisUntilFinished)
            {
                button.setBackgroundColor(colourList[colourIterator]);
                colourIterator++;
            }
            @Override
            public final void onFinish()
            {
                if(nextButtonToBePressed > timerValue||nextButtonToBePressed==1){

                }else{
                    scoreTextView.setText("You got " + String.valueOf(nextButtonToBePressed));
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
                    layout.removeAllViews();
                    nextButtonToBePressed = 1;
                    nextButtonToBeCreated = 1;
                    xPositions.clear();
                    yPositions.clear();

                    initialiseButtonView(layout, xScreenSize, yScreenSize);
                }

            }
        }.start();
    }

    private long workOutStartTime() {

         long newStartTime = (long) (startTime*Math.pow(PERCENTAGEOFF,(nextButtonToBePressed-1)/10));
        return newStartTime;

    }

    private void setRandomPositions(int maxWidthParam, int maxHeightParam, Button button ){
        Log.i("Method called", "setRandomPositions");
        //create random coordinates
        Random random = new Random();
        int xTestValue = random.nextInt((maxWidthParam-MINWIDTHPARAM)+1) +MINWIDTHPARAM;
        int yTestValue = random.nextInt((maxHeightParam-MINHEIGHTPARAM)+1) +MINHEIGHTPARAM;

        Set<Integer> keys = xPositions.keySet();
        boolean overlaps = false;

        //checks if random coordinates overlap with any existing buttons and if not then sets the new position else call method again
        for(Integer i: keys){
            if(Math.abs(xTestValue-xPositions.get(i))< WIDTHCLOSENESSBUFFER && Math.abs(yTestValue-yPositions.get(i))< HEIGHTCLOSENESSBUFFER ) {
                overlaps = true;
                break;
            }
        }

        if(overlaps && noAttempts <10) {
            noAttempts++;
            setRandomPositions(maxWidthParam, maxHeightParam, button);
        }else{
            noAttempts =0;
            button.setX(xTestValue);
            button.setY(yTestValue);
            button.setTextSize(25);
            button.setTextColor(ContextCompat.getColor(this, R.color.white));
            xPositions.put(nextButtonToBeCreated, xTestValue);
            yPositions.put(nextButtonToBeCreated, yTestValue);
        }

    }


    private void printHashMap() {
        Set<Integer> keys = xPositions.keySet();  //get all keys
        for(Integer i: keys)
        {
            System.out.println(i +" "+xPositions.get(i));
        }
        System.out.println(nextButtonToBeCreated);
    }


    private void setColours() {
        colourList[0] = ContextCompat.getColor(this, R.color.color1);
        colourList[1] = ContextCompat.getColor(this, R.color.color2);
        colourList[2] = ContextCompat.getColor(this, R.color.color3);
        colourList[3] = ContextCompat.getColor(this, R.color.color4);
        colourList[4] = ContextCompat.getColor(this, R.color.color5);
        colourList[5] = ContextCompat.getColor(this, R.color.color6);
        colourList[6] = ContextCompat.getColor(this, R.color.color7);
        colourList[7] = ContextCompat.getColor(this, R.color.color8);
        colourList[8] = ContextCompat.getColor(this, R.color.color9);
        colourList[9] = ContextCompat.getColor(this, R.color.color10);
        colourList[10] = ContextCompat.getColor(this, R.color.color11);
        colourList[11] = ContextCompat.getColor(this, R.color.color12);
        colourList[12] = ContextCompat.getColor(this, R.color.color13);
        colourList[13] = ContextCompat.getColor(this, R.color.color14);
        colourList[14] = ContextCompat.getColor(this, R.color.color15);
        colourList[15] = ContextCompat.getColor(this, R.color.color16);
        colourList[16] = ContextCompat.getColor(this, R.color.color17);
        colourList[17] = ContextCompat.getColor(this, R.color.color18);
        colourList[18] = ContextCompat.getColor(this, R.color.color19);
    }



}