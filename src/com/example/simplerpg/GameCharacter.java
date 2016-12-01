package com.example.simplerpg;

public class GameCharacter implements Cloneable {
    protected String name;
    public String getName() { return name; }
    protected String charClass;

    protected int base_strength; // Primary stats
    protected int base_agility;
    protected int base_stamina;

    protected int strength; // Primary stats
    protected int agility;
    protected int stamina;

    protected int hpMax; // Secondary stats
    public int getHpMax() { return hpMax; }
    protected int attack;
    protected int defense;
    protected int critChanse;
    protected float critMultiplier;
    protected int dodgeChance;

    protected int level;
    protected int hp;
    protected boolean blockStance;
    protected boolean life;
    public  boolean isAlive() { return life; }

    protected Inventory myInv;

    public GameCharacter(String _charClass, String _name, int _strength, int _agility, int _stamina){
        name = _name;
        charClass = _charClass;
        strength = _strength;
        agility = _agility;
        stamina = _stamina;
        base_strength = _strength;
        base_agility = _agility;
        base_stamina = _stamina;
        calculateSecondaryParameters();
        level = 1;
        hp = hpMax;
        life = true;
        blockStance = false;
        critChanse = 17;
    }

    public void calculateSecondaryParameters()
    {
        attack = strength * 2;
        hpMax = stamina * 50;
        defense = (int)((strength + agility) / 4.0f);
        critChanse = agility * 1;
        critMultiplier = 1.2f + (float) (agility / 20.0f);
        dodgeChance = 8 + (int)(agility / 5.0f);
    }

    public void showInfo(){
        System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax);
    }

    public void setBlockStance(){
        blockStance = true;
        System.out.println(name + " стал в защитную стойку");
    }

    public void cure(int _val)
    {
        hp += _val;
        if(hp>hpMax) hp = hpMax;
    }

    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Клонирование невозможно");
            return this;
        }
    }

    public void makeNewRound(){
        blockStance = false;
    }

    public int makeAttack(){
        //20 -> 16...24
        int minAttack = (int)(attack * 0.8f);
        int deltaAttack = (int)(attack * 0.4f);
        int currentAttack = minAttack + Utils.rand.nextInt(deltaAttack);
        if(Utils.rand.nextInt(100) < critChanse){
            currentAttack = (int)(currentAttack * critMultiplier);
            System.out.println(name + " провел критический удар в размере " + currentAttack + " ед. урона");
        }
        else
            System.out.println(name + " провел атаку на " + currentAttack + " ед. урона");
        return currentAttack;
    }

    public void getDamage(int _inputDamage) // Метод получения урона
    {
        if(Utils.rand.nextInt(100) < dodgeChance)
        {
            System.out.println(name + " увернулся от атаки");
        }
        else
        {
            _inputDamage -= Utils.rand.nextInt(defense);
            if (blockStance)
            {
                System.out.println(name + " заблокировал часть урона");
                _inputDamage -= Utils.rand.nextInt(defense);
            }
            if (_inputDamage < 0) _inputDamage = 0;
            System.out.println(name + " получил " + _inputDamage + " ед. урона");
            hp -= _inputDamage;
            if(hp < 1)
                life = false;
        }
    }

    public void useItem(String _item)
    {
        switch(_item)
        {
            case "Слабое зелье лечения":
                cure(110);
                System.out.println(name + " пополнил здоровье на 50 ед.");
                break;
            case "Слабый камень здоровья":
                cure(60);
                System.out.println(name + " пополнил здоровье на 50 ед.");
                break;
        }
    }

    public void fullHeal() {
        hp = hpMax;
    }
}
