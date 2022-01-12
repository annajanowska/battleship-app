package com.example.statki;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class CheckReplayGameController {
    public  ImageView imgView;
    public  Image myImage;
    public Button rightButton;
    public Button leftButton;
    private Player player;
    public ArrayList<Image> screenshots = new ArrayList<Image>();
    public int idGame;
    public Button seeScreenButton;
    int counter = 0;

    public ArrayList<Image> showScreen() {
        DatabaseConnector connectNow = new DatabaseConnector();

        try (Connection conn = connectNow.getConnection()) {

            ArrayList<Image> gameScreenshots = new ArrayList<>();

            System.out.println(" TO ID GAME PRZED SELECT " + idGame);
            String sql = "SELECT imageName, imageSource FROM moves WHERE idGame = " + idGame + "";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("imageName");
                File image = new File(name);
                try (FileOutputStream fos = new FileOutputStream(image)) {
                    byte[] buffer = new byte[1024];

                    InputStream is = rs.getBinaryStream("imageSource");
                    while (is.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                    fos.close();
                    is.close();
                    myImage = new Image("file:" + name);
                    gameScreenshots.add(myImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("To jest lista obiektów image" + gameScreenshots);
            return gameScreenshots;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void seeNextScreenClicked(MouseEvent mouseEvent) {
        seeScreenButton.setText("Next");
        screenshots = showScreen();
        System.out.println("To jest lista obiektów image w initialize: " + screenshots.size());

        if (counter < screenshots.size()) {
            imgView.setImage(screenshots.get(counter));
            counter++;
            if (counter == screenshots.size()) {
                seeScreenButton.setDisable(true);
                seeScreenButton.setText("Koniec");
            }
        }
    }

    public void cancelLabelOnAction(MouseEvent mouseEvent) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        StartController startController = loader.getController();
        startController.setPlayer(player);
        counter = 0;
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void rightClicked(ActionEvent actionEvent) throws IOException {
        if (player != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myAccount.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            MyAccountController myAccountController = loader.getController();
            myAccountController.setPlayer(player);
            counter = 0;
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            Parent root =  FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            counter = 0;
        }
    }

    public void leftClicked(ActionEvent actionEvent) throws IOException {
        if (player != null) {
            player = null;
            leftButton.setText("Zaloguj się");
            rightButton.setText("Rejestracja");
            counter = 0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            counter = 0;
        }
    }

    public void setPlayer (Player player) {
        this.player = player;
        System.out.println(this.player);
        if (this.player != null) {
            leftButton.setText("Wyloguj się");
            rightButton.setText("Moje konto");
        }
    }

    public void setIdGame (int idGame) {
        this.idGame = idGame;
        System.out.println("TO JEST ID GAME" + idGame);
    }

}
