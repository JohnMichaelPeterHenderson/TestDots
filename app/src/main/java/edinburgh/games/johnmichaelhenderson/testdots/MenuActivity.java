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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.android.gms.games.Games;

public class MenuActivity extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    Button startGameBt;
    Button howToPlayBt;
    Button leaderBoardBt;
    View signInBt;
    Button signOutBt;
    ImageView iv;


    GoogleApiClient myClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startGameBt = (Button) findViewById(R.id.startGameBt);
        howToPlayBt = (Button) findViewById(R.id.howToPlayBt);
        leaderBoardBt = (Button) findViewById(R.id.leaderboardBt);
        signInBt = (View) findViewById(R.id.sign_in_button);
        signOutBt = (Button) findViewById(R.id.sign_out_button);
        iv = (ImageView) findViewById(R.id.banner);

        createClient();


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

        leaderBoardBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSignedIn()) {
                    Toast.makeText(getBaseContext(), "Display leaderboard", Toast.LENGTH_SHORT).show();
                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
                                    myClient, getString(R.string.leaderboard)),
                            2);
                }else{
                    Toast.makeText(getBaseContext(), "You must be signed in to see the leaderboardd", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginUserInitiatedSignIn();
                myClient.connect();
            }
        });

        signOutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                signInBt.setVisibility(View.VISIBLE);
                signOutBt.setVisibility(View.GONE);
                myClient.disconnect();
            }
        });


    }

    private void createClient() {
        Log.i("Create Client", "started");
        myClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }





    @Override
    public void onBackPressed() {
    }


    @Override
    public void onSignInFailed() {
        signOutBt.setVisibility(View.GONE);
        signInBt.setVisibility(View.VISIBLE);

    }

    @Override
    public void onSignInSucceeded() {
        signInBt.setVisibility(View.GONE);
        signOutBt.setVisibility(View.VISIBLE);

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("Connected", "Sucessful");
    }

    @Override
    public void onConnectionSuspended(int i) {
        myClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("Connected","Failed");

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
