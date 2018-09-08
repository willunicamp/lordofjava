package personagens;


public class Mago extends Classe{
	public Mago(){
		super(1, 2, 3);
		
		FatorDeAtributos fatorDano, fatorMana;
		
		//definicoes da skill Enfraquecer
		fatorDano = new FatorDeAtributos(0.3f, 0.2f, 0.5f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.5f);
		habilidades.add(new Habilidade("Enfraquecer",
				5,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Socar; mana igual de cima
		fatorDano = new FatorDeAtributos(0.1f, 0.1f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Socar",
				2,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Cura amigo
		fatorDano = new FatorDeAtributos(0.0f, 0.0f, -0.8f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.7f);
		habilidades.add(new Habilidade("Cura Amigo",
				7,false, fatorDano, fatorMana, true));
	}
}
