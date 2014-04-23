/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Robin
 */
public class NumberField extends GridPane {

    private int number;
    private TextField txf;
    private Button groter, kleiner;

    public NumberField() {
        initializeComponents();
    }

    public void initializeComponents() {
        number = 0;
        txf = new TextField(String.valueOf(number));
        txf.setEditable(false);
        groter = new Button(">");
        kleiner = new Button("<");

        add(kleiner, 0, 0);
        add(txf, 1, 0, 2, 1);
        add(groter, 3, 0);

        txf.setMaxWidth(100);
        txf.setMinWidth(100);
        txf.setMaxHeight(40);
        txf.setMinHeight(40);
        txf.setAlignment(Pos.CENTER);

        kleiner.setMaxHeight(40);
        kleiner.setMinHeight(40);
        kleiner.setMaxWidth(50);
        kleiner.setMinWidth(50);
        kleiner.setAlignment(Pos.CENTER);

        groter.setMaxHeight(40);
        groter.setMinHeight(40);
        groter.setMaxWidth(50);
        groter.setMinWidth(50);
        groter.setAlignment(Pos.CENTER);

        groter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if (number < max) {       //Controle op max en min gebeurt in HeroPanel zelf
                number++;
                setNumber(number);
                //}
            }
        }
        );

        kleiner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if (number > min) {
                number--;
                setNumber(number);
                //}
            }
        }
        );
    }

    public int getNumber() {
        return Integer.parseInt(txf.getText());
    }

    public void setNumber(int number) {
        this.number = number;
        txf.setText(String.valueOf(number));
    }

    /*
    public void reset() {
        number = 0;
        txf.setText(String.valueOf(0));
    }*/

    public StringProperty textProperty() {
        return txf.textProperty();
    }
}
