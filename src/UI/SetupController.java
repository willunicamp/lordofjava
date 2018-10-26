/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author will
 */
public class SetupController implements Initializable {

    @FXML
    private TextField txtNome1;
    @FXML
    private TextField txtNome2;
    @FXML
    private TextField txtNome3;
    @FXML
    private ComboBox<?> cmbClasse1;
    @FXML
    private ComboBox<?> cmbClasse3;
    @FXML
    private ComboBox<?> cmbClasse2;
    @FXML
    private Button btnIniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnIniciarClick(ActionEvent event) {
    }
    
}
