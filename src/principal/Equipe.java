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

import personagens.Personagem;
import java.util.ArrayList;

public class Equipe {
	private ArrayList<Personagem> grupo;
	
	public Equipe(){
		grupo = new ArrayList<Personagem>();
	}
	
	public boolean adicionaPersonagem(Personagem p){
		boolean retorno = false;
		if(p != null){
			grupo.add(p);
			retorno = true;
		}
		return retorno;
	}
	
	public int contaConscientes(){
		int retorno = 0;
		for(Personagem p: grupo){
			if(p.getPV() > 0){
				retorno++;
			}
		}
		return retorno;
	}
	
	public void ganharExperiencia(Equipe inimigos){
		int PE = 0;
		for(Personagem p: inimigos.getEquipe()){
			PE += p.getNivel()*5;
		}
		for(Personagem p: grupo){
			p.ganhaExpSobeNivel(PE);
		}
	}
	
	public void reviverTodos(){
		for(Personagem p: grupo){
			p.reviver();
		}
	}
	
	public final ArrayList<Personagem> getEquipe(){
		return grupo;
	}
	
	public Personagem proximoQueAtaca(){
		Personagem atacante = null;
		for(Personagem p: grupo){
			if(p.getTempoEspera() == 0 && p.getPV() > 0){
				atacante = p;
			}
		}
		return atacante;
	}
	
	public void atualizaTemposEspera(){
		for(Personagem p: grupo){
			if(p.getTempoEspera() > 0){
				p.atualizaTempoEspera();
			}
		}
	}
	
	public Personagem getPersonagem(int id){
		Personagem retorno = null;
                try{
                    for(Personagem p: grupo){
                            if(p.getID() == id){
                                    retorno = p;
                            }
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(e.getMessage());
                }
		return retorno;
	}
}
