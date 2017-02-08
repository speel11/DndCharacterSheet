/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeons_and_dragons.ui;

import dungeons_and_dragons.entity.Users;
import dungeons_and_dragons.util.DatabaseQuery;
import dungeons_and_dragons.util.UserAuthentication;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Sean Peel
 */
public class LogInScreen extends Application {

    private final Boolean DEBUG = true;

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {

//---------------------------------------------------------------------------------------------------
        if (DEBUG) {
            String charName = "Morthos Skarro";
            CharacterScreen charScreen = new CharacterScreen(charName);
            charScreen.start(primaryStage);
        } //---------------------------------------------------------------------------------------------------
        else {
            //Grid layout
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            //Welcome message for the scene
            Text sceneTitle = new Text("Welcome, Adventurer!");
            sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(sceneTitle, 0, 0, 2, 1);

            //username label
            Label userName = new Label("User Name:");
            grid.add(userName, 0, 1);

            //username field
            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);

            //password label
            Label password = new Label("Password:");
            grid.add(password, 0, 2);

            //password field
            PasswordField passwordField = new PasswordField();
            grid.add(passwordField, 1, 2);

            //Sign in button placed below and to the right of the username/password fields
            Button signInBtn = new Button("Sign in");
            signInBtn.setCursor(Cursor.HAND);
            HBox signInBtnBox = new HBox(10);
            signInBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
            signInBtnBox.getChildren().add(signInBtn);
            grid.add(signInBtnBox, 1, 4);

            //Create Account button places below and to the left of username/password fields
            Button addAccBtn = new Button("Create Account");
            addAccBtn.setCursor(Cursor.HAND);
            HBox addAccBtnBox = new HBox(10);
            addAccBtnBox.setAlignment(Pos.BOTTOM_LEFT);
            addAccBtnBox.getChildren().add(addAccBtn);
            grid.add(addAccBtnBox, 0, 4);

            //The message that shows that the user clicks a button.
            final Text actionTarget = new Text();
            grid.add(actionTarget, 1, 6);

            //For Debugging purposes ONLY
            if (DEBUG) {
                grid.setGridLinesVisible(true);
            }

            Scene scene = new Scene(grid, 350, 275);

            //Sign in button event handler
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        String username = userTextField.getText();
                        String password = passwordField.getText();
                        UserAuthentication auth = new UserAuthentication();
                        try {
                            if (!username.equals("") && !password.equals("")) {

                                if (auth.authenticate(username, password)) {
                                    actionTarget.setFill(Color.LIMEGREEN);
                                    actionTarget.setText("Sign in success!");
                                    primaryStage.close();
                                    CharacterSelectionScreen charScreen = new CharacterSelectionScreen();

                                    charScreen.start(new Stage());

                                } else {
                                    actionTarget.setFill(Color.FIREBRICK);
                                    actionTarget.setText("Username/password is incorrect.");
                                }
                            } else {
                                actionTarget.setFill(Color.FIREBRICK);
                                actionTarget.setText("Please enter a username/password.");
                            }
                        } catch (Exception ex) {
                            new ErrorAlertWindow(AlertType.ERROR, ex);
                        }
                    }
                }
            });

            signInBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {

                    String username = userTextField.getText();
                    String password = passwordField.getText();
                    UserAuthentication auth = new UserAuthentication();
                    try {
                        if (!username.equals("") && !password.equals("")) {

                            if (auth.authenticate(username, password)) {
                                actionTarget.setFill(Color.LIMEGREEN);
                                actionTarget.setText("Sign in success!");
                                primaryStage.close();
                                CharacterSelectionScreen charScreen = new CharacterSelectionScreen();

                                charScreen.start(new Stage());

                            } else {
                                actionTarget.setFill(Color.FIREBRICK);
                                actionTarget.setText("Username/password is incorrect.");
                            }
                        } else {
                            actionTarget.setFill(Color.FIREBRICK);
                            actionTarget.setText("Please enter a username/password.");
                        }
                    } catch (Exception ex) {
                        new ErrorAlertWindow(AlertType.ERROR, ex);
                    }
                }
            });

            addAccBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DatabaseQuery dbQuery = new DatabaseQuery();
                    String username = userTextField.getText();
                    String password = passwordField.getText();

                    Users user = new Users(username, password);

                    if (!username.equals("") && !password.equals("")) {
                        Object result = dbQuery.executeUserQuery(username);
                        if (result == null) {
                            user.commit();
                            actionTarget.setFill(Color.LIMEGREEN);
                            actionTarget.setText("Account Successfully Created!");
                        } else {
                            actionTarget.setFill(Color.FIREBRICK);
                            actionTarget.setText("This username already exists.");
                        }
                    } else {
                        //TODO - Make an account creation window.
                        actionTarget.setFill(Color.FIREBRICK);
                        actionTarget.setText("Enter desired account information.");
                    }
                }

            });

            primaryStage.setTitle("Dungeons & Dragons");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
