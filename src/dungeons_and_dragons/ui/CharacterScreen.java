package dungeons_and_dragons.ui;

import dungeons_and_dragons.entity.Characters;
import dungeons_and_dragons.entity.Spells;
import dungeons_and_dragons.util.DatabaseQuery;
import dungeons_and_dragons.util.CharacterScreenFieldsUtil;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Sean Peel
 */
public class CharacterScreen extends Application implements CharacterScreenFieldsUtil {

    InputStream characterSheetImageStream;
    Image characterSheet;

    private DatabaseQuery dbQuery;
    private Characters character;

    private String charName = "";

    public CharacterScreen() {
    }

    public CharacterScreen(String charName) {
        this.charName = charName;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        characterSheetImageStream = CharacterScreen.class.getClassLoader()
                .getResourceAsStream("dungeons_and_dragons/resources/CharacterSheet.png");
        characterSheet = new Image(characterSheetImageStream);

        dbQuery = new DatabaseQuery();

        if (!charName.equals("")) {
            character = (Characters) dbQuery.executeQuery("FROM Characters where "
                    + "charName = " + "\"" + charName + "\"").get(0);
        } else {
            character = new Characters();
        }
        //Fields
        TextField charNameText = createCharacterTextField(character.getCharName(),
                NAME_FONT, false);
        TextField classNameText = createCharacterTextField(character.getClass_()
                + " - " + character.getLevel(), CLASS_AREA_FONT, false);
        TextField backgroundNameText = createCharacterTextField(character.getBackground(),
                CLASS_AREA_FONT, false);
        TextField playerNameText = createCharacterTextField(character.getPlayerName(),
                CLASS_AREA_FONT, false);
        TextField charRaceText = createCharacterTextField(character.getRace(),
                CLASS_AREA_FONT, false);
        TextField charAlignmentText = createCharacterTextField(character.getAlignment(),
                CLASS_AREA_FONT, false);
        TextField charXpText = createCharacterTextField(character.getXp(),
                CLASS_AREA_FONT, false);
        TextField armorClassText = createCharacterTextField(character.getArmorClass(),
                INITIATIVE_AREA_FONT, true);
        TextField initiativeText = createCharacterTextField(character.getInitiative(),
                INITIATIVE_AREA_FONT, true);
        TextField speedText = createCharacterTextField(character.getSpeed(),
                INITIATIVE_AREA_FONT, true);
        TextField strengthText = createCharacterTextField(character.getStrength(),
                STATS_AREA_FONT, true);
        TextField dexterityText = createCharacterTextField(character.getDexterity(),
                STATS_AREA_FONT, true);
        TextField constitutionText = createCharacterTextField(character.getConstitution(),
                STATS_AREA_FONT, true);
        TextField intelligenceText = createCharacterTextField(character.getIntelligence(),
                STATS_AREA_FONT, true);
        TextField wisdomText = createCharacterTextField(character.getWisdom(),
                STATS_AREA_FONT, true);
        TextField charismaText = createCharacterTextField(character.getCharisma(),
                STATS_AREA_FONT, true);
        TextField strModText = createCharacterTextField(character.getStrMod(),
                STATS_MOD_AREA_FONT, true);
        TextField dexModText = createCharacterTextField(character.getDexMod(),
                STATS_MOD_AREA_FONT, true);
        TextField conModText = createCharacterTextField(character.getConMod(),
                STATS_MOD_AREA_FONT, true);
        TextField intModText = createCharacterTextField(character.getIntMod(),
                STATS_MOD_AREA_FONT, true);
        TextField wisModText = createCharacterTextField(character.getWisMod(),
                STATS_MOD_AREA_FONT, true);
        TextField chaModText = createCharacterTextField(character.getChaMod(),
                STATS_MOD_AREA_FONT, true);

        TextArea personalityText = createCharacterTextArea(character.getPersonalityTraits(),
                PERSONALITY_AREA_FONT, PERSONALITY_TEXT_FIELD_HEIGHT);
        TextArea idealsText = createCharacterTextArea(character.getIdeals(),
                PERSONALITY_AREA_FONT, IDEALS_TEXT_FIELD_HEIGHT);
        TextArea bondsText = createCharacterTextArea(character.getBonds(),
                PERSONALITY_AREA_FONT, IDEALS_TEXT_FIELD_HEIGHT);
        TextArea flawsText = createCharacterTextArea(character.getFlaws(),
                PERSONALITY_AREA_FONT, IDEALS_TEXT_FIELD_HEIGHT);

        //TODO - MAKE TABLE INSTEAD OF LIST
        ListView spellList = createSpellList();

        //Nodes
        ScrollPane scrollPane = new ScrollPane();
        StackPane window = new StackPane();
        ImageView imgView = new ImageView(characterSheet);
        StackPane root = new StackPane();
        VBox charWayOfLifeBox = new VBox();
        VBox classAreaBox = new VBox();
        VBox statsAreaBox = new VBox();
        VBox strBox = new VBox();
        VBox dexBox = new VBox();
        VBox conBox = new VBox();
        VBox intBox = new VBox();
        VBox wisBox = new VBox();
        VBox chaBox = new VBox();
        HBox initiativeAreaBox = new HBox();
        HBox charNameBox = new HBox();
        HBox classAreaBoxUpper = new HBox();
        HBox classAreaBoxLower = new HBox();
        System.out.println(classAreaBoxLower);

        //Force ImageView to fit to window size
        imgView.fitWidthProperty().bind(primaryStage.widthProperty());

        //Set Node Properties
        classAreaBox.setMaxHeight(2 * classAreaBoxLower.getHeight() + 10);
        classAreaBox.setTranslateY(CLASS_AREA_BOX_TRANSLATE_Y);
        classAreaBox.setSpacing(CLASS_AREA_BOX_SPACING);

        charNameBox.setTranslateY(NAME_TEXT_FIELD_TRANSLATE_Y);
        charNameBox.setMaxHeight(NAME_TEXT_FIELD_HEIGHT);

        charWayOfLifeBox.setTranslateY(CHAR_WAY_OF_LIFE_BOX_TRANSLATE_Y);
        charWayOfLifeBox.setMaxHeight(CHAR_WAY_OF_LIFE_BOX_HEIGHT);
        charWayOfLifeBox.setSpacing(CHAR_WAY_OF_LIFE_BOX_SPACING);

        initiativeAreaBox.setMaxHeight(INITIATIVE_AREA_BOX_HEIGHT);
        initiativeAreaBox.setTranslateY(INITIATIVE_AREA_BOX_TRANSLATE_Y);
        initiativeAreaBox.setPadding(INITIATIVE_AREA_BOX_PADDING);

        statsAreaBox.setMaxHeight(STATS_AREA_BOX_HEIGHT);
        statsAreaBox.setTranslateY(STATS_AREA_BOX_TRANSLATE_Y);
        statsAreaBox.setPadding(STATS_AREA_BOX_PADDING);
        statsAreaBox.setSpacing(STATS_AREA_BOX_SPACING);

        strBox.setSpacing(STAT_BOX_SPACING);
        dexBox.setSpacing(STAT_BOX_SPACING);
        conBox.setSpacing(STAT_BOX_SPACING);
        intBox.setSpacing(STAT_BOX_SPACING);
        wisBox.setSpacing(STAT_BOX_SPACING);
        chaBox.setSpacing(STAT_BOX_SPACING);

        //Populate Nodes
        charNameBox.getChildren().addAll(charNameText);
        classAreaBoxUpper.getChildren().addAll(classNameText, backgroundNameText, playerNameText);
        classAreaBoxLower.getChildren().addAll(charRaceText, charAlignmentText, charXpText);
        classAreaBox.getChildren().addAll(classAreaBoxUpper, classAreaBoxLower);
        initiativeAreaBox.getChildren().addAll(armorClassText, initiativeText, speedText);
        charWayOfLifeBox.getChildren().addAll(personalityText, idealsText, bondsText, flawsText);
        strBox.getChildren().addAll(strengthText, strModText);
        dexBox.getChildren().addAll(dexterityText, dexModText);
        conBox.getChildren().addAll(constitutionText, conModText);
        intBox.getChildren().addAll(intelligenceText, intModText);
        wisBox.getChildren().addAll(wisdomText, wisModText);
        chaBox.getChildren().addAll(charismaText, chaModText);
        statsAreaBox.getChildren().addAll(strBox, dexBox, conBox, intBox, wisBox, chaBox);
        window.getChildren().addAll(imgView, charNameBox, classAreaBox, charWayOfLifeBox,
                initiativeAreaBox, statsAreaBox, spellList);

        scrollPane.setContent(window);

        //ScrollPane cannot be set as root because it is inside a scene-graph
        //Use a second StackPane as root
        root.getChildren().add(scrollPane);

        //Create scene
        Scene scene = new Scene(root, characterSheet.getWidth(),
                characterSheet.getHeight());

        //Keep all Nodes/Fields in their proper place upon resizing
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);

                //Set Location and Width for Character Name
                charNameBox.setTranslateX(newSceneWidth.doubleValue() / NAME_TEXT_FIELD_TRANSLATE_X_RATIO);
                charNameBox.setMaxWidth(newSceneWidth.doubleValue() / NAME_TEXT_FIELD_WIDTH_RATIO);
                //Set Location and Width for Class Area Box
                classAreaBox.setTranslateX(newSceneWidth.doubleValue() / CLASS_AREA_BOX_TRANSLATE_X_RATIO);
                classAreaBox.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_BOX_WIDTH_RATIO);
                //Set Width for Class Area Box Nodes
                classAreaBoxUpper.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_BOX_WIDTH_RATIO);
                classAreaBoxLower.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_BOX_WIDTH_RATIO);
                classNameText.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_TEXT_1_WIDTH_RATIO);
                backgroundNameText.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_TEXT_2_WIDTH_RATIO);
                playerNameText.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_TEXT_3_WIDTH_RATIO);
                charRaceText.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_TEXT_1_WIDTH_RATIO);
                charAlignmentText.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_TEXT_2_WIDTH_RATIO);
                charXpText.setMaxWidth(newSceneWidth.doubleValue() / CLASS_AREA_TEXT_3_WIDTH_RATIO);
                //Set Location and Width for Way of Life Box
                charWayOfLifeBox.setTranslateX(newSceneWidth.doubleValue() / CHAR_WAY_OF_LIFE_BOX_TRANSLATE_X_RATIO);
                charWayOfLifeBox.setMaxWidth(newSceneWidth.doubleValue() / CHAR_WAY_OF_LIFE_BOX_WIDTH_RATIO);

                //Set Width for Initiative Area Box
                //No need to set Location as TRANSLATE_X = 0 (Center of screen)
                initiativeAreaBox.setMaxWidth(newSceneWidth.doubleValue() / INITIATIVE_AREA_BOX_WIDTH_RATIO);

                //Set Location and Width for Stats Area Box
                statsAreaBox.setTranslateX(newSceneWidth.doubleValue() / STATS_AREA_BOX_TRANSLATE_X_RATIO);
                statsAreaBox.setMaxWidth(newSceneWidth.doubleValue() / STATS_AREA_BOX_WIDTH_RATIO);

                //Set Location and Width for SpellList
                spellList.setTranslateX(newSceneWidth.doubleValue() / SPELL_LIST_FIELD_TRANSLATE_X_RATIO);
                spellList.setMinWidth(newSceneWidth.doubleValue() / SPELL_LIST_FIELD_WIDTH_RATIO);
                spellList.setMaxWidth(newSceneWidth.doubleValue() / SPELL_LIST_FIELD_WIDTH_RATIO);
            }
        });

        //Create Window
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Character Sheet: " + character.getCharName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //ONLY set HEIGHT based properties.
    //WIDTH based properties are set/changed during scene resizing
    private TextField createCharacterTextField(String text, Font font, boolean center) {
        TextField txtField = new TextField();
        if (!text.equals("")) {
            txtField.setText(text);
        }
        txtField.setFont(font);
        txtField.setBackground(Background.EMPTY);

        if (center) {
            txtField.setAlignment(Pos.CENTER);
        }

        return txtField;
    }

    //ONLY set HEIGHT based properties.
    //WIDTH based properties are set/changed during scene resizing
    private TextArea createCharacterTextArea(String text, Font font, double height) {
        TextArea personalityText = new TextArea();
        if (!text.equals("")) {
            personalityText.appendText(text);
        }
        personalityText.setFont(font);
        personalityText.setMinHeight(height);
        personalityText.setMaxHeight(height);
        personalityText.setWrapText(true);

        return personalityText;
    }

    //ONLY set HEIGHT based properties.
    //WIDTH based properties are set/changed during scene resizing
    private ListView createSpellList() {
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        list.setItems(items);

        list.setTranslateY(SPELL_LIST_FIELD_TRANSLATE_Y);
        list.setMaxHeight(SPELL_LIST_FIELD_HEIGHT);
        list.setMinHeight(SPELL_LIST_FIELD_HEIGHT);

        addSpellListContextMenu(list);

        return list;
    }

    //Helper method to add right click menu to spell ListView
    private void addSpellListContextMenu(ListView list) {
        ListView spellListView = list;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem add = new MenuItem("Add Spell");
        MenuItem remove = new MenuItem("Remove Spell");
        MenuItem show = new MenuItem("Show Spell Card");
        contextMenu.getItems().addAll(add, remove, show);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddSpellScreen addSpellScreen = new AddSpellScreen(spellListView);
                try {
                    addSpellScreen.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(CharacterScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                spellListView.getItems().remove(spellListView.getSelectionModel().getSelectedItem());
            }
        });

        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String spellName = spellListView.getSelectionModel().getSelectedItem().toString();
                if (spellName.contains("(")) {
                    spellName = spellName.split(" \\(")[0];
                }
                DatabaseQuery dbQuery = new DatabaseQuery();
                List<Spells> results = dbQuery.executeQuery("FROM Spells s where s.class_ like 'Warlock%' and s.name like \"" + spellName + "%\"");

                if (results != null) {
                    Spells spell = results.get(0);
                    SpellCard spellCard = new SpellCard(spell);
                    spellCard.start(new Stage());
                }
            }
        });

        spellListView.setContextMenu(contextMenu);

        spellListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event
            ) {
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    contextMenu.show(spellListView, event.getScreenX(), event.getScreenY());
                }
            }

        });
    }

}
