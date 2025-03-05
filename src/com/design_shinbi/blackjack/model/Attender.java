package com.design_shinbi.blackjack.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Attender {
	protected List<Card> cards;
    protected String name;
 
    public Attender(String name) {
        this.name = name;
        initialize();
    }
 
    public void initialize() {
        this.cards = new ArrayList<Card>();
    }
 
    public void start(Stock stock) {
        this.cards.clear();
        for(int i = 0; i < 2; i++) {
            Card card = stock.pickCard();
            this.cards.add(card);
        }
    }
 
    public String getName() {
        return name;
    }
 
    public void hit(Stock stock) {
        Card card = stock.pickCard();
        this.cards.add(card);
    }
 
    public static int calculateStrengthFromList(List<Card> list) {
        int strength = 0;
       
            boolean hasAce = false;   // A が 11 になるのは多くて1枚
                
            for(Card card : list) {     // それぞれのカードに対して
            	int number = card.getNumber();  // カードの数字　(1～13)
            	if(number > 10) { // 絵札
            		strength += 10;
            	}
            	else {
            		strength += number;
            	}
            	
            	if(number == 1) {    // A を持っている
            		hasAce = true;
            	}
            }
            
            if(hasAce && strength <= 11) {    // A を 1 ではなく 11 にする
            	strength += 10;
            }
            
            if(strength > 21) {    // 21 を越えたときはバースト
            	strength = 0;
            }

            return strength;
        }

    
   
    public int calculateStrength() {
        int strength = calculateStrengthFromList(this.cards);
        return strength;
    }
 
    public String toString() {
        String string = name + ": ";
        for(int i = 0; i < this.cards.size(); i++) {
            Card card = this.cards.get(i);
            string = string + card.toString();
        }
        return string;
    }
 
    public void display() {
        System.out.println(this.toString());
    }
 
    public abstract void play(Stock stock) throws IOException;
}

