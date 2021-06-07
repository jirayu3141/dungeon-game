package unsw.dungeon;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Jirayu Sirivorawong & Yazed Al-Falhi
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private int index;
    private IntegerProperty x, y;
    private DoubleProperty o;
    protected String name;

    /**
     * Create an entity positioned in square (x,y)
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.o = new SimpleDoubleProperty(1);
        index = 0;
    }

    public Entity(int x, int y, int index) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.o = new SimpleDoubleProperty(1);
        this.index = index;
    }

    // ---getter methods ---//

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public DoubleProperty o() {
        return o;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public double getO(){
        return o().get();
    }

    public String getName() {
        return name;
    }

    /**
     * move entity to spcified location
     * 
     * @param x coor
     * @param y coor
     */
    public void move(int x, int y) {
        x().set(x);
        y().set(y);
    }

    /**
     * change entity opacity
     * 
     * @param o opacity
     * 
     */
    public void changeOpacity(double o) {
        o().set(o);
    }

    /**
     * getter method for index
     * @return index
     */
    public int getIndex() {
        return index;
    }

}
