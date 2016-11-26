package com.example.simplerpg;

import java.util.Random;
import java.util.Scanner;


public class GameClass {
    public static Random rand = new Random();

    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];

    private Hero mainHero;
    private Monster currentMonster;
    private int currentRound;

    private Scanner sc = new Scanner(System.in);

    public GameClass(){ initGame(); }

    public void mainGameLoop() {
        int inpInt = 0;
        System.out.println("Игра началась!");

        String s = "Выберите героя: ";
        for (int i = 0; i < 3; i++)
            s += (i + 1) + ". " + heroPattern[i].getName() + "   ";
        inpInt = getAction(1, 3, s);

        mainHero = (Hero)heroPattern[inpInt - 1].clone();
        System.out.println("Вы выбрали " + mainHero.getName());
        currentMonster = (Monster)monsterPattern[0].clone();
        do {
            System.out.println("Текущий раунд: " + currentRound);
            mainHero.ShowInfo();
            currentMonster.ShowInfo();

            mainHero.makeNewRound();

            inpInt = getAction(0, 3, "Ход игрока: 1. Атака 2. Защита 3. Покопаться в сумке 0. Завершить игру");

            System.out.print("\n\n");
            if (inpInt == 1) {
                currentMonster.getDamage(mainHero.makeAttack());
                if (!currentMonster.isAlive()) {
                    System.out.println(currentMonster.getName() + " погиб");
                    mainHero.expGain(currentMonster.getHpMax() * 2);
                    mainHero.addKillCounter();
                    currentMonster = (Monster)monsterPattern[rand.nextInt(3)].clone();
                    System.out.println("На поле боя выходит " + currentMonster.getName());
                }
            }
            if (inpInt == 2)
                mainHero.setBlockStance();
            if (inpInt == 3) {
                mainHero.myInv.showAllItems();
                int invInput = getAction(0, mainHero.myInv.getSize(), "Выберите предмет для использования");
                String usedItem = mainHero.myInv.useItem(invInput);
                if (usedItem != "") {
                    System.out.println(mainHero.getName() + " использовал " + usedItem);
                    mainHero.useItem(usedItem);
                }
                else
                    System.out.println(mainHero.getName() + " просто закрыл сумку");
            }
            if (inpInt == 0) break;

            currentMonster.makeNewRound();

            if(rand.nextInt(100) < 80)
                mainHero.getDamage(currentMonster.makeAttack());
            else
                currentMonster.setBlockStance();

            if (!mainHero.isAlive())
                break;

            currentRound++;
        }
        while(true);

        if(currentMonster.isAlive() && mainHero.isAlive()) System.out.println(mainHero.getName() + " сбежал с поля боя");
        if(!currentMonster.isAlive()) System.out.println("Победил " + mainHero.getName());
        if(!mainHero.isAlive()) System.out.println("Победил " + currentMonster.getName());
        System.out.println("Игра завершена");
    }


    private void initGame(){
        heroPattern[0] = new Hero("Knight", "Lancelot", 18, 8, 11);
        heroPattern[1] = new Hero("Barbarian", "Konan", 15, 12, 13);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 12, 15, 16);
        monsterPattern[0] = new Monster("Humanoid", "Goblin", 12, 4, 6);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 18, 6, 6);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 32, 12, 10);
        currentRound = 1;
    }

    public int getAction(int _min, int _max, String _str)
    {
        int x = 0;
        do
        {
            if(_str != "" ) System.out.println(_str);
            x = sc.nextInt();
        } while (x < _min || x > _max);
        return x;
    }
}
