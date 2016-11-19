package ufc.ia.cvts.entity;

import java.util.ArrayList;
import java.util.Collections;

public class Caminho {

	private ArrayList<Cidade> caminho = new ArrayList<Cidade>();

	private int distancia = 0;

	public Caminho() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < caminho.size(); i++) {
			caminho.add(null);
		}
	}

	public Caminho(ArrayList<Cidade> caminho){
		//		this.caminho = (ArrayList<Cidade>) caminho.clone();
		this.caminho = caminho;
	}

	//	public void getIndividual(){
	//		for (int i = 0; i < caminho.size(); i++) {
	//			setCidade(i, caminho.get(i));
	//		}
	//		Collections.shuffle(caminho);
	//	}

	public Cidade getCidade(int index){
		return caminho.get(index);
	}

	public double getDistanciaTotal(){
		int qtdCidades = this.caminho.size();

		return this.caminho.stream().mapToDouble(x -> {
			int indiceCidade = this.caminho.indexOf(x);

			double valor = 0;

			if(indiceCidade < qtdCidades - 1)
				valor = x.getDistancia(this.caminho.get(indiceCidade + 1));

			return valor;
		}).sum() + this.caminho.get(qtdCidades - 1).getDistancia(this.caminho.get(0));
	}

	public ArrayList<Cidade> getCaminho() {
		return this.caminho;
	}

	public void setCaminho(ArrayList<Cidade> caminho) {
		this.caminho = caminho;
	}


}
