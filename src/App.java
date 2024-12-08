import java.lang.ModuleLayer.Controller;

import javax.swing.text.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SolitareView view = new SolitareView(primaryStage);
        view.show();
    }
}
