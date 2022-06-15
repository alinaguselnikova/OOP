package nsu.computerscience.snake.view;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LabeledIntInput extends HBox {
    public final SimpleIntegerProperty valueProperty;

    LabeledIntInput(String text, int value) {
        super();
        valueProperty = new SimpleIntegerProperty(value);

        var label = new Text(text);
        var field = new TextField(String.valueOf(value));

        valueProperty.bind(new IntegerBinding() {
            {
                bind(field.textProperty());
            }

            @Override
            protected int computeValue() {
                try {
                    return Integer.parseInt(field.textProperty().getValue());
                } catch (NumberFormatException e) {
                    return value;
                }
            }
        });

        getChildren().addAll(label, field);
    }
}
