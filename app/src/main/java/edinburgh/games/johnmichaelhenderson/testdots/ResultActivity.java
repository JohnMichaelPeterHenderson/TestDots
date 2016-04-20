package edinburgh.games.johnmichaelhenderson.testdots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameActivity;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;


public class ResultActivity extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    TextView scoreTV;
    TextView scoreTV1;
    TextView highScoreTV;
    TextView highScoreTV1;
    Button homeBt;
    Button restartBt;
    Button leaderBt;
    private int[] colourList =  new int[19];
    private String[] textList = new String[13];
    protected int xScreenSize;
    protected int yScreenSize;
    private final int WIDTHBUFFER = 100;
    private final int HEIGHTBUFFER = 150;
    private final int BUTTONHW = 150;
    private HashMap<Integer,Integer> xPositions = new HashMap<>();
    private HashMap<Integer,Integer> yPositions = new HashMap<>();
    private final int MINWIDTHPARAM = 0;
    private final int MINHEIGHTPARAM = 50;
    private final int HEIGHTCLOSENESSBUFFER = 180;
    private final int WIDTHCLOSENESSBUFFER = 180;
    private int noAttempts = 0;
    GoogleApiClient myClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent fromGame = getIntent();

        setColours();
        int score = fromGame.getIntExtra("Score",-1);


        homeBt = (Button) findViewById(R.id.homeBt);
        restartBt = (Button) findViewById(R.id.restartGameBt);
        leaderBt = (Button) findViewById(R.id.leaderboard1Bt);
        scoreTV = (TextView) findViewById(R.id.displayScoreTV);
        scoreTV1 = (TextView) findViewById(R.id.displayScoreTV1);
        highScoreTV = (TextView) findViewById(R.id.displayBestScoreTV);
        highScoreTV1 = (TextView) findViewById(R.id.displayBestScoreTV1);

        //scoreTV
        scoreTV.setText("You Scored");
          //scoreTV1
        scoreTV1.setText(String.valueOf(score));
        startScoreColour();

        checkConnection(score);



        final RelativeLayout rlayout =(RelativeLayout) findViewById(R.id.resultLayout);
        final ViewTreeObserver observer = rlayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //getting height and width of screen
                yScreenSize = rlayout.getHeight();
                xScreenSize = rlayout.getWidth();

                int numberOfButtons = 0;
                //every half second a new button appears
                createButton(xScreenSize, yScreenSize, rlayout, numberOfButtons);


                rlayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });

        homeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(ResultActivity.this, MenuActivity.class);
                startActivity(toHome);
            }
        });

        restartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGame = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(toGame);
            }
        });

        leaderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isSignedIn()) {
                    Toast.makeText(getBaseContext(), "Display leaderboard", Toast.LENGTH_SHORT).show();
                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
                                    myClient, getString(R.string.leaderboard)),
                            2);
                }else{
                    Toast.makeText(getBaseContext(),"You must be signed in to see the leaderboard",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkConnection(int score) {

        myClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


        if(isSignedIn()) {
            Toast.makeText(getBaseContext(), "Submitting score", Toast.LENGTH_SHORT).show();
            Games.Leaderboards.submitScore(myClient,
                    getString(R.string.leaderboard),
                    score);
        }
    }

    private void createButton(final int xScreenSize,final int yScreenSize,final RelativeLayout layout, final int numberOfButtons){
        final Button bt = new Button(this);
        new CountDownTimer(500000,650){
            @Override
            public final void onTick(final long millisUntilFinished)
            {

                final Button bt = new Button(getApplicationContext());
                setButtonPosition(layout, xScreenSize,yScreenSize,bt,numberOfButtons);
                Random random = new Random();
                int randNum = random.nextInt(18 + 1);
                switch(randNum){
                    case 0: bt.setBackgroundResource(R.drawable.background1);
                        break;
                    case 1: bt.setBackgroundResource(R.drawable.background2);
                        break;
                    case 2: bt.setBackgroundResource(R.drawable.background3);
                        break;
                    case 3: bt.setBackgroundResource(R.drawable.background4);
                        break;
                    case 4: bt.setBackgroundResource(R.drawable.background5);
                        break;
                    case 5: bt.setBackgroundResource(R.drawable.background6);
                        break;
                    case 6: bt.setBackgroundResource(R.drawable.background7);
                        break;
                    case 7: bt.setBackgroundResource(R.drawable.background8);
                        break;
                    case 8: bt.setBackgroundResource(R.drawable.background9);
                        break;
                    case 9: bt.setBackgroundResource(R.drawable.background10);
                        break;
                    case 10: bt.setBackgroundResource(R.drawable.background11);
                        break;
                    case 11: bt.setBackgroundResource(R.drawable.background12);
                        break;
                    case 12: bt.setBackgroundResource(R.drawable.background13);
                        break;
                    case 13: bt.setBackgroundResource(R.drawable.background14);
                        break;
                    case 14: bt.setBackgroundResource(R.drawable.background15);
                        break;
                    case 15: bt.setBackgroundResource(R.drawable.background16);
                        break;
                    case 16: bt.setBackgroundResource(R.drawable.background17);
                        break;
                    case 17: bt.setBackgroundResource(R.drawable.background18);
                        break;
                    case 18: bt.setBackgroundResource(R.drawable.background19);
                        break;
                }
                bt.getBackground().setAlpha(64);
                final float scale = getResources().getDisplayMetrics().density;
                final int[] dp = {20};
                int pixel= (int)(dp[0] * scale + 0.5f);
                bt.setLayoutParams(new RelativeLayout.LayoutParams(pixel, pixel));
                layout.addView(bt);
                bt.setEnabled(false);

                new CountDownTimer(4000,50){
                    int count = 0;
                    @Override
                    public final void onTick(final long millisUntilFinished){
                        if(count <=40) {
                            count++;
                            dp[0] = dp[0] + 1;
                            int newPixel = (int) (dp[0] * scale + 0.5f);
                            bt.setLayoutParams(new RelativeLayout.LayoutParams(newPixel, newPixel));
                        }else{
                            count++;
                            dp[0] = dp[0] - 1;
                            int newPixel = (int) (dp[0] * scale + 0.5f);
                            bt.setLayoutParams(new RelativeLayout.LayoutParams(newPixel, newPixel));
                        }
                    }
                    @Override
                    public final void onFinish(){
                        layout.removeView(bt);
                        xPositions.remove(numberOfButtons);
                        yPositions.remove(numberOfButtons);
                    }
                }.start();


            }
            @Override
            public final void onFinish()
            {
                createButton(xScreenSize, yScreenSize, layout,numberOfButtons);
            }
        }.start();
    }

    private void setButtonPosition(RelativeLayout layout, int maxWidth, int maxHeight, final Button button,int numberOfButtons){
        Log.i("Method called", "setButtonPosition");
        button.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        //add/subtract buffers to keep away from edges
        int maxWidthParam = maxWidth - WIDTHBUFFER;
        int maxHeightParam = maxHeight - HEIGHTBUFFER;

        setRandomPositions(maxWidthParam, maxHeightParam, button, numberOfButtons);

        button.setText(" ");
        button.setWidth(BUTTONHW);
        button.setHeight(BUTTONHW);

    }

    private void setRandomPositions(int maxWidthParam, int maxHeightParam, Button button,int numberOfButtons ){
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

        if (overlaps && noAttempts <10) {
            noAttempts++;
            setRandomPositions(maxWidthParam, maxHeightParam, button, numberOfButtons);
        }else{
            noAttempts =0;
            button.setX(xTestValue);
            button.setY(yTestValue);
            button.setTextColor(ContextCompat.getColor(this, R.color.white));
            xPositions.put(numberOfButtons, xTestValue);
            yPositions.put(numberOfButtons, yTestValue);
            numberOfButtons++;
        }
    }


    private void startScoreColour() {
        final int[] colourIterator = {1};
        final boolean[] direction ={true};
        new CountDownTimer(5000000,125){
            @Override
            public final void onTick(final long millisUntilFinished)
            {
                scoreTV.setTextColor(colourList[colourIterator[0]]);
                scoreTV1.setTextColor(colourList[colourIterator[0]]);
                highScoreTV.setTextColor(colourList[colourIterator[0]]);
                highScoreTV1.setTextColor(colourList[colourIterator[0]]);
                if(colourIterator[0] ==18){
                    colourIterator[0] = 17;
                    direction[0] = false;
                }else
                if(colourIterator[0] == 0){
                    colourIterator[0] = 1;
                    direction[0] = true;
                }else{
                    if(direction[0]){
                        colourIterator[0]++;
                    }else{
                        colourIterator[0]--;
                    }
                }

            }
            @Override
            public final void onFinish()
            {
                startScoreColour();
            }
        }.start();
    }

    private void startHighScoreColour() {
        final int[] colourIterator = {0};
        final boolean[] direction = {true};
        new CountDownTimer(5000000,10){
            @Override
            public final void onTick(final long millisUntilFinished)
            {
                highScoreTV.setText(Html.fromHtml(textList[colourIterator[0]]));
                if(colourIterator[0] == 12){

                    colourIterator[0] =0;
                }else{

                    colourIterator[0]++;
                }

            }
            @Override
            public final void onFinish()
            {
                startHighScoreColour();
            }
        }.start();
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

    private void setTexts() {
        textList[0] ="<font color=#ffbd00>Y</font><font color=#ffb400>o</font><font color=#ffab00>u</font><font color=#ffa200>r</font> <font color=#ff9900>H</font><font color=#ff9000>i</font><font color=#fd8702>g</font><font color=#f87b05>h</font><font color=#f36e09>s</font><font color=#ee620c>c</font><font color=#e95610>o</font><font color=#e34913>r</font><font color=#de3d16>e</font>";
        textList[1] ="<font color=#de3d16>Y</font><font color=#ffbd00>o</font><font color=#ffb400>u</font><font color=#ffab00>r</font> <font color=#ffa200>H</font><font color=#ff9900>i</font><font color=#ff9000>g</font><font color=#fd8702>h</font><font color=#f87b05>s</font><font color=#f36e09>c</font><font color=#ee620c>o</font><font color=#e95610>r</font><font color=#e34913>e</font>";
        textList[2] ="<font color=#e34913>Y</font><font color=#de3d16>o</font><font color=#ffbd00>u</font><font color=#ffb400>r</font> <font color=#ffab00>H</font><font color=#ffa200>i</font><font color=#ff9900>g</font><font color=#ff9000>h</font><font color=#fd8702>s</font><font color=#f87b05>c</font><font color=#f36e09>o</font><font color=#ee620c>r</font><font color=#e95610>e</font>";
        textList[3] ="<font color=#e95610>Y</font><font color=#e34913>o</font><font color=#de3d16>u</font><font color=#ffbd00>r</font> <font color=#ffb400>H</font><font color=#ffab00>i</font><font color=#ffa200>g</font><font color=#ff9900>h</font><font color=#ff9000>s</font><font color=#fd8702>c</font><font color=#f87b05>o</font><font color=#f36e09>r</font><font color=#ee620c>e</font>";
        textList[4] ="<font color=#ee620c>Y</font><font color=#e95610>o</font><font color=#e34913>u</font><font color=#de3d16>r</font> <font color=#ffbd00>H</font><font color=#ffb400>i</font><font color=#ffab00>g</font><font color=#ffa200>h</font><font color=#ff9900>s</font><font color=#ff9000>c</font><font color=#fd8702>o</font><font color=#f87b05>r</font><font color=#f36e09>e</font>";
        textList[5] ="<font color=#f36e09>Y</font><font color=#ee620c>o</font><font color=#e95610>u</font><font color=#e34913>r</font> <font color=#de3d16>H</font><font color=#ffbd00>i</font><font color=#ffb400>g</font><font color=#ffab00>h</font><font color=#ffa200>s</font><font color=#ff9900>c</font><font color=#ff9000>o</font><font color=#fd8702>r</font><font color=#f87b05>e</font>";
        textList[6] ="<font color=#f87b05>Y</font><font color=#f36e09>o</font><font color=#ee620c>u</font><font color=#e95610>r</font> <font color=#e34913>H</font><font color=#de3d16>i</font><font color=#ffbd00>g</font><font color=#ffb400>h</font><font color=#ffab00>s</font><font color=#ffa200>c</font><font color=#ff9900>o</font><font color=#ff9000>r</font><font color=#fd8702>e</font>";
        textList[7] ="<font color=#fd8702>Y</font><font color=#f87b05>o</font><font color=#f36e09>u</font><font color=#ee620c>r</font> <font color=#e95610>H</font><font color=#e34913>i</font><font color=#de3d16>g</font><font color=#ffbd00>h</font><font color=#ffb400>s</font><font color=#ffab00>c</font><font color=#ffa200>o</font><font color=#ff9900>r</font><font color=#ff9000>e</font>";
        textList[8] ="<font color=#ff9000>Y</font><font color=#fd8702>o</font><font color=#f87b05>u</font><font color=#f36e09>r</font> <font color=#ee620c>H</font><font color=#e95610>i</font><font color=#e34913>g</font><font color=#de3d16>h</font><font color=#ffbd00>s</font><font color=#ffb400>c</font><font color=#ffab00>o</font><font color=#ffa200>r</font><font color=#ff9900>e</font>";
        textList[9] ="<font color=#ff9900>Y</font><font color=#ff9000>o</font><font color=#fd8702>u</font><font color=#f87b05>r</font> <font color=#f36e09>H</font><font color=#ee620c>i</font><font color=#e95610>g</font><font color=#e34913>h</font><font color=#de3d16>s</font><font color=#ffbd00>c</font><font color=#ffb400>o</font><font color=#ffab00>r</font><font color=#ffa200>e</font>";
        textList[10] ="<font color=#ffa200>Y</font><font color=#ff9900>o</font><font color=#ff9000>u</font><font color=#fd8702>r</font> <font color=#f87b05>H</font><font color=#f36e09>i</font><font color=#ee620c>g</font><font color=#e95610>h</font><font color=#e34913>s</font><font color=#de3d16>c</font><font color=#ffbd00>o</font><font color=#ffb400>r</font><font color=#ffab00>e</font>";
        textList[11] ="<font color=#ffab00>Y</font><font color=#ffa200>o</font><font color=#ff9900>u</font><font color=#ff9000>r</font> <font color=#fd8702>H</font><font color=#f87b05>i</font><font color=#f36e09>g</font><font color=#ee620c>h</font><font color=#e95610>s</font><font color=#e34913>c</font><font color=#de3d16>o</font><font color=#ffbd00>r</font><font color=#ffb400>e</font>";
        textList[12] ="<font color=#ffb400>Y</font><font color=#ffab00>o</font><font color=#ffa200>u</font><font color=#ff9900>r</font> <font color=#ff9000>H</font><font color=#fd8702>i</font><font color=#f87b05>g</font><font color=#f36e09>h</font><font color=#ee620c>s</font><font color=#e95610>c</font><font color=#e34913>o</font><font color=#de3d16>r</font><font color=#ffbd00>e</font>";
    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        myClient.connect();

    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        myClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myClient.isConnected()) {
            myClient.disconnect();
        }
    }
}
