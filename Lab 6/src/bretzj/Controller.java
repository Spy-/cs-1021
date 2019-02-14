/**
 * CS1021
 * Winter 2018-2019
 * Name: John Bretz
 * Created 1/16/19
 */
package bretzj;

import edu.msoe.se1021.Lab6.WebsiteTester;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Optional;

public class Controller {
    public TextField url;
    public TextField size;
    public TextField port;
    public TextField timeout;
    public TextField time;
    public TextField host;
    public TextArea output;

    private WebsiteTester tester = new WebsiteTester();
    private boolean urlSet = false;
    private boolean connectionOpen = false;
    private boolean downloadedText = false;

    /**
     * Sets the timeout time from the timeout TextField
     */
    @FXML
    public void setTimeout() {
        String userTimeout = timeout.getText();
        try {
            tester.setTimeout(userTimeout);
            System.out.println("set timeout: " + userTimeout);
        } catch (NumberFormatException nfe) {
            createAlert(Alert.AlertType.ERROR, "Invalid Timeout", "Timeout must be greater than zero").show();
        }
    }

    /**
     * Sets the timeout
     *
     * @param s the timeout
     */
    private void setTimeout(String s) {
        try {
            tester.setTimeout(s);
            System.out.println("set timeout: " + s);
            timeout.setText(s);
            analyze();
        } catch (NumberFormatException nfe) {
            createAlert(Alert.AlertType.ERROR, "Invalid Timeout", "Timeout must be greater than zero").show();
        }
    }

    /**
     * Analyzes the URL and runs through the process to get the website content
     */
    @FXML
    public void analyze() {
        System.out.println("Analyzing URL: " + url.getText());

        openURL();
        if (urlSet) {
            openConnection();
        }
        if (connectionOpen) {
            downloadText();
        }
        if (downloadedText) {
            setOutputs();
        }
    }

    /**
     * Opens the URL in the website tester
     */
    private void openURL() {
        try {
            tester.openURL(url.getText());
            urlSet = true;
            System.out.println("\topenURL()");
        } catch (MalformedURLException e) {
            createAlert(Alert.AlertType.ERROR, "Malformed URL", "Enter an invalid URL.").show();
        }
    }

    /**
     * Connects to the website
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private void openConnection() {
        try {
            tester.openConnection();
            connectionOpen = true;
            urlSet = false;
            System.out.println("\topenConnection()");
        } catch (UnknownHostException e) {
            createAlert(Alert.AlertType.ERROR, "Unknown Host", "Could not reach the host.").show();
        } catch (SocketTimeoutException e) {
            Alert alert = createAlert(Alert.AlertType.WARNING, "Socket Timeout", "Extend timeout delay?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                extendTimeout();
            }
        } catch (MalformedURLException e) {
            createAlert(Alert.AlertType.ERROR, "Malformed URL", "Enter an invalid URL.").show();
        } catch (IOException e) {
            createAlert(Alert.AlertType.ERROR, "IO Error", "Some error occurred. Check that the URL is correct").show();
        }
    }

    /**
     * Downloads the website's content
     */
    private void downloadText() {
        try {
            tester.downloadText();
            downloadedText = true;
            connectionOpen = false;
            System.out.println("\tdownloadText()");
        } catch (ConnectException e) {
            createAlert(Alert.AlertType.ERROR, "Connection Error", "Check that the URL is correct and connected to the internet.").show();
        } catch (UnknownHostException e) {
            createAlert(Alert.AlertType.ERROR, "Unknown Host", "Could not reach the host.").show();
        } catch (FileNotFoundException e) {
            createAlert(Alert.AlertType.ERROR, "File Not Found", "File not found on server").show();
        } catch (IOException e) {
            createAlert(Alert.AlertType.ERROR, "IO Error", "Some error occurred. Check that the URL is correct").show();
        }
    }

    /**
     * Handles the dialog that allows the user to extend the timeout delay.
     */
    private void extendTimeout() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Extend Timeout");
        dialog.setHeaderText("Extend Timeout");
        dialog.setContentText("Enter Timeout: ");

        dialog.showAndWait().ifPresent(this::setTimeout);
    }

    /**
     * Sets the output fields
     */
    private void setOutputs() {
        System.out.println("\tsetOutputs()");
        output.setText(tester.getContent());
        size.setText(String.valueOf(tester.getSize()));
        port.setText(String.valueOf(tester.getPort()));
        host.setText(tester.getHostname());
        time.setText(String.valueOf(tester.getDownloadTime()));
        System.out.println("done");

        downloadedText = false;
    }

    /**
     * Helper Method for creating Alert objects
     *
     * @param type    the AlertType
     * @param header  the header text
     * @param content the content text
     * @return an Alert object
     */
    private Alert createAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert;
    }
}
