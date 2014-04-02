package gui;

import javafx.geometry.Orientation;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author pieterjan
 */
public class GamePanel extends BorderPane{
    
    public GamePanel() {
        buildGui();
    }
    
    private void buildGui() {
        FlowPane main = new FlowPane(Orientation.HORIZONTAL);
        setCenter(main);
    }
    
}
