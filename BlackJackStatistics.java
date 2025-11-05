package tiei.ajp.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Math;

import tiei.ajp.lab1.*;

public class BlackJackStatistics {

    // some const.
    static final int INITIAL_DEFAULT = 500;
    static final int LOWER_BOUND = 2;
    static final int UPPER_BOUND = 110000;
    static final int FIRST_ROLL = 3;
    static final int BONUS = 1;
    static final int PUNISHMENT = -1;
    static final int BLACKJACK_POINT = 21;

    private static int setNumbers(int num, int lower_bound, int upper_bound) {
        if (num < lower_bound || num > upper_bound) {
            System.out.printf("Invalid number of participants. Using default value: %d \n", INITIAL_DEFAULT);
            return INITIAL_DEFAULT;
        }
        return num;
    }

    private static void kickPlayer(ArrayList<Players> arr, int index){
        if(arr.get(index).getMoney() <= 0){
            // System.out.printf("%s is out! \n", arr.get(index).getName());
            arr.get(index).setTable();
        }
    }

    private static double averageRatioProcess(ArrayList<Players> arr, int turnCounter){
        double avg = 0;
        for(int i = 0 ; i < arr.size() ; i ++){
            avg += (double)arr.get(i).getWins() / turnCounter;
        }
        return (double)avg / arr.size();
    }

    private static void announceWinner(ArrayList<Players> arr, int bank, int turnCounter){
        System.out.println("\n");
        System.out.println("-------------------------------------------------");
        int key = -1;
        for(int i = 0 ; i < arr.size() ; i ++){
            if(arr.get(i).getTable() == true){
                key = i;
                break;
            }
        }
        if (key == -1) {
            System.out.println("All players are out. Bank wins everything.");
            System.out.printf("Bank earns %d. \n", bank);
            System.out.println("The game is over.");
            return;
       }
        // printPlayerStats(arr, arr.get(key).getName(), bank);
        double avg = averageRatioProcess(arr, turnCounter);
        System.out.printf("Winner is %s !!! , wons for %d chips (type %s) \n", arr.get(key).getName(), arr.get(key).getMoney(), arr.get(key).getType());
        System.out.printf("The winner's win rate:%.8f, which the average:%.8f \n", (double) arr.get(key).getWins() / turnCounter , avg);
        System.out.printf("Bank earns %d. \n", bank);
        System.out.println("The game is over.");
    }

    private static void announceStatistics(ArrayList<Players> arr, int turnCounter, long duration){
        System.out.printf("Tie times: %d. \n", arr.get(0).getTie());
        for(int i = 0 ; i < arr.size() ; i ++){
            System.out.printf("Ratio of player: %s : %4f. \n", arr.get(i).getName(), (double) arr.get(i).getWins() / turnCounter);
        }
        double seconds = (double) duration / 1000.0;
        System.out.printf("Total game duration: %.3f seconds. \n", seconds);
    }

    private static void transferChips(ArrayList<Players> arr, int receiver){
        int sender = -1;
        for(int i = 0 ; i < arr.size() ; i ++){
            if(arr.get(i).getTable() == false) continue;
            if(i == receiver) continue;
            sender = i;
            arr.get(sender).setMoney(PUNISHMENT);
            arr.get(receiver).setMoney(-PUNISHMENT);
        }
    }

    private static void printPlayerStats(ArrayList<Players> arr, String winnerName, int bank) {
        // System.out.println("\n--- Current Player Stats ---");
        // System.out.println("------------------------------------------------------------------");
        // System.out.printf("| %-15s | %-8s | %-6s | %-8s |\n", "Name", "Type", "Chips", "Outcome");
        // System.out.println("------------------------------------------------------------------");
        for (Players elem : arr) {
            if(elem.getTable() == false) continue;
            String outcomeIndicator = " ";
            if (elem.getName().equals(winnerName)) {
                outcomeIndicator = "*";
            } else if (winnerName.equals("Tie")) {
                outcomeIndicator = "-";
            }
    
            // System.out.printf("| %-15s | %-8s | %-6d | %-8s |\n", 
            //     elem.getName(), 
            //     elem.getType(), 
            //     elem.getMoney(), 
            //     outcomeIndicator);
        }
        // System.out.printf("Bank earns %d. \n", bank);
        // System.out.println("------------------------------------------------------------------");
    }

    private static boolean winMoney(ArrayList<Players> arr, int key, int pots){
        boolean flag = false;

        transferChips(arr, key);

        for(int i = arr.size() - 1 ; i >= 0 ; i --){
            if(arr.get(i).getTable() == false) continue;
            if(arr.get(i).getMoney() <= 0){
                kickPlayer(arr, i);
                if(arr.get(0).checkCount()){
                    flag = true;
                }
            }
        }

        if (key >= 0 && key < arr.size()) {
            arr.get(key).setMoney(pots);
            
            int chipsFromPlayers = (-PUNISHMENT) * (arr.get(0).getCount() - arr.get(0).getRemove() - 1);
            int totalChipsWon = pots + chipsFromPlayers;
            
            // System.out.printf("WinMoney: %s wins %d chips, %d from the pots & %d from the players.\n", 
            //                   arr.get(key).getName(), 
            //                   totalChipsWon, 
            //                   pots, 
            //                   chipsFromPlayers);
        } else {
            System.out.println("Error: Winner index is invalid after kicking players.");
        }
        return flag;
    }
    public static void main(String[] args) {
        // 1. Dealing with the start.
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the BlackJack! Giving the settings:");
        System.out.printf("How many initial chips? (default = %d. Number which larger than %d or less than %d will be invaild):\n", INITIAL_DEFAULT, LOWER_BOUND, UPPER_BOUND);
        int givenChips = in.nextInt();
        int chips = setNumbers(givenChips, LOWER_BOUND, UPPER_BOUND);
        System.out.printf("How many paticipants? (default = %d. Number which larger than %d or less than %d will be invaild):\n", INITIAL_DEFAULT, LOWER_BOUND, UPPER_BOUND);
        int givenPaticipants = in.nextInt();
        int paticipants = setNumbers(givenPaticipants, LOWER_BOUND, UPPER_BOUND);
        ArrayList<Players> arr = new ArrayList<>(paticipants);
        in.nextLine();
        for(int i = 0 ; i < paticipants ; i ++){
            String name, type; int money; boolean table = true; 
            // System.out.printf("Information of No.%d \n", i + 1);
            // System.out.println("name?");
            // name = in.nextLine();
            name = "User" + i;
            // System.out.println("human type?(y/n)");
            // String typeyn = in.next();
            String typeyn = "n";
            type = (typeyn.equalsIgnoreCase("y")) ? "human" : "virtual";
            money = chips;
            Players player = new Players(name, type, money, table);
            arr.add(player);
            // in.nextLine();
        }

        // 2.Gambling.
        Dice dice = new Dice();
        int pots = 0; int bank = 0; int turnCounter = 0;

        // 3.loop for the game.
        long startTime = System.currentTimeMillis();
        while(true){
            boolean BlackJack = false, system = false;
            int key = -1, value = -1, index = -1, cnt = 0;

            // 3.1.Turns for each players.
            for(Players elem : arr){
                
                // 3.1.1.Rolls.
                // System.out.println("\n");
                int score = 0, delta = 0; index ++;
                if(elem.getTable() == false) continue;
                // System.out.printf("That's %s for the first roll. \n", elem.getName());
                score = elem.getEasyGame(FIRST_ROLL, dice);
                // System.out.printf("First roll result: %d \n", score);
                // System.out.printf("That's %s for the second roll. \n", elem.getName());
                if("human".equalsIgnoreCase(elem.getType())){ 
                    delta = elem.getHumanGame(dice, in);
                }else if("virtual".equalsIgnoreCase(elem.getType())){
                    delta = elem.getRobotGame(dice, score);
                }
                // System.out.printf("Second roll result: %d \n", delta);
                score += delta;

                //3.1.2.Turn for one player.
                if(score == BLACKJACK_POINT){
                    // System.out.println("-------------------------------------------------");
                    // System.out.printf("BLACKJACK FOR %s !!\n", elem.getName());
                    // System.out.println("-------------------------------------------------");
                    key = index;
                    BlackJack = true;
                    break;
                }
                else if(score > BLACKJACK_POINT){
                    // punish player (by score within the round), not remove (by chips in hands).
                    // System.out.println("-------------------------------------------------");
                    // System.out.printf("BANG FOR %s !!\n", elem.getName());
                    // System.out.println("-------------------------------------------------");
                    elem.setMoney(PUNISHMENT);
                    pots += Math.abs(PUNISHMENT);
                    /* the reason why we set "kickPlayer()" here, is because the kick happens suddenly,
                     * exactly the time when one has no chips.
                     * (where the punishment, where the kick function).
                     */
                    // 3.1.2.1.Remove Players (by chips in hands)
                    kickPlayer(arr, index);
                    if(arr.get(0).checkCount()){
                        system = true;
                        break;
                    }
                }
                else{
                    // System.out.println("-------------------------------------------------");
                    // System.out.printf("Turn for %s is over. score = %d.\n", elem.getName(), score);

                    if(score > value){
                        // System.out.println("Highest now.");
                        key = index;
                        value = score;
                        cnt = 1;
                    }else if(score == value){
                        /* deal with one situlation, where some players (n > 1) got the same highest score.
                         * then, in that case, nobody won the chips in the pots.
                         * we can recognize this situation by only setting cnt > 1.
                         */
                        // System.out.println("Highest for the same.");
                        cnt ++;
                    }

                    // System.out.println("-------------------------------------------------");
                }
                // System.out.printf("That's the end of %s in this turn.\n", elem.getName());
            }

            // 3.2.Summary in the turn.
            // System.out.println("\n");
            // System.out.println("-------------------------------------------------");
            String currentWinner = "";
            boolean potToWinner = false;
            
            if(BlackJack == true){
                currentWinner = arr.get(key).getName();
                system = winMoney(arr, key, pots);
                potToWinner = true;
                if(arr.get(0).checkCount()){
                    break;
                }
            }
            else{
                // System.out.println("Nobody wins the BLACKJACK.");
                if(cnt > 1){
                    // System.out.println("scores are equal highest. Over.");
                    currentWinner = "Tie";
                }else if (key != -1){
                    currentWinner = arr.get(key).getName(); 
                    // System.out.printf("The winner for this turn is %s, score = %d \n", currentWinner, value);
                    system = winMoney(arr, key, pots);
                    potToWinner = true;
                    if(arr.get(0).checkCount()){
                        break;
                    }
                }else {
                    currentWinner = "None";
                }
            }

            /* 
             * ADD HERE FOR STATISTICS.
             * cnt > 1 means several votes are the same.
             * key == -1 means all BANG.
             */
            if(cnt > 1 || key == -1){
                arr.get(0).setTie();
            }else{
                arr.get(key).setWins();
            }
            
            if (potToWinner == false) {
                 bank += pots; 
            }
            pots = 0;
            if (system || arr.get(0).checkCount()) {
                break;
            }


            // printPlayerStats(arr, currentWinner, bank);
            
            // 3.3.Shuffle the players on the table.
            turnCounter++;
            Collections.shuffle(arr);
        }

        // 4.Summary the game
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        announceStatistics(arr, turnCounter, duration);
        announceWinner(arr, bank, turnCounter);
        in.close();
    }
}

/* 
 * the game is so much fun! i shared it with my friends.
 */
