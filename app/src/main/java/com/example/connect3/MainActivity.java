package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

//import android.text.Layout;
//import android.widget.Button;

import android.os.Bundle;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    //0=yellow 1=red
    int activePlayer=0;
    boolean gameOn=true;
    String winner;
    int[] gameState  = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPosition = {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameOn)
        { gameState[tappedCounter] = activePlayer;
        counter.setTranslationY(-1500f);
           if (activePlayer == 0)
           {
            counter.setImageResource( R.drawable.yellow );
            activePlayer = 1;
           }
           else
               {
            counter.setImageResource( R.drawable.red );
            activePlayer = 0;
               }
        counter.animate().translationYBy( 1500f ).rotation( 360 ).setDuration( 1000 );

           for(int winningPosition[] : winningPosition)
           {
               if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]== gameState[winningPosition[2]]
               && gameState[winningPosition[0]]!=2) {
                   gameOn = false;
                   if (gameState[winningPosition[0]] == 0)
                       winner = "YELLOW";
                   else
                       winner = "RED";

                   TextView winneMessage = (TextView) findViewById( R.id.winnerMessage );
                   winneMessage.setText( winner + " has won!" );
                   LinearLayout vertiLayout = (LinearLayout) findViewById( R.id.vertiLayout );
                   vertiLayout.setVisibility( View.VISIBLE );
               }
               else
               {
                   boolean gameIsOver = true;
                   for(int counterState : gameState)
                       if(counterState == 2) gameIsOver = false;
                   if(gameIsOver)
                   {
                       TextView winneMessage = (TextView) findViewById( R.id.winnerMessage );
                       winneMessage.setText( "Its A DRAW" );
                      LinearLayout vertiLayout = (LinearLayout) findViewById( R.id.vertiLayout );
                       vertiLayout.setVisibility( View.VISIBLE );
                   }
               }


           }

        }

    }

    public void playAgain(View view)
    {
        System.out.println("Hello");
        gameOn = true;
       LinearLayout vertiLayout = (LinearLayout)findViewById(R.id.vertiLayout );
        vertiLayout.setVisibility( View.INVISIBLE );
        activePlayer = 0;
        for(int i=0; i<gameState.length;i++)
        { gameState[i] = 2;}
        GridLayout gridLayout = (GridLayout)findViewById( R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(android.R.color.transparent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }
}
