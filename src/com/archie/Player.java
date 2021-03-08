package com.archie;

import java.util.ArrayList;

public class Player {

    public String name;
    public ArrayList<Domino> hand = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Domino> getHand() {
        return hand;
    }

    public Domino getDomino(int index) {
        return hand.get(index);
    }

    public void addDomino(Domino domino) {
        hand.add(domino);
    }

    public void removeDomino(Domino domino) {
        hand.remove(domino);
    }

    @Override
    public String toString() {
        String dominoes= "Dominoes: ";
        for (int i = 0; i < hand.size(); i++) {
            dominoes += hand.get(i)+"  ";
        }
        return "Player: " + name + '\n' + dominoes + '\n';
    }
}
