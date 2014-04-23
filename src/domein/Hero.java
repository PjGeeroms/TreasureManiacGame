//NIET voor iteratie 1
package domein;

/**
 *
 * @author Robin
 */
public class Hero {

    private int power;
    private int defense;
    private int speed;
    private int awareness;
    private String name;
    private String avatar;
    private Inventory inventory;
    private boolean alive;

    private final static int MAX = 8;                 //Minimum en maximum-waarden voor de eigenschappen
    private final static int MIN = 0;
    private final static int MAX_NAME = 15;

    /**
     * Create a Hero object with standard values
     */
    public Hero() {
        this("Player", "Hero.gif", 0, 0, 0, 0);
    }

    /**
     * Create a Hero object with custom values
     *
     * @param name Name of your hero
     * @param avatar The avatar of your hero
     * @param power power of your hero
     * @param defense defense of your hero
     * @param speed speed of your hero
     * @param awareness awareness of your hero
     */
    public Hero(String name, String avatar, int power, int defense, int speed, int awareness) {
        setName(name);
        setAvatar(avatar);
        setPower(power);
        setDefense(defense);
        setSpeed(speed);
        setAwareness(awareness);
        this.alive=true;
        inventory = new Inventory();
    }                                               //Bij het spel zelf ervoor zorgen dat alle nodige waarden zijn ingegeven, meerdere constructors zijn niet nodig

    /**
     * will return the hero's power
     *
     * @return the power of the hero
     */
    public int getPower() {
        return power;
    }

    /**
     * set the hero's power
     *
     * @param power changes the hero's power
     */
    public void setPower(int power) {
        this.power = (power >= MIN && power <= MAX) ? power : MIN;     //Eigenschappen liggen tussen 0 en 8
    }

    /**
     * will return the hero's defense
     *
     * @return the defense of the hero
     */
    public int getDefense() {
        return defense;
    }

    /**
     * set the hero's defense
     *
     * @param defense changes the hero's defense
     */
    public void setDefense(int defense) {
        this.defense = (defense >= MIN && defense <= MAX) ? defense : MIN;
    }

    /**
     * will return the hero's speed
     *
     * @return the speed of the hero
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set the hero's speed
     *
     * @param speed changes the hero's speed
     */
    public void setSpeed(int speed) {
        this.speed = (speed >= MIN && speed <= MAX) ? speed : MIN;
    }

    /**
     * will return the hero's awareness
     *
     * @return the awareness of the awareness
     */
    public int getAwareness() {
        return awareness;
    }

    /**
     * set the hero's awareness
     *
     * @param awareness changes the hero's awareness
     */
    public void setAwareness(int awareness) {
        this.awareness = (awareness >= MIN && awareness <= MAX) ? awareness : MIN;
    }

    /**
     * will return the name of the hero
     *
     * @return the hero's name
     */
    public String getName() {
        return name;
    }

    /**
     * set the hero's name
     *
     * @param name changes the hero's name
     */
    public void setName(String name) {
        this.name = (!name.equals("")) ? name : "Treasure Maniac";          //Naam is per default Treasure Maniac indien niets ingegeven
    }

    /**
     * will return the path of the avatar
     *
     * @return returns the path of the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * set the hero's avatar path
     *
     * @param avatar changes the path of the avatar, resulting in changing the
     * avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = (!avatar.equals("")) ? avatar : "";           //Ofwel een default-avatar ofwel geen
    }

    /**
     *
     * @return
     */
    public int calcTotalAwareness() {

        int total = awareness + inventory.getInvAwareness();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;         //Totaal=minimum van het vaste totaal (zonder randomwaarde) is 0
        }
        return total;
    }

    /**
     *
     * @return
     */
    public int calcTotalPower() {           //Totaal=Beginwaarde+bonuswaarden van items in inventory+random waarde tussen 1 en 6
        int total = power + inventory.getInvPower();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;
        }
        return total;
    }

    /**
     *
     * @return
     */
    public int calcTotalDefense() {
        int total = defense + inventory.getInvDefense();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;         //Totaal=minimum van het vaste totaal (zonder randomwaarde) is 0
        }
        return total;
    }

    /**
     *
     * @return
     */
    public int calcTotalSpeed() {
        int total = speed + inventory.getInvSpeed();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;         //Totaal=minimum van het vaste totaal (zonder randomwaarde) is 0
        }
        return total;
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    

    public int getTotal() {
        return power + defense + speed + awareness;           //Totaal van alle stats
    }

    public static int getMAX() {
        return MAX;
    }

    public static int getMIN() {
        return MIN;
    }

    public static int getMAX_NAME() {
        return MAX_NAME;
    }

    public boolean attack(Monster monster) //true if won, false if not
    {
        if (calcTotalPower() > monster.calcTotalDefense()) {        //Bij gelijkstand wint de verdediger (of random nemen)
            return true;
        }
        return false;
    }

    public boolean defend(Monster monster) {
        if (calcTotalDefense() >= monster.calcTotalPower()) {
            return true;
        }
        alive=false;
        return false;
    }
    
    public boolean flee(Monster monster)
    {
        if(calcTotalSpeed()>monster.calcTotalSpeed())
        {
            return true;
        }
        return false;
    }
    
    public boolean isDead()
    {
        return alive;
    }
}
