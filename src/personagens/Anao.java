/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagens;

/**
 *
 * @author will
 */
public class Anao extends Classe{
    
    public Anao(){
        super(5,0,2);
        
        		
		FatorDeAtributos fatorDano, fatorMana;
		
		//definicoes da skill Machado Fatal
		fatorDano = new FatorDeAtributos(0.8f, 0.2f, 0.5f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Machado Fatal",
				5,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Socar; mana igual de cima
		fatorDano = new FatorDeAtributos(0.3f, 0.1f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Socar",
				4,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Tremor
		fatorDano = new FatorDeAtributos(0.4f, 0.1f, 0.1f);
		fatorMana = new FatorDeAtributos(0.1f, 0.0f, 1.0f);
		habilidades.add(new Habilidade("Tremor",
				5,true, fatorDano, fatorMana, false));
    }
    
    	@Override
	public String toString(){
		return "Anão";
	}
    
}
