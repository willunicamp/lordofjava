package personagens;

import java.util.ArrayList;

public abstract class Classe {
	private int forca, agilidade, inteligencia;
	protected ArrayList<Habilidade> habilidades;
	
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
}
