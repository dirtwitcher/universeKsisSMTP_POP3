package universeKsisSMTP_POP3;

import java.awt.Button;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MailGuiFX extends Application {

    Button popBtn;

    @Override
    public void start(Stage primaryStage) throws IOException {
	Stage stage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("../mailGui.fxml"));

	stage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }
}
