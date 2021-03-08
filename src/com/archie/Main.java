package com.archie;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static ArrayList<Domino> dominoList = new ArrayList<>();
    private static ArrayList<Player> playerList = new ArrayList<>();
    private static ArrayList<Domino> tableList = new ArrayList<>();

    public static void main(String[] args) {
        createDomino();
        displayAllDominoes();
        createPlayer();
        createPlayer();
        dealDominoes();
        System.out.println(playerList.get(0).toString());
        System.out.println(playerList.get(1).toString());
        highestDouble();
        System.out.println(playerList.get(0).toString());
        System.out.println(playerList.get(1).toString());
        displayBoneyard();
    }

    public static void createPlayer() {
        playerList.add(new Player(input("What is your name player "+ (playerList.size()+1)  +"?")));
    }

    public static void createDomino(){
        for (int leftNumber = 0; leftNumber <= 6; leftNumber++) {
            for (int rightNumber = 0; rightNumber <= leftNumber; rightNumber++) {
                dominoList.add(new Domino(leftNumber,rightNumber));
            }
        }
    }

    public static void dealDominoes() {
        for (int playerNumber = 0; playerNumber < playerList.size(); playerNumber++) {
            for (int i = 0; i < 7; i++) {
                playerList.get(playerNumber).addDomino(drawDomino());
            }
        }
    }

    public static Domino drawDomino() {
        Domino domino = dominoList.get(Random(0,dominoList.size()-1));
        dominoList.remove(domino);
        return domino;
    }

    public static void displayBoneyard() {
        System.out.println("Boneyard:");
        displayAllDominoes();
    }

    public static void displayTable() {
        System.out.println("\nTable:");
        System.out.print("Dominoes: ");
        tableList.forEach(domino -> System.out.print(domino+"  "));
        System.out.println();
    }

    private static void displayAllDominoes(){
        System.out.print("Dominoes: ");
        dominoList.forEach(domino -> System.out.print(domino+"  "));
        System.out.println();
    }

    private static void highestDouble(){
        int highestDouble = -1;
        int highestDoublePlayer = -1;
        while (highestDouble == -1) {
            for (int playerNumber = 0; playerNumber < playerList.size(); playerNumber++) {
                Player player = playerList.get(playerNumber);
                for (int i = 0; i < player.hand.size(); i++) {

                    int rightNumber = player.getDomino(i).rightNumber;
                    int leftNumber = player.getDomino(i).leftNumber;

                    if (rightNumber == leftNumber && rightNumber > highestDouble) {
                        highestDouble = rightNumber;
                        highestDoublePlayer = playerNumber;
                    }
                }
            }
            if (highestDouble == -1){
                for (Player player : playerList) {
                    player.addDomino(drawDomino());
                }
            }
        }
        start(highestDoublePlayer);
    }

    private static void start(int firstPlayer){
        move(playerList.get(firstPlayer));
        if (firstPlayer==0){
            move(playerList.get(1));
        }
        while (true){
            for (int playerNumber = 0; playerNumber < playerList.size(); playerNumber++) {
                move(playerList.get(playerNumber));
            }
        }
    }

    private static void move(Player player){
        System.out.println("\nIt is now "+player.getName()+"'s turn!\n");
        System.out.println(player.toString());
        boolean loop = true;
        while (loop) {
            switch (inputInt("Play(1) or Pass(2)")){
                case 1:
                    play(player);
                    loop = false;
                    break;
                case 2:
                    pass(player);
                    loop = false;
                    break;
                default:
                    System.out.println("Enter 1 or 2!");
            }
        }
        System.out.println(player.toString());
        displayBoneyard();
        displayTable();
    }


    private static void pass(Player player){
        player.addDomino(drawDomino());
    }

    private static void play(Player player){
        int dominoIndex = -1;
        while(dominoIndex < 0 || dominoIndex >= player.hand.size()){
            dominoIndex = (inputInt("Input the index of the domino you want to play (Starting at 1)."))-1;
        }
        Domino domino = player.getDomino(dominoIndex);
        tableList.add(domino);
        if (player.getDomino(dominoIndex).rightNumber == player.getDomino(dominoIndex).leftNumber) {
            player.removeDomino(domino);
            System.out.println(player.toString());
            boolean loop = true;
            while (loop) {
                switch (inputInt("Play(1) or Pass(2)")){
                    case 1:
                        play(player);
                        loop = false;
                        break;
                    case 2:
                        loop = false;
                        break;
                    default:
                        System.out.println("Enter 1 or 2!");
                }
            }
        }
        else {
            player.removeDomino(domino);
        }
    }

    private static String input(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.next();
    }

    private static int inputInt(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextInt();
    }

    private static int Random(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

}
