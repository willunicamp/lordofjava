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


public class FatorDeAtributos {
	private float forca, agilidade, inteligencia;
	
	public FatorDeAtributos(float forca, float agilidade, float inteligencia){
		this.setForca(forca);
		this.setAgilidade(agilidade);
		this.setInteligencia(inteligencia);
	}

	public float getForca() {
		return forca;
	}

	public void setForca(float forca) {
		this.forca = forca;
	}

	public float getAgilidade() {
		return agilidade;
	}

	public void setAgilidade(float agilidade) {
		this.agilidade = agilidade;
	}

	public float getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(float inteligencia) {
		this.inteligencia = inteligencia;
	}
	
	
}
