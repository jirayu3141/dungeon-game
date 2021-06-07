package unsw.dungeon;

import java.io.IOException;
import java.util.Scanner;


import java.io.FileReader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class LvSelectController {
    @FXML
    private Button backBtn;

    @FXML
    private Button lv1, lv2, lv3, lv4, lv5, lv6,lv7, lv8, lv9, lv10;
    
    // lines connecting each level
    @FXML
    private Line l2, l3, l4, l5,l6, l7, l8,l9, l10;


    private int currentLv;

    //constructor to read the current LV
    public LvSelectController() throws IOException {
        this.currentLv = Integer.parseInt(readFile("currentLv.txt"));
        
    }

    @FXML
    public void handleBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        loader.setController(new MenuController());
        Parent lvSelectParent = loader.load();
        Scene lvSelectScene = new Scene(lvSelectParent);
        //get the stage info and show it
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(lvSelectScene);
        window.show();

    }

    @FXML
    public void initialize() {
        Line lineArr[] = {l2, l3, l4, l5, l6, l7, l8, l9, l10};
        for (int i = 8 ; i > currentLv-2; i--) {
            lineArr[i].setVisible(false);
        }

    }

    @FXML
    public void handleLv1Btn(ActionEvent event) throws IOException {
        loadLevel("lv1.json", event, 1);
    }
    
    

    @FXML
    public void handleLv2Btn (ActionEvent event) throws IOException {
        if (currentLv < 2) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv2.json", event, 2);
        }
        
    }
    @FXML
    public void handleLv3Btn(ActionEvent event) throws IOException {
        if (currentLv < 3) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv3.json", event, 3);
        }
    }
    @FXML
    public void handleLv4Btn(ActionEvent event) throws IOException {
        if (currentLv < 4) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv4.json", event, 4);
        }
    }
    @FXML
    public void handleLv5Btn(ActionEvent event) throws IOException {
        if (currentLv < 5) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv5.json", event, 5);
        }
    }
    @FXML
    public void handleLv6Btn(ActionEvent event) throws IOException {
        //todo
        if (currentLv < 6) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv6.json", event, 6);
        }
    }
    @FXML
    public void handleLv7Btn(ActionEvent event) throws IOException {
        if (currentLv < 7) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv7.json", event, 7);
        }
    }
    @FXML
    public void handleLv8Btn(ActionEvent event) throws IOException {
        if (currentLv < 8) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv8.json", event, 8);
        }
    }
    @FXML
    public void handleLv9Btn(ActionEvent event) throws IOException {
        if (currentLv < 9) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv9.json", event, 9);
        }
    }
    @FXML
    public void handleLv10Btn(ActionEvent event) throws IOException {
        if (currentLv < 10) {
            System.out.println("You have not unlocked the current lv");
        } else {
            loadLevel("lv10.json", event, 10);
        }
    }
   
    /**
     * this function reads the lv that we are at
     */
    public String readFile(String filename) throws IOException {
        Scanner in = new Scanner(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    /**
     * this method is for loading dungeon at a specified level
     * 
     * @param map .json file
     * @param level level indicator so we can update level in currentLv.txt
     * 
     */
    private void loadLevel(String map, ActionEvent event, int level) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);
        DungeonController controller = dungeonLoader.loadController(level);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        //get the stage info and show it
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}

