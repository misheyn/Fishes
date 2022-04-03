package com.company.JavaFish;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FishLogWindowController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private TextArea livingFishesTextField;

    @FXML
    void initialize() {
        AtomicReference<String> str = new AtomicReference<>("");
        FishArr.getInstance().linkedList.forEach(tmp -> {
            for (Map.Entry<Integer, Long> entry : FishArr.getInstance().treeMap.entrySet()) {
                Integer id = entry.getKey();
                Long birthTime = entry.getValue();
                if (tmp.getBirthTime() == birthTime) {
                    if (tmp instanceof GoldenFish) {
                        str.set(str + String.format("%3d GoldenFish %5d %5d\n", id, birthTime, birthTime + GoldenFish.lifeTime));
                    } else {
                        str.set(str + String.format("%3d GuppyFish  %5d %5d\n", id, birthTime, birthTime + GuppyFish.lifeTime));
                    }
                    break;
                }
            }
        });
        livingFishesTextField.setText(str.toString());
    }

}

