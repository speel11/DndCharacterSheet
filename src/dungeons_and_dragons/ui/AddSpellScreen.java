package dungeons_and_dragons.ui;

import dungeons_and_dragons.entity.Spells;
import dungeons_and_dragons.util.DatabaseQuery;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Sean Peel
 */
public class AddSpellScreen extends Application {

    ListView list;

    AddSpellScreen(ListView list) {
        this.list = list;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root = new StackPane();

        ListView spellList = new ListView();
        root.getChildren().add(spellList);

        Scene scene = new Scene(root, 600, 400);

        DatabaseQuery dbQuery = new DatabaseQuery();
        List<Spells> spells = dbQuery.executeQuery("FROM Spells s where s.class_ like 'Warlock%'");

        for (Spells s : spells) {
            // if (!spellList.getItems().contains(s.getName())) {
            spellList.getItems().add(s.getName());
            // }
        }

        spellList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        int spellIndex = spellList.getSelectionModel().getSelectedIndex();
                        Spells spell = spells.get(spellIndex);

                        if (!list.getItems().contains(spell)) {
                            if (spell.getClass_().contains("(")) {
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText("Spell Class Confirmation");
                                alert.setContentText("Are you a " + spell.getClass_() + "?");

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.get() == ButtonType.OK) {
                                    list.getItems().add(spells.get(spellIndex).getName());
                                }
                            } else {
                                list.getItems().add(spells.get(spellIndex).getName());
                            }
                        }
                    }
                }
            }

        });

        primaryStage.setTitle("Add Spells");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
