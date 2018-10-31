/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import personagens.Classe;
import personagens.Equipe;
import personagens.Habilidade;
import personagens.Personagem;
import principal.Batalha;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author will
 */
public class GameController implements Initializable {

    private String mensagem;

    private Batalha batalha;
    @FXML
    private GridPane grdAliados;
    @FXML
    private AnchorPane sptGame;
    @FXML
    private AnchorPane anpPrincipal;

    List<PersonagemController> pcAliadosList;
    List<PersonagemController> pcInimigosList;
    @FXML
    private TextArea txtHistoria;
    @FXML
    private AnchorPane anpAcoes;
    @FXML
    private ComboBox<Habilidade> cmbHabilidades;
    @FXML
    private Label lblNomeAtacante;
    @FXML
    private Label lblDano;
    @FXML
    private Label lblTempoEspera;
    @FXML
    private Label lblAlvo;
    @FXML
    private Label lblAfeta;
    @FXML
    private Label lblPM;
    @FXML
    private ComboBox<Personagem> cmbAlvo;
    @FXML
    private TextArea txtMensagem;
    @FXML
    private Button btnUsarHabilidade;

    private Service<String> iniciaTurno;
    @FXML
    private Pane pnlInimigos;

    /**
     * Initializes the controller class.
     */
    public GameController() {
        //batalha eh iniciada no form Setup
        this.batalha = null;
        //como lista de aliados eh fixa ate o fim, inicializa-se no
        //construtor
        pcInimigosList = new LinkedList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Servico responsavel por executar uma batalha. Eh reiniciado pelo 
        //servico iniciaTurno a cada vitoria do time aliado
        Service<String> carregaBatalha = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        //Avisa o inicio da batalha
                        txtMensagem.setText("Iniciando nova batalha");
                        //Aguarda 2 segundos para o usuario ler a mensagem
                        Thread.sleep(2000);
                        //carrega proxima fase da batalha
                        batalha.carregaProximaFase();
                        //nao se pode mexer em componentes JavaFX de dentro de
                        //um servico, portanto, retornamos o texto descritivo da
                        //fase para ser capturado no onSucceeded
                        return batalha.getTextoFase();
                    }
                };
            }
        };

        //Servico responsavel por executar um turno. Ele eh reiniciado ao final de
        //cada turno
        iniciaTurno = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        //Como o turno eh iniciado varias vezes, aguardamos sempre
                        //2 segundos antes de iniciar
                        Thread.sleep(2000);
                        //verifica o final da batalha antes de iniciar novo turno
                        if (batalha.getInimigos().contaConscientes() == 0) {
                            updateMessage("Vitoria dos Aliados");
                            //Aliados venceram, aguarda 2 segundos e da aos
                            //personagens suas recompensas
                            Thread.sleep(2000);
                            batalha.getAliados().ganharExperiencia(batalha.getInimigos());
                            batalha.getAliados().reviverTodos();
                            return "proxima"; //retorno usado no onSuceeded
                        } else if (batalha.getAliados().contaConscientes() == 0) {
                            //Inimigos venceram, jogo acabou. Finalizar batalha
                            updateMessage("Vitória dos Inimigos. Fim de jogo...");
                            batalha.terminar();
                            return "fim"; //retorno usado no onSuceeded
                        }
                        //Ninguem venceu ainda, comeca novo turno
                        Personagem atacante = null;
                        while (atacante == null) {
                            //escolhe quem ataca, se ninguem estiver disponivel,
                            //significa que tempos de espera estao acima de zero
                            //para todos
                            atacante = batalha.escolheAtacante();
                            if (atacante == null) {
                                //ninguem disponivel, reduz os tempos de espera
                                batalha.atualizaTemposEspera();
                            }
                        }
                        return "turno"; //retorno usado no onSuceeded
                    }
                };
            }
        };

        carregaBatalha.setOnSucceeded((WorkerStateEvent event) -> {
            if (!batalha.acabouTudo()) {
                txtHistoria.setText(event.getSource().getValue().toString());
                carregaInimigosNaTela();
                iniciaTurno.restart();
            }
        });

        iniciaTurno.setOnSucceeded((WorkerStateEvent event) -> {
            //Se houver, atualiza o texto de mensagem com o que foi atualizado
            //pelo servico. Lembrando que o servico nao consegue mexer no 
            //componente JavaFX (por causa da thread principal JavaFX)
            txtMensagem.setText(event.getSource().getMessage()); //mensagem do updateMessage
            String resposta = event.getSource().getValue().toString(); //valor do retorno do servico
            if (resposta.equals("proxima")) { //acabou partida, ir pra proxima
                atualizaPersonagens();
                carregaBatalha.cancel();//finalizo a thread da batalha atual
                carregaBatalha.restart();//inicio nova batalha
            } else if (resposta.equals("turno")) {//novo turno deve comecar
                atualizaPersonagens();//atualiza componentes com info dos personagens
                preencheAtacante();//preenche o combobox com info de quem ataca
            } else if (resposta.equals("fim")) {//fim de jogo
                anpPrincipal.setDisable(true);//desabilita o form completamente
            }
        });

        //carrega os personagens aliados na tela usando outro FXML
        //Sem esse runlater ha problemas para carregar outros FXML no metodo initialize
        Platform.runLater(() -> {
            try {
                pcAliadosList = new LinkedList<>();
                if (batalha != null) {
                    Equipe aliados = batalha.getAliados(); //pega os personagens
                    int i = 0;
                    for (Personagem p : aliados.getEquipe()) {//para cada personagem
                        //carrega um novo form de personagem
                        FXMLLoader personagemLoader = new FXMLLoader(getClass().getResource("../UI/Personagem.fxml"));
                        //adiciona no grid ja existente, feito no FXML
                        grdAliados.add(personagemLoader.load(), 0, i);
                        i++;
                        //pega referencia ao controller do form
                        PersonagemController pc = personagemLoader.getController();
                        //atraves do controller, carrega o personagem no form e atualiza suas informacoes
                        pc.carrega(p);
                        pc.atualiza();
                        //adiciona controller numa lista para uso futuro em atualizacoes de tela
                        pcAliadosList.add(pc);
                    }
                    //inicia a batalha
                    carregaBatalha.start();
                } else {
                    //avisa o usuario se nao der certo
                   txtMensagem.setText("Problemas ao iniciar a batalha");
                }
            } catch (IOException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void preencheAtacante() {
        //preenche o combobox com as habilidades de quem ataca no momento
        //e limpa os campos de informacoes
        Personagem atacante = batalha.getAtacante();
        cmbHabilidades.setDisable(false);
        if (atacante != null) {
            lblNomeAtacante.setText(atacante.getNome());
            cmbHabilidades.getItems().clear();
            for (Habilidade h : atacante.getClasse().getHabilidades()) {
                cmbHabilidades.getItems().add(h);
            }
        }
        lblDano.setText("");
        lblTempoEspera.setText("");
        lblAlvo.setText("");
        lblAfeta.setText("");
        lblPM.setText("");
    }

    public void carregaBatalha(Batalha batalha) {
        //metodo usado pelo form externo para passar a batalha como parametro
        this.batalha = batalha;
        //ao carregar a batalha, ja le o arquivo de fases e carrega
        batalha.carregaFases();
    }

    public void carregaInimigosNaTela() {
        //carrega inimigos na tela usando o form de personagem em FXML
        Equipe inimigos = batalha.getInimigos();
        double i = 0;
        //calcula um angulo de separacao para distribuir os inimigos na tela
        //de forma circular, em um raio especifico
        double angle = 360 / inimigos.getEquipe().size();
        double raio = 140.0;
        //limpa a lista de controllers das telas de inimigos
        pcInimigosList.clear();
        //remove, se houver, todos os inimigos que estavam no painel no turno
        //anterior
        pnlInimigos.getChildren().clear();
        for (Personagem p : inimigos.getEquipe()) {
            try {
                //carrega o form externo
                FXMLLoader personagemLoader = new FXMLLoader(getClass().getResource("../UI/Personagem.fxml"));
                Node formPersonagem = personagemLoader.load();
                //posiciona o form carregado na tela
                formPersonagem.setLayoutX(500 + Math.cos(Math.toRadians(i)) * raio);
                formPersonagem.setLayoutY(150 + Math.sin(Math.toRadians(i)) * raio);
                i += angle;
                //adiciona no painel de inimigos
                pnlInimigos.getChildren().add(formPersonagem);
                //pega o objeto de controle associado ao form
                PersonagemController pc = personagemLoader.getController();
                //invoca os metodos carrega e atualiza do controle, responsaveis por
                //atualizar as informacoes dos personagens no form carregado
                pc.carrega(p);
                pc.atualiza();
                //adiciona o controle em uma lista para uso posterior
                pcInimigosList.add(pc);
            } catch (IOException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cmbHabilidadeAction(ActionEvent event) {
        //evento do combo de habilidades, primeiro pega a habilidade
        Habilidade h = cmbHabilidades.getValue();
        //confere se nao esta nula. pode ocorrer do evento ser disparado quando
        //se limpa o combobox, deixando todas as posicoes nulas
        if (h != null) {
            //preenche todas os labels informativos da habilidade
            lblDano.setText(String.valueOf(h.getDano(batalha.getAtacante())));
            lblTempoEspera.setText(String.valueOf(h.getTempo()));
            lblAlvo.setText((h.afetaTodos() ? "Todos" : "Unico"));
            lblAfeta.setText((h.afetaAmigo() ? "Aliado" : "Inimigo"));
            lblPM.setText(String.valueOf(h.getCustoPM(batalha.getAtacante())));

            if (h.afetaTodos()) { //habilidades qua afetam todos nao usam o combo de alvo
                cmbAlvo.setDisable(true);
                cmbAlvo.getItems().clear();
            } else if (h.afetaAmigo()) {//quando afeta amigos, deve carregar o alvo com amigos
                cmbAlvo.setDisable(false);
                preencheAlvos(batalha.quemJoga());
            } else if (!h.afetaAmigo()) {//quando afeta inimigos, carrega com inimigos
                cmbAlvo.setDisable(false);
                preencheAlvos(batalha.quemEhAtacado());
            }
        }
    }

    //metodo que preenche o combo de alvos, para escolher quem recebe o ataque
    public void preencheAlvos(Equipe e) {
        cmbAlvo.setDisable(false);
        cmbAlvo.getItems().clear();
        for (Personagem p : e.getEquipe()) {
            if (p.getPV() > 0) {//se o personagem esta inativo, deixa ele de lado
                cmbAlvo.getItems().add(p);
            }
        }
    }

    @FXML
    private void btnUsaHabilidadeClick(ActionEvent event) throws InterruptedException {
        //inicialmente, define-se que o ataque nao ocorreu
        boolean atacou = false;
        //pega personagem que vai atacar
        Personagem atacante = batalha.getAtacante();
        //pega a habilidade selecionada
        Habilidade habilidade = cmbHabilidades.getValue();
        //inicializa personagem que sera atacado
        Personagem alvo = null;
        //confere se habilidade e atacante estao ok
        if (habilidade != null && atacante != null) {
            //primeiro trata caso onde alvo unico eh afetado
            if (!habilidade.afetaTodos()) {
                alvo = cmbAlvo.getValue();//pega o alvo no combobox
                if (alvo != null) {//confere se nao eh nulo
                    atacou = atacante.ataca(habilidade, alvo);//efetua o ataque e pega o boolean de resultado
                    if (habilidade.afetaAmigo()) {//se foi um amigo, eh cura, inverte o valor negativo da cura
                        mensagem = alvo.getNome() + " recebeu " + habilidade.getNome() + " de "
                                + atacante.getNome() + " curando " + (habilidade.getDano(atacante)*-1) + " pontos de vida.";
                    } else {//se for inimigo, causa dano
                        mensagem = alvo.getNome() + " recebeu " + habilidade.getNome() + " de "
                                + atacante.getNome() + " tomando dano de " + habilidade.getDano(atacante) + " pontos de vida.";
                    }
                } else {//alvo nulo, nao selecionado no combo
                    mensagem = "Selecione um alvo";
                }
            } else {
                Equipe afetados;//afeta todos
                if (habilidade.afetaAmigo()) {//afeta aliados
                    afetados = batalha.quemJoga();
                    mensagem = "Todos foram afetados por " + habilidade.getNome() + " recebendo cura de " + habilidade.getDano(atacante) + " pontos de vida";
                } else {//afeta inimigos
                    afetados = batalha.quemEhAtacado();
                    mensagem = "Todos foram afetados por " + habilidade.getNome() + " recebendo dano de " + habilidade.getDano(atacante) + " pontos de vida";
                }
                //ataca equipe completa
                atacou = atacante.ataca(habilidade, afetados);
                
            }
            if (!atacou) {
                mensagem = "Nao foi possivel efetuar o ataque";
            } else {
                //ataque deu certo, atualiza tempos e desativa combos antes do
                //proximo turno se iniciar
                batalha.atualizaTemposEspera();
                cmbHabilidades.setDisable(true);
                cmbAlvo.setDisable(true);
                iniciaTurno.cancel();//finaliza turno atual (thread)
                iniciaTurno.restart();//inicia novo turno
            }
        } else {
            mensagem = "Selecione uma habilidade a ser usada";
        }
        //exibe mensagem no label campo de texto
        txtMensagem.setText(mensagem);
    }

    //atualiza os formularios de todos os personagens na tela atraves
    //das listas armazenadas de objetos controle relacionados a eles
    public void atualizaPersonagens() {
        for (PersonagemController pc : pcInimigosList) {
            pc.atualiza();
        }
        for (PersonagemController pc : pcAliadosList) {
            pc.atualiza();
        }
    }

}
