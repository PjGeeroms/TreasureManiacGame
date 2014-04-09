/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author pieterjan
 */
public class Stickman extends Rectangle {
    
    public Stickman () {
        //setWidth(40);
        //setHeight(40);
        createStickman();
    }
    
    private boolean createStickman() {
        Rectangle head = new Rectangle(40, 40);
        Rectangle body = new Rectangle(20, 40);
        
        return true;
    }

}
