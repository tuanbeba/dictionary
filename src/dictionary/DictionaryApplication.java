package dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class DictionaryApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Dictionary");
        //Image img = new Image("/image/logo.png");
        //window.getIcons().add(img);
        FXMLLoader loaderScene = new FXMLLoader(getClass().getResource("/Start.fxml"));
        Scene scene = new Scene(loaderScene.load(), 800, 600);
        stage.setScene(scene);
        FXMLLoader loaderScene1 = new FXMLLoader(getClass().getResource("/DictionaryGraphic.fxml"));
        Scene scene1 = new Scene(loaderScene1.load(), 800, 600);

        Button ButtonStart = (Button) scene.lookup("#start");
        ButtonStart.setOnAction(actionEvent -> {
            stage.setScene(scene1);
        });
        Control temporaryControllerScene1 = loaderScene1.getController();
        temporaryControllerScene1.initResultList();
        stage.show();
    }
}
