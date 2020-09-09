package com.primo.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    CardsManager manager;
    Player p1,p2,currentP;
    TextView p1Score,p2Score,curr;
    int chooseCount = 0;

    private ImageButton[][] imgBtns;
    int[] prev = {-1,-1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1Score = (TextView) findViewById(R.id.player1score);
        p2Score = (TextView) findViewById(R.id.player2score);
        curr = (TextView) findViewById(R.id.currentPlayer);


        this.manager = new CardsManager();
        this.p1 = new Player("player 1");
        this.p2 = new Player("player 2");
        this.currentP = p1;

        curr.setText("Current player: " +currentP.name);
        updateScore();
        imgBtns = new ImageButton[4][4];
        for (int i = 0; i < imgBtns.length; i++) {
            for (int j = 0; j < imgBtns[0].length; j++) {
                int id = getResources().getIdentifier("btn"+i+j,"id",getPackageName());
                imgBtns[i][j] = (ImageButton) findViewById(id);
                final int fi = i;
                final int fj = j;
                imgBtns[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onPress(fi,fj,(ImageButton) v);
                    }
                });
            }
        }

    }



    public void onPress(int i,int j,ImageButton v){
        if(chooseCount ==2){
            resetBoard();
            switchPlayer();
            chooseCount = 0;
            prev[0] = -1;
            prev[1] = -1;
            curr.setText("Current player: "+currentP.name);
        }
        if(manager.canPress(i,j)){
            chooseCount++;
            v.setImageResource(this.manager.getPhoto(i,j));
            if(chooseCount == 1){
                prev[0] = i;
                prev[1] = j;
            }
            if(chooseCount ==2&& manager.equals(prev[0],prev[1],i,j)){
                currentP.score++;
                updateScore();
            }

        }

    }
    public void resetBoard(){
        for (int i = 0; i < this.imgBtns.length; i++) {
            for (int j = 0; j < imgBtns[0].length; j++) {
                imgBtns[i][j].setImageResource(manager.getCardBack(i,j));
            }
        }
    }
    public void switchPlayer(){
        this.currentP = this.currentP == this.p1 ? this.p2:this.p1;
    }
    public void updateScore(){
        p1Score.setText(""+p1.score);
        p2Score.setText(""+p2.score);
    }

}