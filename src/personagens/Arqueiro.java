package personagens;


public class Arqueiro extends Classe{
	public Arqueiro(){
		super(1, 3, 2);
		
		FatorDeAtributos fatorDano, fatorMana;
		
		//definicoes da skill Atirar Flecha
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
}
