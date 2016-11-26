package com.example.simplerpg;

import java.util.ArrayList;

public class Inventory {
    ArrayList<String> inv;
    public Inventory() { inv = new ArrayList<>(); }

    public void add(String _newItem) { inv.add(_newItem); }

    public void showAllItems(){
        System.out.println("Инвентарь:");
        System.out.println("0. Закончить осмотр");
        if(inv.size() > 0){
            for(int i = 0; i < inv.size(); i++)
                System.out.println((i + 1) + ". " + inv.get(i));
        }
        else
            System.out.println("Инвентарь пуст");
    }

    public String useItem(int _itemID) {
        if(_itemID == 0)
            return "";

        String a = inv.get(_itemID - 1);
        inv.remove(_itemID - 1);
        return a;
    }

    public int getSize() { return inv.size(); }
}
