/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeons_and_dragons.ui;

import dungeons_and_dragons.entity.Spells;
import java.io.InputStream;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Sean Peel
 */
public class SpellCard extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private Spells spell;

    public SpellCard(Spells spell) {
        this.spell = spell;
    }

    @Override
    public void start(Stage primaryStage) {
        InputStream spellCardImageStream = null;
        String imgPath = "dungeons_and_dragons/resources/";
        Image image;

        switch (spell.getSchool()) {
            case "A":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "AjurationSpellCard.png");
                break;
            case "C":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "ConjurationSpellCard.png");
                break;
            case "D":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "DivinationSpellCard.png");
                break;
            case "EN":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "EnchantmentSpellCard.png");
                break;
            case "EV":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "EvocationSpellCard.png");
                break;
            case "I":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "IllusionSpellCard.png");
                break;
            case "N":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "NecromancySpellCard.png");
                break;
            case "T":
                spellCardImageStream = SpellCard.class.getClassLoader()
                        .getResourceAsStream(imgPath + "TransmutationSpellCard.png");
                break;
        }
        image = new Image(spellCardImageStream);

        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        StackPane root = new StackPane();

        root.setBackground(new Background(background));

        Scene scene = new Scene(root, background.getImage().getWidth(),
                background.getImage().getHeight());

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() - xOffset);
                primaryStage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        primaryStage.close();
                    }
                }
            }
        });

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
