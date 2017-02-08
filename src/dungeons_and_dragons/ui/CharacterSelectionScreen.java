package dungeons_and_dragons.ui;

import dungeons_and_dragons.entity.Characters;
import dungeons_and_dragons.util.DatabaseQuery;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Sean Peel
 */
public class CharacterSelectionScreen extends Application {

    private boolean DEBUG = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        DatabaseQuery dbQuery = new DatabaseQuery();
        List chars = dbQuery.executeQuery("select char FROM Characters char, Users user "
                + "where char.userId = user.id");

        ObservableList<String> characters = FXCollections.observableArrayList();

        ListView<String> list = new ListView<>();
        for (Object obj : chars) {
            Characters c = (Characters) obj;
            characters.add(c.getCharName());
        }

        list.setItems(characters);
        list.setPrefWidth(100);
        list.setPrefHeight(125);
        grid.add(list, 0, 1);

        Label charLabel = new Label("Characters");
        grid.add(charLabel, 0, 0);

        Button addButton = new Button("Add Character");
        addButton.setCursor(Cursor.HAND);
        HBox addBtnBox = new HBox(10);
        addBtnBox.setAlignment(Pos.TOP_RIGHT);
        addBtnBox.getChildren().add(addButton);
        grid.add(addBtnBox, 1, 1);

        Button loadButton = new Button("Load Character");
        loadButton.setCursor(Cursor.HAND);
        HBox loadBtnBox = new HBox(10);
        loadBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
        loadBtnBox.getChildren().add(loadButton);
        grid.add(loadBtnBox, 1, 2);

        loadButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    if (list.getSelectionModel().getSelectedItem() == null) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Character Selection Warning");
                        alert.setHeaderText("Please select a character!");
                        alert.show();
                    } else {
                        String charName = list.getSelectionModel().getSelectedItem();
                        CharacterScreen charScreen = new CharacterScreen(charName);
                        charScreen.start(new Stage());
                    }
                } catch (NullPointerException npe) {
                    new ErrorAlertWindow(Alert.AlertType.ERROR, npe);
                } catch (Exception ex) {
                    new ErrorAlertWindow(Alert.AlertType.ERROR, ex);
                }
            }

        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CharacterScreen charScreen = new CharacterScreen();
                    charScreen.start(new Stage());
                } catch (NullPointerException npe) {
                    new ErrorAlertWindow(Alert.AlertType.ERROR, npe);
                } catch (Exception ex) {
                    new ErrorAlertWindow(Alert.AlertType.ERROR, ex);
                }
            }

        });
        
        
        if (DEBUG) {
            grid.setGridLinesVisible(true);
        }

        Scene scene = new Scene(grid, 350, 275);

        primaryStage.setTitle("Dungeons & Dragons");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
