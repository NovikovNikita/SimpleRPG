package com.example.simplerpg;

/**
 * Created by Никита on 26.11.2016.
 */
public class Hero extends GameCharacter {
    private int currentExp;
    private int expToNextLevel;
    private int killedMonsters;
    public Inventory myInv;

    public Hero(String _charClass, String _name, int _strength, int _agility, int _stamina){
        super(_charClass, _name, _strength, _agility, _stamina);
        currentExp = 0;
        expToNextLevel = 1000;
        killedMonsters = 0;
        myInv = new Inventory();
        myInv.add("Слабое зелье лечения");
    }

    public void expGain(int _exp){
        currentExp += _exp;
        System.out.println(name + " получил " + _exp + " ед. опыта");
        if(currentExp > expToNextLevel){
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            attack += 5;
            hpMax += 50;
            strength += 2;
            agility += 2;
            stamina += 1;
            calculateSecondaryParameters();
            hp = hpMax;
            System.out.println(name + " повысил уровень до " + level);
        }
    }

    public void addKillCounter()
    {
        killedMonsters++;
    }

    public void ShowInfo()
    {
        System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax + " Уровень: " + level + "[" +currentExp + " / " + expToNextLevel + "]");
    }
}
