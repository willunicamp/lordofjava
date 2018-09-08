package personagens;


public class Guerreiro extends Classe {
	public Guerreiro(){
		super(4, 1, 1);
		
		FatorDeAtributos fatorDano, fatorMana;
		
		//definicoes da skill Golpe de Espada
		fatorDano = new FatorDeAtributos(0.7f, 0.3f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Golpe de Espada",
				5,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Socar; mana igual de cima
		fatorDano = new FatorDeAtributos(0.3f, 0.1f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Socar",
				4,false, fatorDano, fatorMana, false));
		
		//definicoes da skill EspadaFlamejante
		fatorDano = new FatorDeAtributos(0.7f, 0.3f, 0.4f);
		fatorMana = new FatorDeAtributos(0.2f, 0.0f, 1.0f);
		habilidades.add(new Habilidade("Espada Flamejante",
				7,false, fatorDano, fatorMana, false));
		
	}
	
}
