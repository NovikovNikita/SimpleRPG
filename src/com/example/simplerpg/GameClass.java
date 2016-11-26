package com.example.simplerpg;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Никита on 26.11.2016.
 */
public class GameClass {
    public static Random rand = new Random();

    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];

    private Hero mainHero;
    private Monster currentMonster;
    private int currentRound;
    private int monsterID = 0;

    public GameClass(){
        initGame();
    }

    public void mainGameLoop(){
        Scanner sc = new Scanner(System.in);
        int inpInt = 0;
        System.out.println("Игра началась!");
        System.out.println("Выберите героя:");
        for (int i = 0; i < 3; i++)
            System.out.println((i + 1) + ". " + heroPattern[i].getName());
        inpInt = sc.nextInt();
        mainHero = (Hero)heroPattern[inpInt - 1].clone();
        System.out.println("Вы выбрали " + mainHero.getName());
        currentMonster = (Monster)monsterPattern[monsterID].clone();

        do {
            System.out.println("Текущий раунд: " + currentRound);
            mainHero.ShowInfo();
            currentMonster.ShowInfo();
            System.out.println("Ход игрока: 1. Атака 2. Защита 3. Пропустить ход 9. Завершить игру");
            mainHero.makeNewRound();
            inpInt = sc.nextInt();
            System.out.println("\n\n");
            if (inpInt == 1){
                currentMonster.getDamage(mainHero.makeAttack());
            }
            if (inpInt == 2){
                mainHero.setBlockStance();
            }
            if (inpInt == 9)
                break;
            currentMonster.makeNewRound();
            mainHero.getDamage(currentMonster.makeAttack());
            currentRound++;
            if (!currentMonster.isAlive) {
                System.out.println(currentMonster.getName() + " погиб");
                mainHero.expGain(currentMonster.getHpMax() * 2);
                monsterID++;
                if (monsterID < monsterPattern.length) {
                    currentMonster = (Monster) monsterPattern[monsterID].clone();
                    System.out.println("На поле боя выходит " + currentMonster.getName());
                } else
                    break;
            }
            if (!mainHero.isAlive)
                break;
        }
        while (true);
        if (!currentMonster.isAlive) System.out.println("Победил герой");
        if (!mainHero.isAlive) System.out.println("Победил " + currentMonster.getName());

        System.out.println("Игра завершена");
    }


    private void initGame(){
        heroPattern[0] = new Hero("Knight", "Lancelot", 500, 40, 5);
        heroPattern[1] = new Hero("Babrarian", "Konan", 1000, 20, 0);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 500, 15, 20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 250, 30, 2);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 380, 6 ,2);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 490, 7, 3);

        currentRound = 1;
    }

}
