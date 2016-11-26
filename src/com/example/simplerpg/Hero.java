package com.example.simplerpg;

/**
 * Created by Никита on 26.11.2016.
 */
public class Hero extends GameCharacter implements Cloneable  {
    private int currentExp;
    private int expToNextLevel;

    public Hero(String _charClass, String _name, int _hp, int _attack, int _defense){
        super(_charClass, _name, _hp, _attack, _defense);
        initHero();
    }

    private void initHero(){
        currentExp = 0;
        expToNextLevel = 1000;
    }

    public void expGain(int _exp){
        currentExp += _exp;
        System.out.println(name + " получил " + _exp + " ед. опыта");
        if(currentExp > expToNextLevel){
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            attack += 5;
            System.out.println("Атака героя повысилась до " + attack + " ед. урона");
            hp = hpMax;
            System.out.println(name = " повысил уровень до " + level);
        }
    }

    public void ShowFullInfo(){

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
