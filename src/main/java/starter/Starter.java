package starter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends javafx.application.Application {
    public static Stage Pristage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Home.fxml"))));
        stage.show();
        Pristage=stage;
    }
    public static  void switchscne(String x) throws IOException {
        Pristage.setScene(new Scene(FXMLLoader.load(Starter.class.getResource(x))));
    }
}
