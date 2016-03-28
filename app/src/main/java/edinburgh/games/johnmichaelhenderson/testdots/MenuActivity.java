package edinburgh.games.johnmichaelhenderson.testdots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {
    Button startGameBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startGameBt = (Button) findViewById(R.id.startGameBt);
        startGameBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGame = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(toGame);
            }
        });
    }
}
