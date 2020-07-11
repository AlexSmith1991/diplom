package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gridPane = new GridPane();
        ArrayList<ColumnConstraints> columnConstraints = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            columnConstraints.add(new ColumnConstraints());
            columnConstraints.get(i).setPercentWidth(100);
            gridPane.getColumnConstraints().add(columnConstraints.get(i));
        }
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        gridPane.getRowConstraints().add(row1);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);
        gridPane.getRowConstraints().add(row2);
        gridPane.setGridLinesVisible(true);
        ArrayList<VBox> vBoxes = new ArrayList<>(24);
        for (int i = 0; i < 24; i++) {
            vBoxes.add(new VBox());
            gridPane.add(vBoxes.get(i), i % 12, i / 12);
        }
        vBoxes.get(10).
//        ArrayList<Button> buttons = new ArrayList<>(24);
//        for (int i = 0; i < 24; i++) {
//            buttons.add(new Button(String.valueOf(i)));
//            gridPane.add(buttons.get(i), i % 12, i / 12);
//        }

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(gridPane, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
