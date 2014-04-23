/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Robin
 */
public class GameProcesses {

    private int maxRound;
    private int gameOver;           //0=niet voorbij, 1 is gewonnen, 2 is verloren
    private List<Treasure> allTreasures;
    private List<Monster> allMonsters;
    private int difference;             //Verschil tussen hero en monster en bijgevolg ook de moeilijkheidsgraad
    private Hero hero;

    public GameProcesses(int difference, Hero hero) {
        DomeinController controller = new DomeinController();
        gameOver=0;
        this.difference = difference;
        this.hero = hero;
        allMonsters = controller.searchAllMonsters();
        allTreasures = controller.searchAllTreasures();
        maxRound = allMonsters.size() / 4;             //Je kan telkens tegen een 4e van de mogelijke monsters vechten
    }

    public Chamber createRandomChamber() {
        if (Chamber.getRound() == maxRound) {
            return new Chamber();           //Dit is de BossChamber-> Vaste baas met vast beloning insteken
        }
        if(Chamber.getRound()>maxRound)
        {
            gameOver=1;
            return null;            //null teruggeven als het spel afgelopen is
        }

        Random random = new Random();

        List<Treasure> treasures = getValidTreasures();            //De valid-arrays correct initialiseren
        List<Monster> monsters = getValidMonsters();

        int monsterCount = monsters.size();
        int treasureCount = treasures.size();

        if (monsterCount > 0 && random.nextBoolean()) {       //random.nextBoolean bepaalt of een monster wordt gezet in de kamer of niet (als er monsters beschikbaar zijn)
            return new Chamber(monsters.get(random.nextInt(monsterCount)));     //Genereert een int tussen 0 en monsterCount-1
        } else if (treasureCount > 0 && random.nextBoolean()) {
            return new Chamber(treasures.get(random.nextInt(treasureCount)));
        } else {
            return new Chamber();
        }
    }

    private List<Treasure> getValidTreasures() {
        List<Treasure> validTreasures = new ArrayList<Treasure>();
        for (Treasure t : allTreasures) {
            if (((hero.getTotal() + difference) / 8 >= t.getTotal() && t.getTotal() >= (hero.getTotal() + difference) / 16)
                    || (t.getTotal() == 0
                    && ((t.getValue() <= (hero.getTotal() + difference) * 20) && t.getValue() <= (hero.getTotal() + difference) * 10))) {
                //Je kan in totaal een 8e van je huidige stats+difference bijkrijgen 
                //de values zijn afgestemd op hoe goed een item is en moeten dus niet apart getest worden
                //tenzij wanneer het gaat om een goudkist die geen stats omhoog doet en enkel een value heeft
                //treasures moeten een sterkte tussen een 8e en een 16e van die van hero+difference hebben
                //of een zuivere value tussen 10 en 20 keer de sterkte van hero+difference
                validTreasures.add(t);
            }
        }
        return validTreasures;
    }

    private List<Monster> getValidMonsters() {
        List<Monster> validMonsters = new ArrayList<Monster>();
        for (Monster m : allMonsters) {
            if (hero.getTotal() - m.getTotal() >= difference
                    && m.getTotal() >= (hero.getTotal() + difference) / 2) {
                //Monsters moeten minstens qua totale sterkte een difference onder die van hero liggen
                //en boven die van (hero+difference)/2
                validMonsters.add(m);
            }
        }
        return validMonsters;
    }

    //Deze methode hoort eerder in de gui thuis want is erg afhankelijk van invoer van de gebruiker
    public void initializeFight(Monster monster) {
        boolean fightOver = false;
        int turn=0;         //1 is hero's turn 2 monster
        while (!fightOver) {
            if (hero.calcTotalAwareness() > monster.calcTotalAwareness()||turn==1) {
                //Informatie monster tonen in gui (en keuze opvragen, namelijk aanvallen of vluchten)
                //controller.showOptions();
                fightOver=hero.flee(monster);           //Afhankelijk van keuze (slechts 1 van beide mogelijk)
                fightOver=hero.attack(monster);
                //Bij aanvallen en winnen -> Schatten opnemen
                //Bij vluchten -> Geen schatten opnemen
                turn=2;
            } else if (monster.calcTotalAwareness() > hero.calcTotalAwareness()||turn==2)
            {
                //Informatie monster tonen in gui, maar keuzes zijn ontoegankelijk (buttons disabled)
                fightOver=!hero.defend(monster);        //Als hero niet kon defenden is hij dood en is het gevecht voorbij
                gameOver=2;
                turn=1;
            }
            else
            {
                turn=Utility.generateRandom(1, 2);     //Geeft 1 of 2 als resultaat
            }   
        }
    }
    
    public int getScore()
    {
        return hero.getInventory().getInvValue();
    }
}
