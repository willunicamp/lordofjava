/* 
 * This file is part of the LordOfJava distribution (https://github.com/willunicamp/lordofjava).
 * Copyright (c) 2018 William Paiva
 * 
 * This program is free software: you can redistribute it and/or modify  
 * it under the terms of the GNU General Public License as published by  
 * the Free Software Foundation, version 3.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package principal;

import personagens.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Batalha{
	Equipe aliados;
        Equipe inimigos;
        Personagem atacante;
        String textoFase;
	boolean vezAliados;
	boolean fim;
	Clip clip;
	static int turno = 0;
        List<String> fases;
        Iterator<String> fase;
        
	
	public Batalha(){
		fim = false;
                aliados = new Equipe();
                fases = new LinkedList<>();
                fase = fases.iterator();
	}
        
        public Equipe getAliados(){
            return this.aliados;
        }
        
        
        
        public Equipe quemJoga(){
            if(vezAliados){
                return this.aliados;
            }
            return this.inimigos;
        }
        
        public Equipe quemEhAtacado(){
            if(vezAliados){
                return this.inimigos;
            }
            return this.aliados;
        }
        
        public Equipe getInimigos() {
            return this.inimigos;
        }
        
        public Classe.Tipo[] getTipos(){
            return Classe.Tipo.values();
        }
	
	public void playSomEpico(){
	        AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("epic.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			Logger.getLogger(Batalha.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				audioInputStream.close();
			} catch (IOException ex) {
				Logger.getLogger(Batalha.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public void paraSomEpico(){
	    try {
	        clip.stop();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	private void espere(int segundos){
		try {
		    Thread.sleep(segundos*1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
        
        public String getTextoFase() {
            return textoFase;
        }

        public void setTextoFase(String textoFase) {
            this.textoFase = textoFase;
        }
        
        public void carregaFases(){
            Path arquivo = Paths.get("game.txt");
            List<String> fases_temp = new LinkedList<>();
            BufferedReader buf_fases;
            String linha;
            if(Files.exists(arquivo)){
                try {
                        buf_fases = Files.newBufferedReader(arquivo);
                        while((linha = buf_fases.readLine()) != null){
                            fases_temp.add(linha);
                        } 
                }catch (IOException ex) {
                    Logger.getLogger(Batalha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fases = fases_temp;
            fase = fases.iterator();
        }
        
        public boolean acabouTudo(){
            return (fase == null);
        }
                
        public void carregaProximaFase(){
            inimigos = new Equipe();
            do{
                String linha = fase.next();
                String[] comando = linha.split(" ");
                if(comando[0].equals("fase")){
                    textoFase = linha.substring(4);
                    return;
                }else{
                    String nome = comando[0];
                    Classe.Tipo tipo = selecionaTipo(comando[1]);
                    int nivel = Integer.parseInt(comando[2]);
                    Personagem inimigo = new Personagem(nome, tipo);
                    inimigo.sobeParaNivel(nivel);
                    inimigos.adicionaPersonagem(inimigo);
                }
            }while(fase.hasNext());
            //Se passar por todas as fases, inimigos null indica fim do jogo
            inimigos = null;
        }
	
	private Classe.Tipo selecionaTipo(String tipo){
		Classe.Tipo retorno = null;
		switch(tipo){
			case "monstro":
				retorno = Classe.Tipo.MONSTRO;
			break;
			case "mago":
				retorno = Classe.Tipo.MAGO;
			break;
			case "arqueiro":
				retorno = Classe.Tipo.ARQUEIRO;
			break;
			case "guerreiro":
				retorno = Classe.Tipo.GUERREIRO;
			break;
                        case "anao":
				retorno = Classe.Tipo.ANAO;
			break;
		}
		return retorno;
	}
        
        public void adicionaAliado(String nome, Classe.Tipo tipo){
                    if(!nome.trim().isEmpty() && tipo != null){
                        Personagem p = new Personagem(nome, tipo);
			aliados.adicionaPersonagem(p);
                    }
		}
        
        public boolean rodadaAcabou(){
            return !(aliados.contaConscientes() > 0 && inimigos.contaConscientes() > 0 && !fim);
        }

        public void terminar(){
            this.fim = true;
        }
        
        public void atualizaTemposEspera(){
            aliados.atualizaTemposEspera();
            inimigos.atualizaTemposEspera();
        }
	
	public void print(String p){
		System.out.println(p);
	}
        
        public Personagem getAtacante(){
            return atacante;
        }
		
	public Personagem escolheAtacante(){
		atacante = null;
		Personagem aliado, inimigo;
		int sorte = (int)(Math.random()*100);
		sorte = sorte%2;
		aliado = aliados.proximoQueAtaca();
		inimigo = inimigos.proximoQueAtaca();
		
		if(aliado != null && inimigo != null){
			if(sorte == 0){
				atacante = aliado;
				vezAliados = true;
			}else{
				atacante = inimigo;
				vezAliados = false;
			}
		}else if(inimigo == null){
			atacante = aliado;
			vezAliados = true;
		}else if(aliado == null){
			atacante = inimigo;
			vezAliados = false;
		}
		
		return atacante;
	}

}
