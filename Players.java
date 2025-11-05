package tiei.ajp.lab1;

import tiei.ajp.lab1.Dice;

import java.util.Scanner;

public class Players {

    static final int LOWER_BOUND = 0;
    static final int UPPER_BOUND = 3;

    private int money;
    private int wins;
    private String name;
    private String type;
    private boolean table;

    private static int cnt;
    private static int remove = 0;
    private static int tie = 0;

    public Players(String name, String type, int money, boolean table){
        this.name = name;
        this.type = type;
        this.money = money;
        this.table = table;
        this.wins = 0;
        Players.tie = 0;
        Players.cnt ++;
    }

    public boolean getTable(){
        return this.table;
    }

    public void setWins(){
        this.wins ++;
    }

    public int getWins(){
        return this.wins;
    }

    public void setTie(){
        Players.tie ++;
    }

    public int getTie(){
        return Players.tie;
    }

    public void setTable(){
        this.table = false;
        Players.remove ++;
    }

    public int getMoney(){
        return this.money;
    }

    public void setMoney(int crease){
        this.money += crease;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public int getRemove(){
        return Players.remove;
    }

    public int getCount(){
        return Players.cnt;
    }

    public boolean checkCount(){
        if(cnt == remove + 1){
            return true;
        }else return false;
    }

    public int getEasyGame(int times, Dice dice){
        return dice.getNumber(times);
    }

    public int getRobotGame(Dice dice, int initCount){
        int times;
        if(initCount < 9){
            times = 3;
        }else if(initCount < 15){
            times = 2;
        }else{
            times = 1;
        }
        return dice.getNumber(times);
    }

    public int getHumanGame(Dice dice, Scanner in){
        System.out.printf("How many dice? From %d to %d. \n", LOWER_BOUND, UPPER_BOUND);
        int times = in.nextInt();
        while(times < 0 || times > 3){
            System.out.println("Wrong Number. Repeat.");
            System.out.printf("How many dice? From %d to %d. \n", LOWER_BOUND, UPPER_BOUND);
            times = in.nextInt();
        }
        in.nextLine();
        return dice.getNumber(times);
    }

}
