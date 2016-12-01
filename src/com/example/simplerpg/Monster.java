package com.example.simplerpg;

/**
 * Created by Никита on 26.11.2016.
 */

public class Monster extends GameCharacter {

    public Monster(String _charClass, String _name, int _strength, int _agility, int _stamina){
        super(_charClass, _name, _strength, _agility, _stamina);
        myInv = new Inventory();
        myInv.add(new Item("Слабое зелье лечения", Item.ItemType.Consumables));
        myInv.addSomeCoins(100);
    }

    public void lvlUp(int _1) {
        for (int i = 0; i < _1; i++) {
            showInfo();
            strength += base_strength * 0.3f;
            agility += base_agility * 0.3f;
            stamina += base_stamina * 0.3f;

            calculateSecondaryParameters();
            hp = hpMax;
            showInfo();
        }
    }
}
