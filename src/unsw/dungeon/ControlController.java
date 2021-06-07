package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ControlController {
    @FXML
    private Button backBtn;

    /**
     * go back to menu screen
     * 
     */
    @FXML
    public void handleBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        loader.setController(new MenuController());
        Parent lvSelectParent = loader.load();
        Scene lvSelectScene = new Scene(lvSelectParent);
        // get the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(lvSelectScene);
        window.show();

    }
}