package com.primo.memory_game;

import java.util.Random;

public class CardsManager {
    private static Random rnd = new Random();
    private int[][] mat;
    int[] pics = {
            R.drawable.disabled_card,
            R.drawable.car1,
            R.drawable.car2,
            R.drawable.car3,
            R.drawable.car4,
            R.drawable.car5,
            R.drawable.car6,
            R.drawable.car7,
            R.drawable.car8,
    };
    public CardsManager(){
        mat = new int[4][4];
        createRandomBoard(mat);
        System.out.println("generated Board");
        printMat(mat);
    }


    public int getPhoto(int i,int j){
        return pics[this.mat[i][j]];
    }

    public Boolean equals(int i1,int j1,int i2,int j2){
        if(this.mat[i1][j1] == this.mat[i2][j2]){
            this.mat[i1][j1] = 0;
            this.mat[i2][j2] = 0;
            return true;
        }
        return false;
    }
    public int getCardBack(int i,int j){
        return this.mat[i][j] == 0 ? R.drawable.disabled_card : R.drawable.back_of_card;
    }
    private void createRandomBoard(int[][] mat){
        int num = mat.length * mat[0].length/2;
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++) {
                int rndNum = rnd.nextInt(num)+1;
                while(countNumInBoard(mat,rndNum) >= 2)
                    rndNum = rnd.nextInt(num) +1;
                mat[i][j] = rndNum;
            }
    }
    private static void printMat(int[][] mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(" " + mat[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean canPress(int i,int j){
        return mat[i][j] != 0;
    }
    private int countNumInBoard(int[][] mat,int num){
        int counter = 0;
        for(int i = 0; i<mat.length;i++){
            for (int j = 0; j < mat[0].length ; j++) {
                if(num == mat[i][j]) counter++;
            }
        }
        return counter;
    }

}
