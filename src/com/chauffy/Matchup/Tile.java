package com.chauffy.Matchup;

/**
 * Created by Dad on 12/17/2015.
 */
public class Tile {
    public int c=0;
    public int k=0;

    public Tile(int n){
        switch(n){
            case 0:c=1;
                k=1;
                break;
            case 1:c=1;
                k=2;
                break;
            case 2:c=1;
                k=3;
                break;
            case 3:c=1;
                k=4;
                break;
            case 4:c=2;
                k=1;
                break;
            case 5:c=2;
                k=2;
                break;
            case 6:c=2;
                k=3;
                break;
            case 7:c=2;
                k=4;
                break;
            case 8:c=3;
                k=1;
                break;
            case 9:c=3;
                k=2;
                break;
            case 10:c=3;
                k=3;
                break;
            case 11:c=3;
                k=4;
                break;
            case 12:c=4;
                k=1;
                break;
            case 13:c=4;
                k=2;
                break;
            case 14:c=4;
                k=3;
                break;
            case 15:c=4;
                k=4;
                break;
        }
    }
}
