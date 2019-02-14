/*
 * CS1021
 * Winter 2018-2019
 * Lab 9: Final Project
 * Name: John Bretz
 * Created 2/7/19
 */
package bretzj;

import edu.msoe.cs1021.ImageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.stream.DoubleStream;

public class FilterController {

    @FXML
    public TextField top_left;

    @FXML
    public TextField top_center;

    @FXML
    public TextField top_right;

    @FXML
    public TextField center_left;

    @FXML
    public TextField center;

    @FXML
    public TextField center_right;

    @FXML
    public TextField bottom_left;

    @FXML
    public TextField bottom_center;

    @FXML
    public TextField bottom_right;

    private Controller mainController;

    /**
     * Pass the primary controller to this controller
     * @param controller the primary controller
     */
    void injectMainController(Controller controller) {
        this.mainController = controller;
    }

    /**
     * Apply the kernel
     */
    @FXML
    void apply() {
        double div = DoubleStream.of(retrieveKernel(1.0)).sum();
        Image image = ImageUtil.convolve(mainController.viewport.getImage(), retrieveKernel(div));
        mainController.viewport.setImage(image);
    }

    /**
     * Apply a blur kernel
     */
    @FXML
    void blur() {
        setKernel(new double[]{0, 1, 0, 1, 5, 1, 0, 1, 0});
        Image image = ImageUtil.convolve(mainController.viewport.getImage(), retrieveKernel(9.0));
        mainController.viewport.setImage(image);
    }

    /**
     * Apply a sharpen kernel
     */
    @FXML
    void sharpen() {
        setKernel(new double[]{0, -1, 0, -1, 5, -1, 0, -1, 0});
        Image image = ImageUtil.convolve(mainController.viewport.getImage(), retrieveKernel(1.0));
        mainController.viewport.setImage(image);
    }

    /**
     * retrieves the current kernel in the filter
     * @param modifier a number to be divided by each cell (cell/modifier)
     * @return the kernel
     */
    private double[] retrieveKernel(double modifier) {
        double[] kernel = new double[9];

        kernel[0] = Double.parseDouble(top_left.getText()) / modifier;
        kernel[1] = Double.parseDouble(top_center.getText()) / modifier;
        kernel[2] = Double.parseDouble(top_right.getText()) / modifier;
        kernel[3] = Double.parseDouble(center_left.getText()) / modifier;
        kernel[4] = Double.parseDouble(center.getText()) / modifier;
        kernel[5] = Double.parseDouble(center_right.getText()) / modifier;
        kernel[6] = Double.parseDouble(bottom_left.getText()) / modifier;
        kernel[7] = Double.parseDouble(bottom_center.getText()) / modifier;
        kernel[8] = Double.parseDouble(bottom_right.getText()) / modifier;

        return kernel;
    }

    /**
     * Sets the filter to be the current kernel
     * @param values the values to set
     */
    private void setKernel(double[] values) {
        top_left.setText(String.valueOf((int) values[0]));
        top_center.setText(String.valueOf((int) values[1]));
        top_right.setText(String.valueOf((int) values[2]));
        center_left.setText(String.valueOf((int) values[3]));
        center.setText(String.valueOf((int) values[4]));
        center_right.setText(String.valueOf((int) values[5]));
        bottom_left.setText(String.valueOf((int) values[6]));
        bottom_center.setText(String.valueOf((int) values[7]));
        bottom_right.setText(String.valueOf((int) values[8]));
    }

}
