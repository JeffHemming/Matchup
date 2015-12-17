package com.chauffy.Matchup;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameView extends Activity {
    public Tile[] board=new Tile[16];
    public int[] boardById=new int[16];
    public int[] boardwin=new int[16];
    public boolean [] tilecheck= new boolean[16];
    public boolean turn=true;
    public int currentC=0;
    public int currentK=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen);
        setupBoard();
    }

    public void setupBoard(){
        for(int i=0;i<16;i++){
            boolean gotit=false;
            int myr=-1;
            while(!gotit) {
                Random r = new Random();
                myr= r.nextInt(16);
                if (!tilecheck[myr]) gotit = true;
            }
            Tile temp=new Tile(myr);
            tilecheck[myr]=true;
            board[i]=temp;
            boardById[i]=myr;
            setTile(i,myr);
        }
    }

    public void displayTurn(){
        if(turn){
            TextView tv1 = (TextView)findViewById(R.id.textView);
            tv1.setText("First Player's Turn");
        }
        else{
            TextView tv1 = (TextView)findViewById(R.id.textView);
            tv1.setText("Second Player's Turn");
        }
    }

    public void setTile(int loc,int til){
        Button myButton;
        switch(loc) {
            case 0:
                myButton = (Button) findViewById(R.id.button1);
                break;
            case 1:
                myButton = (Button) findViewById(R.id.button2);
                break;
            case 2:
                myButton = (Button) findViewById(R.id.button3);
                break;
            case 3:
                myButton = (Button) findViewById(R.id.button4);
                break;
            case 4:
                myButton = (Button) findViewById(R.id.button5);
                break;
            case 5:
                myButton = (Button) findViewById(R.id.button6);
                break;
            case 6:
                myButton = (Button) findViewById(R.id.button7);
                break;
            case 7:
                myButton = (Button) findViewById(R.id.button8);
                break;
            case 8:
                myButton = (Button) findViewById(R.id.button9);
                break;
            case 9:
                myButton = (Button) findViewById(R.id.button10);
                break;
            case 10:
                myButton = (Button) findViewById(R.id.button11);
                break;
            case 11:
                myButton = (Button) findViewById(R.id.button12);
                break;
            case 12:
                myButton = (Button) findViewById(R.id.button13);
                break;
            case 13:
                myButton = (Button) findViewById(R.id.button14);
                break;
            case 14:
                myButton = (Button) findViewById(R.id.button15);
                break;
            case 15:
                myButton = (Button) findViewById(R.id.button16);
                break;
            case 16:
                myButton = (Button) findViewById(R.id.button);
                break;
            default:
                myButton=(Button) findViewById(R.id.button1);
                break;
        }

        switch(til){
            case 0:
                myButton.setBackgroundResource(R.drawable.nadiablue);
                break;
            case 1:
                myButton.setBackgroundResource(R.drawable.finnblue);
                break;
            case 2:
                myButton.setBackgroundResource(R.drawable.maesblue);
                break;
            case 3:
                myButton.setBackgroundResource(R.drawable.gwenblue);
                break;
            case 4:
                myButton.setBackgroundResource(R.drawable.nadiagreen);
                break;
            case 5:
                myButton.setBackgroundResource(R.drawable.finngreen);
                break;
            case 6:
                myButton.setBackgroundResource(R.drawable.maesgreen);
                break;
            case 7:
                myButton.setBackgroundResource(R.drawable.gwengreen);
                break;
            case 8:
                myButton.setBackgroundResource(R.drawable.nadiared);
                break;
            case 9:
                myButton.setBackgroundResource(R.drawable.finnred);
                break;
            case 10:
                myButton.setBackgroundResource(R.drawable.maesred);
                break;
            case 11:
                myButton.setBackgroundResource(R.drawable.gwenred);
                break;
            case 12:
                myButton.setBackgroundResource(R.drawable.nadiayellow);
                break;
            case 13:
                myButton.setBackgroundResource(R.drawable.finnyellow);
                break;
            case 14:
                myButton.setBackgroundResource(R.drawable.maesyellow);
                break;
            case 15:
                myButton.setBackgroundResource(R.drawable.gwenyellow);
                break;
            case 16:
                myButton.setBackgroundResource(R.drawable.p1);
                break;
            case 17:
                myButton.setBackgroundResource(R.drawable.p2);
                break;
        }
    }

    public void checkLegal(int t){
        if((currentC>0&&currentK>0)&&board[t-1].c!=currentC&&board[t-1].k!=currentK){
            return;
        }
            currentC=board[t-1].c;
            currentK=board[t-1].k;
            Button curr = (Button) findViewById(R.id.button);
            board[t-1].c=99;
            board[t-1].k=99;
            setTile(16, boardById[t - 1]);
            if(turn)boardwin[t-1]=1;
            else boardwin[t-1]=2;
            if(turn){
                setTile(t-1,16);
            }
            else{
                setTile(t-1,17);
            }
            if(checkForTie()){
                TextView tv1 = (TextView)findViewById(R.id.textView);
                tv1.setText("Tie Game!");
            }

            else if(checkForWin()){
                if(turn){
                    TextView tv1 = (TextView)findViewById(R.id.textView);
                    tv1.setText("First Player Wins!");
                }
                else{
                    TextView tv1 = (TextView)findViewById(R.id.textView);
                    tv1.setText("Second Player Wins!");
                }
                currentK=42;
                currentC=43;
            }
            else {
                turn = !turn;
                displayTurn();
            }
    }

    public boolean checkForTie(){
        for(int i=0;i<16;i++){
            if(boardwin[i]==0)return false;
        }
        return true;
    }

    public boolean checkForWin(){
        return (fourInARow()||fourInABox()||!availableMoves());
    }

    public boolean availableMoves(){
        for(int i=0;i<16;i++){
            if(board[i].k==currentK)return true;
            if(board[i].c==currentC)return true;
        }
        return false;
    }

    public boolean fourInARow(){
        for(int i=0;i<4;i++){
            if(boardwin[i]>0&&boardwin[i]==boardwin[i+4]&&boardwin[i]==boardwin[i+8]&&boardwin[i]==boardwin[i+12]){
                return true;
            }
        }
        for(int i=0;i<13;i+=4){
            if(boardwin[i]>0&&boardwin[i]==boardwin[i+1]&&boardwin[i]==boardwin[i+2]&&boardwin[i]==boardwin[i+3]){
                return true;
            }
        }
        return false;
    }

    public boolean fourInABox(){
        for(int i=0;i<11;i++){
            if(i!=3&&i!=7){
                if(boardwin[i]>0&&boardwin[i]==boardwin[i+1]&&boardwin[i]==boardwin[i+4]&&boardwin[i]==boardwin[i+5]){
                    return true;
                }
            }
        }
        return false;
    }

    public void click1(View view){
        checkLegal(1);
    }

    public void click2(View view){
        checkLegal(2);
    }

    public void click3(View view){
        checkLegal(3);
    }

    public void click4(View view){
        checkLegal(4);
    }

    public void click5(View view){
        checkLegal(5);
    }

    public void click6(View view){
        checkLegal(6);
    }

    public void click7(View view){
        checkLegal(7);
    }

    public void click8(View view){
        checkLegal(8);
    }

    public void click9(View view){
        checkLegal(9);
    }

    public void click10(View view){
        checkLegal(10);
    }

    public void click11(View view){
        checkLegal(11);
    }

    public void click12(View view){
        checkLegal(12);
    }

    public void click13(View view){
        checkLegal(13);
    }

    public void click14(View view){
        checkLegal(14);
    }

    public void click15(View view){
        checkLegal(15);
    }

    public void click16(View view){
        checkLegal(16);
    }
}
