/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Robin
 */
public class Chamber {
    private static int round=0;         //Houdt bij in welke ronde je zit
    private Treasure treasure;
    private Monster monster;
    private GameProcesses gp;

    public Chamber() {
        //Eventueel een random achtergrond hier selecteren
        //En eventueel een naam van de chamber
        round++;
    }
    
    public Chamber(Treasure treasure)
    {
        this();         //Zal achtergrond initialiseren en round optellen
        treasure=this.treasure;
    }
    
    public Chamber(Monster monster)
    {
        this();
        monster=this.monster;
    }
    
    public static int getRound()
    {
        return round;
    }
}
