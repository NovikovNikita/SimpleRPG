package com.example.simplerpg;

/**
 * Created by Никита on 26.11.2016.
 */
public class Monster extends GameCharacter {
    public Monster(String _charClass, String _name, int _hp, int _attack, int _defense){
        super(_charClass, _name, _hp, _attack, _defense);
    }

    public Object clone(){
        try{
            return super.clone();
        }
        catch (CloneNotSupportedException e){
            System.out.println("Клонировать невозможно");
            return this;
        }
    }
}
