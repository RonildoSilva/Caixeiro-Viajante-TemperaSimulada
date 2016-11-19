package ufc.ia.cvts.util;

import java.util.Collections;

import ufc.ia.cvts.entity.Caminho;
import ufc.ia.cvts.entity.Cidade;

public class Operacao {
	
	private int posicaoInicial;
	private int posicaoFinal;
	private TipoOperacao tipoOperacao;
	
	public Operacao(int posicaoInicial, int posicaoFinal, TipoOperacao tipoOperacao) {
		this.posicaoInicial = posicaoInicial;
		this.posicaoFinal = posicaoFinal;
		this.tipoOperacao = tipoOperacao;
	}

	public int getPosicaoInicial() {
		return posicaoInicial;
	}

	public int getPosicaoFinal() {
		return posicaoFinal;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public Caminho operacaoShufle(Caminho caminho){
		
		//Operacao 1
		if(this.tipoOperacao == TipoOperacao.OP1) {
			System.out.println("Operacao 1");
			Cidade a = caminho.getCaminho().get(this.posicaoInicial);
			Cidade b = caminho.getCaminho().get(this.posicaoFinal);
			
			caminho.getCaminho().set(this.posicaoFinal, a);
			caminho.getCaminho().set(this.posicaoInicial, b);
			
			return caminho;
		}
		//Operacao 2
		else {
			System.out.println("Operacao 2");
			Collections.reverse(caminho.getCaminho().subList(this.posicaoInicial, this.posicaoFinal));
			
			return caminho;
		}
		
	}
}

