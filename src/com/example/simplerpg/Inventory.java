package com.example.simplerpg;

import java.util.ArrayList;

public class Inventory {

    private int gold;
    ArrayList<Item> inv;
    public Inventory() { inv = new ArrayList<>(); }

    public void add(Item _newItem) {
        gold = 0;
        inv.add(_newItem);
    }

    public void addSomeCoins(int amout) {
        gold += amout;
    }

    public void showAllItems(){
        System.out.println("Инвентарь:");
        System.out.println("0. Закончить осмотр");
        if(inv.size() > 0){
            for(int i = 0; i < inv.size(); i++)
                System.out.println((i + 1) + ". " + inv.get(i).getName());
        }
        else
            System.out.println("Инвентарь пуст");
    }

    public String useItem(int _itemID) {
        if(_itemID == 0)
            return "";

        String a = inv.get(_itemID - 1).getName();
        inv.remove(_itemID - 1);
        return a;
    }

    public int getSize() { return inv.size(); }
}
