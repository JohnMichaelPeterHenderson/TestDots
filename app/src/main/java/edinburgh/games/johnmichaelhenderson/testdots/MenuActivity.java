package edinburgh.games.johnmichaelhenderson.testdots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class MenuActivity extends Activity {
    Button startGameBt;
    Button howToPlayBt;
    Button leaderBoardBt;
    ImageView iv;

    protected int xScreenSize;
    protected int yScreenSize;
    private final int WIDTHBUFFER = 180;
    private final int HEIGHTBUFFER = 150;
    private final int BUTTONHW = 150;
    private HashMap<Integer,Integer> xPositions = new HashMap<>();
    private HashMap<Integer,Integer> yPositions = new HashMap<>();
    private final int MINWIDTHPARAM = 0;
    private final int MINHEIGHTPARAM = 50;
    private final int HEIGHTCLOSENESSBUFFER = 180;
    private final int WIDTHCLOSENESSBUFFER = 180;
    private int noAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startGameBt = (Button) findViewById(R.id.startGameBt);
        howToPlayBt = (Button) findViewById(R.id.howToPlayBt);
        leaderBoardBt = (Button) findViewById(R.id.leaderboardBt);
        iv = (ImageView) findViewById(R.id.banner);


        iv.setImageResource(R.mipmap.coontdoonbaner);
        startGameBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGame = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(toGame);
            }
        });

        howToPlayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInstructions = new Intent(MenuActivity.this, HowToPlayActivity.class);
                startActivity(toInstructions);
            }
        });



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
    @Override
    public void onBackPressed() {
    }



}
