package principal;

import personagens.Personagem;
import java.util.ArrayList;

public class Equipe {
	private ArrayList<Personagem> grupo;
	
	public Equipe(){
		grupo = new ArrayList<Personagem>();
	}
	
	public boolean adicionaPersonagem(Personagem p){
		boolean retorno = false;
		if(p != null){
			grupo.add(p);
			retorno = true;
		}
		return retorno;
	}
	
	public int contaConscientes(){
		int retorno = 0;
		for(Personagem p: grupo){
			if(p.getPV() > 0){
				retorno++;
			}
		}
		return retorno;
	}
	
	public void ganharExperiencia(Equipe inimigos){
		int PE = 0;
		for(Personagem p: inimigos.getEquipe()){
			PE += p.getNivel()*5;
		}
		for(Personagem p: grupo){
			p.ganhaExpSobeNivel(PE);
		}
	}
	
	public void reviverTodos(){
		for(Personagem p: grupo){
			p.reviver();
		}
	}
	
	public ArrayList<Personagem> getEquipe(){
		return grupo;
	}
	
	public Personagem proximoQueAtaca(){
		Personagem atacante = null;
		for(Personagem p: grupo){
			if(p.getTempoEspera() == 0 && p.getPV() > 0){
				atacante = p;
			}
		}
		return atacante;
	}
	
	public void atualizaTemposEspera(){
		for(Personagem p: grupo){
			if(p.getTempoEspera() > 0){
				p.atualizaTempoEspera();
			}
		}
	}
	
	public Personagem getPersonagem(int id){
		Personagem retorno = null;
                try{
                    for(Personagem p: grupo){
                            if(p.getID() == id){
                                    retorno = p;
                            }
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(e.getMessage());
                }
		return retorno;
	}
}
