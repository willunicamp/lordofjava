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


import personagens.Personagem;

public class Habilidade{
	
	private String nome;
	private FatorDeAtributos fatorDano, fatorMana;
	private int tempo;
	private boolean afetaTodos;
	private boolean amigo;
	private static int ID_cont = 1;
	public int ID;
	
	public Habilidade(String nome, int tempo, boolean afetaTodos, 
	FatorDeAtributos fatorDano, FatorDeAtributos fatorMana, boolean afetaAmigo){
		
		this.setNome(nome);
		this.setTempo(tempo);
		this.setAfetaTodos(afetaTodos);
		this.setFatorDano(fatorDano);
		this.setFatorMana(fatorMana);
		this.setAfetaAmigo(afetaAmigo);
		this.setID();
	}
	
	public void setID(){
		this.ID = Habilidade.ID_cont++;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		if(tempo>0){
			this.tempo = tempo;
		}else{
			//valor default no caso de tempo negativo ou zerado
			this.tempo = 10;
		}
	}
	public boolean afetaTodos() {
		return this.afetaTodos;
	}
	public void setAfetaTodos(boolean afetaTodos) {
		this.afetaTodos = afetaTodos;
	}
	public FatorDeAtributos getFatorDano() {
		return fatorDano;
	}
	public void setFatorDano(FatorDeAtributos fatorDano) {
		this.fatorDano = fatorDano;
	}
	public FatorDeAtributos getFatorMana() {
		return fatorMana;
	}
	public void setFatorMana(FatorDeAtributos fatorMana) {
		this.fatorMana = fatorMana;
	}
	
	public int getCustoPM(Personagem p){
		float mana = 0;
		mana += this.getFatorMana().getForca()*p.getForca();
		mana += this.getFatorMana().getAgilidade()*p.getAgilidade();
		mana += this.getFatorMana().getInteligencia()*p.getInteligencia();
		return (int)(p.getNivel()*Math.ceil(mana));
	}
	
	public int getDano(Personagem p){
		float dano = 0;
		dano += this.getFatorDano().getForca()*p.getForca();
		dano += this.getFatorDano().getAgilidade()*p.getAgilidade();
		dano += this.getFatorDano().getInteligencia()*p.getInteligencia();
		return (int)(p.getNivel()*Math.ceil(dano));
	}

	public boolean afetaAmigo() {
		return amigo;
	}

	public void setAfetaAmigo(boolean amigo) {
		this.amigo = amigo;
	}
}