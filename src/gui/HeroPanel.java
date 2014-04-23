/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Hero;
import exceptions.EmptyArgumentException;
import exceptions.ImageNotSelectedException;
import exceptions.OutOfRangeException;
import java.io.File;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JButton;

/**
 *
 * @author Robin
 */
public class HeroPanel extends GridPane {

    private DetailHero detail;
    private DomeinController controller;
    private int count;
    private Hero hero;
    private Label message;
    private final int maxCount = 8;

    private ImageView ivAvatar, ivPower, ivDefense, ivSpeed, ivAwareness;
    private static Image iDefaultAvatar = new Image(Main.class.getResourceAsStream("/images/heroes/Warrior.gif"));
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
    private ChoiceBox cbAvatars;
    private final static File folder = new File("./src/images/heroes/");
    private static File[] listOfFiles = folder.listFiles();

    private TextField txfName;
    private NumberField nfPower, nfDefense, nfSpeed, nfAwareness;
    private Label lblId, lblName, lblPower, lblDefense, lblSpeed, lblAwareness, lblAvatar;
    private Button createBtn;
    private MainPanel owner;

    private ChoiceBox fillAvatarChoiceBox() {
        ChoiceBox avatars = new ChoiceBox();
        String name;

        for (File avatar : listOfFiles) {
            if (avatar.isFile()) {
                name = avatar.getName();
                avatars.getItems().add(name.substring(0, (int) (name.length() - 4)));
//De extensie van de afbeelding moet niet zichtbaar zijn voor de speler
            }
        }
        return avatars;
    }

    public HeroPanel(DetailHero detail, MainPanel owner) {
        this.detail = detail;
        this.owner = owner;
        hero = new Hero();
        settings();
        buildPane();
    }

    private void settings() {
        getStyleClass().add("heropanel");
        getStylesheets().add("css/gamebackground.css");
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        message = new Label("* " + maxCount + " points remaining");
        message.setFont(Font.font("Arial", FontPosture.ITALIC, 15));

        lblName = new Label("Name");
        lblPower = new Label("Power");
        lblDefense = new Label("Defense");
        lblSpeed = new Label("Speed");
        lblAwareness = new Label("Awareness");
        lblAvatar = new Label("Avatar");

        txfName = new TextField(hero.getName());  //Zal defaultnaam Player zijn
        txfName.setPromptText(hero.getName());
        nfPower = new NumberField();
        nfDefense = new NumberField();
        nfSpeed = new NumberField();
        nfAwareness = new NumberField();

        createBtn = new Button("PLAY GAME");
    }

    private void setCounter(int counter) {
        message.setText("* " + counter + " points remaining");
    }

    private void buildPane() {
        ivAvatar = new ImageView(iDefaultAvatar);
        ivAvatar.setFitHeight(80);
        ivAvatar.setFitWidth(80);

        cbAvatars = fillAvatarChoiceBox();

        add(ivAvatar, 0, 0, 1, 2);
        add(detail, 1, 0, 1, 2);

        add(lblName, 0, 3);
        add(txfName, 1, 3);

        add(lblPower, 0, 4);
        add(nfPower, 1, 4);

        add(lblDefense, 0, 5);
        add(nfDefense, 1, 5);

        add(lblSpeed, 0, 6);
        add(nfSpeed, 1, 6);

        add(lblAwareness, 0, 7);
        add(nfAwareness, 1, 7);

        add(lblAvatar, 0, 8);
        add(cbAvatars, 1, 8, 2, 1);
        add(message, 0, 9, 2, 1);

        add(createBtn, 0, 12, 2, 1);
        setHalignment(createBtn, HPos.CENTER);

        count = 0;            //Aantal stats toegewezen

        //Maximuminvoer voorzien
        txfName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (newValue.length() >= Hero.getMAX_NAME()) {
                    txfName.setText(oldValue);
                }
                if (newValue.length() == 0) {
                    detail.setName(hero.getName());
                } else {
                    detail.setName(txfName.getText());
                }
            }
        });
        //Eventueel in ControlPanel ook maximuminvoer voorzien

        nfPower.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                int oldV = Integer.parseInt(oldValue);
                int newV = Integer.parseInt(newValue);

                if (newV > oldV) {
                    if (count < maxCount && newV <= maxCount) {
                        nfPower.setNumber(newV);
                        detail.setPower(newValue);
                        count++;
                        setCounter(maxCount - count);
                    } else {
                        count++;
                        nfPower.setNumber(oldV);
                        detail.setPower(oldValue);
                    }
                    /*
                     Er moet ook rekening gehouden worden met de extra textfieldwijzigingen bij
                     herzetten van de oldValue. Er vindt immers een count-- plaats wanneer je van de nieuwe hogere
                     value teruggaat naar de oudere, lagere value. Die moet je dus opheffen met een count++.
                     Als de value succesvol verhoogt werd, heb je ook een count++ want de stats zijn verhoogd.
                     */
                }
                if (newV < oldV) {
                    if (count > 0 && newV >= 0) {
                        nfPower.setNumber(newV);
                        detail.setPower(newValue);
                        count--;
                        setCounter(maxCount - count);
                    } else {
                        count--;
                        /*
                         count-- moet hier bovenaan staan (voor setNumber), omdat anders het terugzetten van -1 naar 0 niet zal plaatsvinden
                         indien count op maxCount staat, want dan kan de correcte if niet uitgevoerd worden om -1 terug op 0 te zetten
                         aangezien count<maxCount een voorwaarde daarvoor is.
                         */
                        nfPower.setNumber(oldV);
                        detail.setPower(oldValue);
                    }

                }
            }
        });

        nfDefense.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                int oldV = Integer.parseInt(oldValue);
                int newV = Integer.parseInt(newValue);

                if (newV > oldV) {
                    if (count < maxCount && newV <= maxCount) {
                        nfDefense.setNumber(newV);
                        detail.setDefense(newValue);
                        count++;
                        setCounter(maxCount - count);
                    } else {
                        count++;
                        nfDefense.setNumber(oldV);
                        detail.setDefense(oldValue);
                    }
                }
                if (newV < oldV) {
                    if (count > 0 && newV >= 0) {
                        nfDefense.setNumber(newV);
                        detail.setDefense(newValue);
                        count--;
                        setCounter(maxCount - count);
                    } else {
                        count--;
                        nfDefense.setNumber(oldV);
                        detail.setDefense(oldValue);
                    }
                }

            }
        });

        nfSpeed.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                int oldV = Integer.parseInt(oldValue);
                int newV = Integer.parseInt(newValue);

                if (newV > oldV) {
                    if (count < maxCount && newV <= maxCount) {
                        nfSpeed.setNumber(newV);
                        detail.setSpeed(newValue);
                        count++;
                        setCounter(maxCount - count);
                    } else {
                        count++;
                        nfSpeed.setNumber(oldV);
                        detail.setSpeed(oldValue);
                    }
                }
                if (newV < oldV) {
                    if (count > 0 && newV >= 0) {
                        nfSpeed.setNumber(newV);
                        detail.setSpeed(newValue);
                        count--;
                        setCounter(maxCount - count);
                    } else {
                        count--;
                        nfSpeed.setNumber(oldV);
                        detail.setSpeed(oldValue);
                    }
                }

            }
        });

        nfAwareness.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                int oldV = Integer.parseInt(oldValue);
                int newV = Integer.parseInt(newValue);

                if (newV > oldV) {
                    if (count < maxCount && newV <= maxCount) {
                        nfAwareness.setNumber(newV);
                        detail.setAwareness(newValue);
                        count++;
                        setCounter(maxCount - count);
                    } else {
                        count++;
                        nfAwareness.setNumber(oldV);
                        detail.setAwareness(oldValue);
                    }
                }
                if (newV < oldV) {
                    if (count > 0 && newV >= 0) {
                        nfAwareness.setNumber(newV);
                        detail.setAwareness(newValue);
                        count--;
                        setCounter(maxCount - count);
                    } else {
                        count--;
                        nfAwareness.setNumber(oldV);
                        detail.setAwareness(oldValue);
                    }
                }

            }
        });

        cbAvatars.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
                if (newVal != null) {
                    Image newImg = new Image(Main.class.getResourceAsStream("/images/heroes/" + newVal + ".gif"));
                    //Alle heroes zullen gifs zijn
                    ivAvatar.setImage(newImg);
                    hero.setAvatar(newVal + ".gif");
                }
            }
        }
        );

        createBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        String name = txfName.getText();
                        int power = nfPower.getNumber();
                        int defense = nfDefense.getNumber();
                        int speed = nfSpeed.getNumber();
                        int awareness = nfAwareness.getNumber();

                        if (name.length() > 0) //Anders wordt de defaultnaam Player gebruikt
                        {
                            hero.setName(name);
                        }
                        hero.setPower(power);
                        hero.setDefense(defense);
                        hero.setSpeed(speed);
                        hero.setAwareness(awareness);
                        owner.initializeHero(hero);
                        owner.getChildren().removeAll(HeroPanel.this);
                        owner.focusGame();
                    }
                }
        );

        cbAvatars.getSelectionModel().selectFirst();            //Uitvoeren na de listener te adden zodat avatar automatisch wordt geïnitialiseerd
        txfName.requestFocus();         //request focus nog niet correct
        txfName.selectAll();
    }
}
