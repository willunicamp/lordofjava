package personagens;


public class Personagem {
	private String nome;
	private int nivel, PE;
	private float PV, PM;
	private int tempoEspera;
	private Classe classe;
	private int ID;
	private static int ID_cont = 1;
	

	
	public Personagem(String nome, Classe.Tipo classe){
		this.setNome(nome);
		this.setNivel(1);
		this.setID();
		if(classe == Classe.Tipo.GUERREIRO){
			this.classe = new Guerreiro();
		}else if(classe == Classe.Tipo.ARQUEIRO){
			this.classe = new Arqueiro();
		}else if(classe == Classe.Tipo.MAGO){
			this.classe = new Mago();
		}else if(classe == Classe.Tipo.MONSTRO){
			this.classe = new Monstro();
		}
		this.setPV(this.getMaxPV());
		this.setPM(this.getMaxPM());
		this.setTempoEspera(0);
		this.setPE(0);
	}
	
	public void reviver(){
		this.setPV(this.getMaxPV());
		this.setPM(this.getMaxPM());
		this.setTempoEspera(0);
	}
	
	public void atualizaTempoEspera(){
		if(this.getTempoEspera() > 0){
			this.setTempoEspera(this.getTempoEspera()-1);
		}
	}
	
	private void setTempoEspera(int tempo){
		if(tempo < 0){
			this.tempoEspera = 0;
		}else{
			this.tempoEspera = tempo;
		}
	}
	
	public int getTempoEspera(){
		return this.tempoEspera;
	}
	
	public void setNome(String nome){
		if(!nome.trim().isEmpty()){
			this.nome = nome;
		}else{
			this.nome = Double.toString(Math.random());
		}
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setPV(float PV){
		if(PV < 0){
			this.PV = 0;
		}else if(PV > this.getMaxPV()){
			this.PV = this.getMaxPV();
		}else{
			this.PV = PV;
		}
	}
	
	public int getPV(){
		return (int)Math.ceil(this.PV);
	}
	
	public void setPM(float PM){
		if(PM < 0){
			this.PM = 0;
		}else if(PM > this.getMaxPM()){
			this.PM = this.getMaxPM();
		}else{
			this.PM = PM;
		}
	}
	
	public int getPM(){
		return (int)Math.ceil(this.PM);
	}
	
	public int getMaxPV(){
		return this.getNivel()*this.getForca()+
				this.getNivel()*this.getAgilidade()/2;
	}
	
	public int getMaxPM(){
		return this.getNivel()*this.getInteligencia()+
				this.getNivel()*this.getAgilidade()/3;
	}
	
	private void setNivel(int nivel){
		if(nivel < 1){
			this.nivel = 1;
		}else{
			this.nivel = nivel;
		}		
	}
	
	public void sobeParaNivel(int nivel){
		this.setNivel(nivel);
		reviver();
	}
	
	public int getNivel(){
		return this.nivel;
	}
	
	public int getPE(){
		return this.PE;
	}
	
	private void setPE(int PE){
		if(PE < 0){
			this.PE = 0;
		}else{
			this.PE = PE;
		}
	}
	
	public int getForca(){
		return this.getNivel()*this.getClasse().getForca();
	}
	
	public int getAgilidade(){
		return this.getNivel()*this.getClasse().getAgilidade();
	}
	
	public int getInteligencia(){
		return this.getNivel()*this.getClasse().getInteligencia();
	}
	
	public void recuperaPV(){
		this.setPV((float)(this.PV+0.1*this.getForca()+this.getNivel()*0.1));
	}
	
	public void recuperaPM(){
		this.setPM((float)(this.PM+0.1*this.getInteligencia()+this.getNivel()*0.05));
	}
	
	//metodo verifica se o personagem sobe de nivel
	//com os pontos de experiencia ganhados
	//retorna true se subir de nivel
	public boolean ganhaExpSobeNivel(int pontos){
		boolean retorno = false;
		int excedente, pexp;
		
		//se pontos nao forem negativos, ok
		if(pontos > 0){
			pexp = this.getPE();
			pexp += pontos; //faco a soma aos pontos atuais
			if(pexp >= this.getNivel()*25){
				//se tiver pontos necessarios pra subir de nivel,
				//calculo o excedente e aumento nivel+1
				excedente = pexp - this.getNivel()*25;
				this.nivel += 1;
			}else{
				//senao o excedente sera igual aos pontos ganhos
				excedente = pontos;
			}
			//no final, somo o excedente em PE
			this.setPE(this.getPE()+excedente);
		}
		//retorna true se subir de nivel
		return retorno;
	}
	
	public Classe getClasse(){
		return this.classe;
	}
	
	public boolean ataca(Habilidade hab, Personagem inimigo){
		boolean retorno = false;
		int dano = hab.getDano(this);
		int custoMagia = hab.getCustoPM(this);
		if(custoMagia <= this.getPM()){
			this.setPM(this.getPM()-custoMagia);
			inimigo.tomaDano(dano);
			this.setTempoEspera(hab.getTempo());
			retorno = true;
		}
		return retorno;
	}
	
	
	private void tomaDano(int dano){
		this.setPV(this.getPV()-dano);
	}

	public int getID() {
		return ID;
	}

	public void setID() {
		this.ID = Personagem.ID_cont++;
	}
}
