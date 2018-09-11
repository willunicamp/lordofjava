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
package personagens;

import java.util.ArrayList;

public abstract class Classe {
	private int forca, agilidade, inteligencia;
	ArrayList<Habilidade> habilidades;
	
	public enum Tipo{
		GUERREIRO, ARQUEIRO, MAGO, MONSTRO;
	}
	
	public Classe(int forca, int agilidade, int inteligencia){
		this.setForca(forca);
		this.setAgilidade(agilidade);
		this.setInteligencia(inteligencia);
		habilidades = new ArrayList<Habilidade>();
	}
	
	public ArrayList<Habilidade> getHabilidades(){
		return this.habilidades;
	}
	
	public Habilidade getHabilidade(int id){
		Habilidade retorno = null;
                try{
		for(Habilidade h: habilidades){
			if(h.getID() == id){
				retorno = h;
			}
		}
                }catch(ArrayIndexOutOfBoundsException e){
                    
                }
		return retorno;
	}
	
	private void setForca(int forca){
		if(forca > 0){
			this.forca = forca;
		}else{
			this.forca = 0;
		}
	}
	
	private void setAgilidade(int agilidade){
		if(agilidade > 0){
			this.agilidade = agilidade;
		}else{
			this.agilidade = 0;
		}
	}
	
	private void setInteligencia(int inteligencia){
		if(inteligencia > 0){
			this.inteligencia = inteligencia;
		}else{
			this.inteligencia = 0;
		}
	}
	
	public int getForca(){
		return this.forca;
	}
	
	public int getAgilidade(){
		return this.agilidade;
	}
	
	public int getInteligencia(){
		return this.inteligencia;
	}
	
	@Override
	public abstract String toString();
}
