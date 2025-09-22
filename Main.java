import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();

        // Здания
        Box buildingA = createBuilding(60, 200, 60, -150, 100, 0);
        Box buildingB = createBuilding(80, 300, 80, 0, 150, 0);
        Box buildingC = createBuilding(50, 250, 50, 150, 125, 0);

        root.getChildren().addAll(buildingA, buildingB, buildingC);

        // Тросы как цилиндры
        Cylinder ropeAB = createRope(-150, 200, 0, 0, 300, 0);
        Cylinder ropeBC = createRope(0, 300, 0, 150, 250, 0);
        Cylinder ropeAC = createRope(-150, 200, 0, 150, 250, 0);

        root.getChildren().addAll(ropeAB, ropeBC, ropeAC);

        // Камера
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(-20, Rotate.X_AXIS),
                new Rotate(-20, Rotate.Y_AXIS)
        );
        camera.setTranslateZ(-800);

        SubScene subScene = new SubScene(root, 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.LIGHTBLUE);
        subScene.setCamera(camera);

        Group container = new Group();
        container.getChildren().add(subScene);

        Scene scene = new Scene(container, 800, 600, true);
        stage.setTitle("3D Здания с тросами (JavaFX)");
        stage.setScene(scene);
        stage.show();
    }

    private Box createBuilding(double w, double h, double d, double x, double y, double z) {
        Box box = new Box(w, h, d);
        box.setTranslateX(x);
        box.setTranslateY(y);
        box.setTranslateZ(z);
        PhongMaterial mat = new PhongMaterial(Color.GRAY);
        box.setMaterial(mat);
        return box;
    }

    private Cylinder createRope(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double length = Math.sqrt(dx*dx + dy*dy + dz*dz);
        Cylinder rope = new Cylinder(3, length);

        // Устанавливаем позицию посередине
        rope.setTranslateX((x1 + x2) / 2.0);
        rope.setTranslateY((y1 + y2) / 2.0);
        rope.setTranslateZ((z1 + z2) / 2.0);

        rope.setMaterial(new PhongMaterial(Color.DARKGRAY));
        return rope;
    }

    public static void main(String[] args) {
        launch();
    }
}
