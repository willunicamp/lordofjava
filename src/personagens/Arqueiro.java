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


public class Arqueiro extends Classe{
	public Arqueiro(){
		super(1, 3, 2);
		
		FatorDeAtributos fatorDano, fatorMana;
		
		//definicoes da skill Atirar Flecha
		//forca, agilidade, inteligencia
		fatorDano = new FatorDeAtributos(0.3f, 0.5f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Atirar Flecha",
				4,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Socar; mana igual de cima
		fatorDano = new FatorDeAtributos(0.1f, 0.3f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Socar",
				3,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Flecha encantada
		fatorDano = new FatorDeAtributos(0.3f, 0.5f, 0.4f);
		fatorMana = new FatorDeAtributos(0.0f, 0.2f, 1.0f);
		habilidades.add(new Habilidade("Flecha Encantada",
				7,false, fatorDano, fatorMana, false));
	}
	
	@Override
	public String toString(){
		return "Arqueiro";
	}
        
}
