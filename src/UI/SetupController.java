/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personagens.Classe;
import principal.Batalha;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author will
 */
public class SetupController implements Initializable {

    @FXML
    private ComboBox<Classe.Tipo> cmbClasse1;
    
    private Batalha batalha;
    @FXML
    private TextField txtNome1;
    @FXML
    private TextField txtNome2;
    @FXML
    private TextField txtNome3;
    @FXML
    private ComboBox<Classe.Tipo> cmbClasse3;
    @FXML
    private ComboBox<Classe.Tipo> cmbClasse2;
    @FXML
    private Button btnIniciar;
    @FXML
    private Label lblInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //preenche os combos com os tipos de classes disponiveis
        //detalhe: o metodo toString do enum eh usado para relacionar
        //o texto visivel no combobox
        cmbClasse1.getItems().setAll(Classe.Tipo.values());
        cmbClasse2.getItems().setAll(Classe.Tipo.values());
        cmbClasse3.getItems().setAll(Classe.Tipo.values());
    }    
    
    private boolean adicionaPersonagens(){
            //pega os dados escolhidos e digitados pelo usuario
            String nome1 = txtNome1.getText();
            Classe.Tipo tipo1 = cmbClasse1.getValue();
            String nome2 = txtNome2.getText();
            Classe.Tipo tipo2 = cmbClasse2.getValue();
            String nome3 = txtNome3.getText();
            Classe.Tipo tipo3 = cmbClasse3.getValue();
            
            //confere se nao estao vazios
            if(nome1.trim().isEmpty() || nome2.trim().isEmpty() || nome3.trim().isEmpty()){
                lblInfo.setText("Algum dos nomes pode estar invalido. Confira.");
                return false;
            }else if(cmbClasse1.getSelectionModel().isEmpty() || cmbClasse2.getSelectionModel().isEmpty() 
                    || cmbClasse3.getSelectionModel().isEmpty()){ //confere combos selecionados
                lblInfo.setText("Selecione corretamente as classes dos personagens");
                return false;
            }
            //adiciona no objeto batalha
            batalha.adicionaAliado(nome1, tipo1);
            batalha.adicionaAliado(nome2, tipo2);
            batalha.adicionaAliado(nome3, tipo3);
            
            return true;
    }

    @FXML
    private void btnIniciarClick(ActionEvent event) {
        //cria o objeto batalha
        batalha = new Batalha();
        try {
            //se personagens forem adicionados corretamente
            if(adicionaPersonagens()){
                //cria o form usando FXMLLoader
                FXMLLoader game = new FXMLLoader(getClass().getResource("../UI/Game.fxml"));
                //Cria a cena e mostra o palco
                Scene scene = new Scene(game.load());
                //pega o controle do form
                GameController gc = game.getController();
                //carrega a batalha no form do jogo
                gc.carregaBatalha(this.batalha);
                
                
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
