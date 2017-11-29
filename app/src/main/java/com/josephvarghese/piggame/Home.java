package com.josephvarghese.piggame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Random;

public class Home extends AppCompatActivity {

    Button roll,hold,newGame;
    TextView tagOne,tagTwo,totalOne,totalTwo,currentOne,currentTwo,winnerOne,winnerTwo;
    ImageView dice;


    MaterialDialog.Builder back;
    MaterialDialog backDialog;

    int user = 1;
    int current1 = 0,current2 = 0,total1 = 0,total2 = 0;

    int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);

        newGame = (Button)findViewById(R.id.newGame);

        tagOne = (TextView) findViewById(R.id.tagOne);
        tagTwo = (TextView) findViewById(R.id.tagTwo);

        totalOne = (TextView) findViewById(R.id.totalOne);
        totalTwo = (TextView) findViewById(R.id.totalTwo);

        currentOne = (TextView) findViewById(R.id.currentOne);
        currentTwo = (TextView) findViewById(R.id.currentTwo);

        winnerOne = (TextView)findViewById(R.id.winnerOne);
        winnerTwo = (TextView)findViewById(R.id.winnerTwo);

        dice = (ImageView)findViewById(R.id.dice);


        winnerOne.setVisibility(View.INVISIBLE);
        winnerTwo.setVisibility(View.INVISIBLE);
        newGame.setVisibility(View.INVISIBLE);
        newGame.setClickable(false);

        displayScore();


        back = new MaterialDialog.Builder(Home.this)
                .title("Exit")
                .content("Are you sure you want to exit?")
                .cancelable(false)
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        backDialog.dismiss();
                        finish();
                        //
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        //backDialog.dismiss();
                    }
                });

        backDialog = back.build();




        final Random random = new Random();



        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                val = random.nextInt(6) + 1;
                Log.d("Random Number ",Integer.toString(val));
                //or do 1 + (int) (Math.random() * max)

                if(val == 1){

                    dice.setImageResource(getImageId(getApplicationContext(),"dice" + Integer.toString(val)));

                    switchUser();

                }else {

                    dice.setImageResource(getImageId(getApplicationContext(),"dice" + Integer.toString(val)));

                    addCurrent(val);


                }


                displayScore();


            }
        });




        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holdTotal();

                checkWinner();

                switchUser();


                displayScore();


            }
        });



        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewGame();
            }
        });




    }


    public int getImageId(Context context,String imageName){
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }


    public void addCurrent(int val){
        if(user == 1){
            current1 += val;
        }else {
            current2 += val;
        }
    }


    public void holdTotal(){
        if(user == 1){
            total1 += current1;
        }else {
            total2 += current2;
        }
    }


    public void switchUser(){

        //needs to set the focusing part
        if(user == 1){
            current1 = 0;
            user = 2;
        }else{
            current2 = 0;
            user = 1;
        }
    }


    public void displayScore(){
        currentOne.setText(Integer.toString(current1));
        currentTwo.setText(Integer.toString(current2));

        totalOne.setText(Integer.toString(total1));
        totalTwo.setText(Integer.toString(total2));
    }


    public void checkWinner(){
        if(user == 1){
            if(total1 >= 50){
                winnerOne.setVisibility(View.VISIBLE);
                disablePlay();
            }
        }else{
            if(total2 >= 50){
                winnerTwo.setVisibility(View.VISIBLE);
                disablePlay();
            }
        }
    }


    public void startNewGame(){
        newGame.setVisibility(View.INVISIBLE);
        newGame.setClickable(false);

        winnerOne.setVisibility(View.INVISIBLE);
        winnerTwo.setVisibility(View.INVISIBLE);

        total1 = total2 = current1 = current2 = 0;
        displayScore();
        user = 1;

        enablePlay();
    }

    public void disablePlay(){
        roll.setClickable(false);
        roll.setVisibility(View.INVISIBLE);
        hold.setClickable(false);
        hold.setVisibility(View.INVISIBLE);
        newGame.setVisibility(View.VISIBLE);
        newGame.setClickable(true);
    }

    public void enablePlay(){
        roll.setClickable(true);
        roll.setVisibility(View.VISIBLE);
        hold.setClickable(true);
        hold.setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {

        backDialog.show();
    }
}
