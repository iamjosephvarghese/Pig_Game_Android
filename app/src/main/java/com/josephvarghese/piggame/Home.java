package com.josephvarghese.piggame;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Home extends AppCompatActivity {

    Button roll,hold;
    TextView tagOne,tagTwo,totalOne,totalTwo,currentOne,currentTwo;
    ImageView dice;

    int flag = 0;
    int current1,current2,total1,total2;

    int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);

        tagOne = (TextView) findViewById(R.id.tagOne);
        tagTwo = (TextView) findViewById(R.id.tagTwo);

        totalOne = (TextView) findViewById(R.id.totalOne);
        totalTwo = (TextView) findViewById(R.id.totalTwo);

        currentOne = (TextView) findViewById(R.id.currentOne);
        currentTwo = (TextView) findViewById(R.id.currentTwo);

        dice = (ImageView)findViewById(R.id.dice);


        final Random random = new Random();



        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //or do 1 + (int) (Math.random() * max)

                val = random.nextInt(6) + 1;
                Log.d("val",Integer.toString(val));


                dice.setImageResource(getImageId(getApplicationContext(),"dice" + Integer.toString(val)));

            }
        });





    }


    public int getImageId(Context context,String imageName){
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
