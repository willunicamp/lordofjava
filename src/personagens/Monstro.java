package personagens;


public class Monstro extends Classe{
	public Monstro(){
		super(4, 1, 0);
		
		FatorDeAtributos fatorDano, fatorMana;
		//definicoes da skill Chutar
		fatorDano = new FatorDeAtributos(1.0f, 0.5f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Chutar",
				8,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Socar; mana igual de cima
		fatorDano = new FatorDeAtributos(0.8f, 0.4f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Socar",
				5,false, fatorDano, fatorMana, false));
		
		//definicoes da skill Grito atordoante
		fatorDano = new FatorDeAtributos(0.2f, 0.1f, 0.0f);
		fatorMana = new FatorDeAtributos(0.0f, 0.0f, 0.0f);
		habilidades.add(new Habilidade("Grito Atordoante",
				6,true, fatorDano, fatorMana, false));
	}
}
