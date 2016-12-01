package com.example.simplerpg;

public class Hero extends GameCharacter {

    private int currentExp;
    private int expToNextLevel;
    private int killedMonsters;
    private int currentZone;
    private int posX;
    public int getX() { return posX; }
    public int getY() { return posY; }
    private int posY;

    public void setXY(int _x, int _y) {
        posX = _x;
        posY = _y;
    }

    public void moveHero(int _vx, int _vy) {
        posX += _vx;
        posY += _vy;
    }

    public int getZoneDangerous() { return currentZone; }

    public void goToDangerousZone() {
        currentZone++;
        System.out.println("Герой перешел в зону опасности " + currentZone);
    }

    public Hero(String _charClass, String _name, int _strength, int _agility, int _stamina){
        super(_charClass, _name, _strength, _agility, _stamina);
        currentZone = 0;
        currentExp = 0;
        expToNextLevel = 1000;
        killedMonsters = 0;
        myInv = new Inventory();
        myInv.add(new Item("Слабый камень здоровья", Item.ItemType.InfConsumables));
        myInv.add(new Item("Слабое зелье лечения", Item.ItemType.Consumables));
        myInv.addSomeCoins(1000);
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

    public void showInfo()
    {
        System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax + " Уровень: " + level + "[" +currentExp + " / " + expToNextLevel + "]");
    }
}
