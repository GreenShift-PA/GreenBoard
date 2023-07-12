package com.greenshift.greenboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;

public class TopBarController {

    @FXML
    public FontAwesomeIconView topBarHamburger;

    @FXML
    public void toggleLeftMenu() {
        System.out.println("Toggle left menu");
    }

}
