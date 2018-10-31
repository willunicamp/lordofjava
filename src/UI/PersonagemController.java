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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import personagens.Personagem;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author will
 */
public class PersonagemController implements Initializable {

    private Personagem personagem;
    @FXML
    private ProgressBar proEspera;
    @FXML
    private ImageView imgPersonagem;
    @FXML
    private Label lblNome;
    @FXML
    private AnchorPane anpPrincipal;
    @FXML
    private Label lblStatus;
    @FXML
    private ProgressBar proPV;
    @FXML
    private Label lblClasse;
    @FXML
    private Label lblPV;
    @FXML
    private Label lblNivel;
    @FXML
    private Label lblNivel1;
    @FXML
    private ProgressBar proPM;
    @FXML
    private Label lblPM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void carrega(Personagem p){
        personagem = p;
        lblNome.setText(p.getNome());
        System.out.println(p.getNome());
        Utils.print(p.getCaminhoImagem());
        imgPersonagem.setImage(new Image(p.getCaminhoImagem()));
        anpPrincipal.setDisable(false);
        
        
    }
    
    public void atualiza(){
        if(personagem != null){
            double PV = personagem.getPV();
            double maxPV = personagem.getMaxPV();
            double PM = personagem.getPM();
            double maxPM = personagem.getMaxPM();
            double tempo = personagem.getTempoEspera();
            double maxTempo = personagem.getMaxTempoEspera();

            proEspera.setProgress(tempo/maxTempo);
            proPV.setProgress(PV/maxPV);
            proPM.setProgress(PM/maxPM);
            lblPV.setText(String.valueOf(PV));
            lblPM.setText(String.valueOf(PM));
            lblClasse.setText(personagem.getClasse().toString());
            lblNivel.setText("Nivel: "+personagem.getNivel());
            lblStatus.setText("Status:" + (personagem.getPV() == 0 ? "Inativo" : "Ativo"));
        }
    }

    private  void btnAtacarClick(ActionEvent event) {
        notifyAll();
    }
    
}
