package com.example.simplerpg;

/**
 * Created by Никита on 26.11.2016.
 */

public class Monster extends GameCharacter {
    public Monster(String _charClass, String _name, int _strength, int _agility, int _stamina){
        super(_charClass, _name, _strength, _agility, _stamina);
    }

    public void lvlUp(int _1) {
        for (int i = 0; i < _1; i++) {
            ShowInfo();
            strength += 2;
            agility += 1;
            stamina += 3;
            calculateSecondaryParameters();
            hp = hpMax;
            ShowInfo();
        }
    }
}
