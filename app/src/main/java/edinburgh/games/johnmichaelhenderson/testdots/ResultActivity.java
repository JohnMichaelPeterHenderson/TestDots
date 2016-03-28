package edinburgh.games.johnmichaelhenderson.testdots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
    TextView scoreTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent fromGame = getIntent();
        int score = fromGame.getIntExtra("Score",-1);
        scoreTV = (TextView) findViewById(R.id.displayScoreTV);
        scoreTV.setText("You scored "+String.valueOf(score));

    }
}
