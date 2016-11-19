package ufc.ia.cvts.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import ufc.ia.cvts.entity.Caminho;
import ufc.ia.cvts.entity.Cidade;
import ufc.ia.cvts.util.ManipuladorArquivos;
import ufc.ia.cvts.util.Operacao;
import ufc.ia.cvts.util.TipoOperacao;

public class Main {

	public static int QUTD_PONTOS = 15;
	public static ManipuladorArquivos mArq = new ManipuladorArquivos();
	
	public static double fTemp1 = 500;
	public static double fTemp2 = 1000;
	public static double fTemp3 = 1500;
	
	public static double sResfri1 = 0.999999;
	public static double sResfri2 = 0.000001;
	
	
	
	
	public static Random randomNumber;
	
	public static void main(String[] args) {
		randomNumber = new Random();

		//mArq.gerarPontos(QUTD_PONTOS);
		Caminho caminhoSimulacao = new Caminho(mArq.carregar());
		Caminho melhorCaminhoSimulacao = new Caminho(caminhoSimulacao.getCaminho());
		
		int debug = 1;

		
		System.out.println(debug++);
		simulacaoTempera(fTemp1,sResfri1,caminhoSimulacao,TipoOperacao.OP1,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp2,sResfri1,caminhoSimulacao,TipoOperacao.OP1,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp3,sResfri1,caminhoSimulacao,TipoOperacao.OP1,5,9);
		
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp1,sResfri1,caminhoSimulacao,TipoOperacao.OP2,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp2,sResfri1,caminhoSimulacao,TipoOperacao.OP2,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp3,sResfri1,caminhoSimulacao,TipoOperacao.OP2,5,9);
		
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp1,sResfri2,caminhoSimulacao,TipoOperacao.OP1,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp2,sResfri2,caminhoSimulacao,TipoOperacao.OP1,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp3,sResfri2,caminhoSimulacao,TipoOperacao.OP1,5,9);
		
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp1,sResfri2,caminhoSimulacao,TipoOperacao.OP2,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp2,sResfri2,caminhoSimulacao,TipoOperacao.OP2,5,9);
		
		caminhoSimulacao = new Caminho(mArq.carregar());
		System.out.println(debug++);
		simulacaoTempera(fTemp3,sResfri2,caminhoSimulacao,TipoOperacao.OP2,5,9);
			
		
	}

	public static String getCurrentTime() {
		Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss.SSS");
        String t1 = sdf1.format(cal1.getTime()).toString();
		return t1;
	}

	public static void simulacaoTempera(double tempera, double resfriamento,
			Caminho caminho, TipoOperacao tipoOperacao,
			int inicio, int fim) {
		
		System.out.println("Caminho Inicial: \t" + caminho.getDistanciaTotal());
		
		String t1 = getCurrentTime();
		
		Operacao op = new Operacao(inicio, fim, tipoOperacao);
		op.operacaoShufle(caminho);
	
		Caminho melhorCaminho = new Caminho(caminho.getCaminho());
		
		while (tempera > 1) {

			Caminho novaSolucao = new Caminho(caminho.getCaminho());

			int posicaoA = Cidade.RandomIntFaixa(0, caminho.getCaminho().size());
			int posicaoB = Cidade.RandomIntFaixa(0, caminho.getCaminho().size());

			Cidade cidadeA = novaSolucao.getCidade(posicaoA);
			Cidade cidadeB = novaSolucao.getCidade(posicaoB);

			novaSolucao.getCaminho().set(posicaoB, cidadeA);
			novaSolucao.getCaminho().set(posicaoA, cidadeB);

			double distanciaAtual = caminho.getDistanciaTotal();
			double distanciaVizinhanca = novaSolucao.getDistanciaTotal();

			double random = randomNumber.nextDouble();
			
			if(Cidade.calculaProbabilidade(distanciaAtual, distanciaVizinhanca, tempera) > random){
				caminho = new Caminho(caminho.getCaminho());
			}

			if(caminho.getDistanciaTotal() < melhorCaminho.getDistanciaTotal()){
				melhorCaminho = new Caminho(caminho.getCaminho());
			}

			tempera *= resfriamento;
		}
		
		String t2 = getCurrentTime();

		System.out.println("Tempo t1:\t\t" + t1 );
		System.out.println("Tempo t2:\t\t" + t2 );
		
		melhorCaminho.getCidade(0).setNome("INI");
		melhorCaminho.getCidade(QUTD_PONTOS-1).setNome("FIM");
		System.out.println("Caminho Otimizado: \t" + melhorCaminho.getDistanciaTotal());
		mArq.escreverResultado(cidadesToJS(melhorCaminho.getCaminho()));
	}

	//Mapeia os nos (cidades) e escreve o diagrama
	public static String cidadesToJS (ArrayList<Cidade> listaCidades){

		String nodesHTML = "";

		for (int i = 0; i < listaCidades.size(); i++) {
			nodesHTML += "{name: '"+listaCidades.get(i).getNome()+"', "
					+ "row: "+listaCidades.get(i).getX()+" , "
					+ "column: "+listaCidades.get(i).getY()+", connectsTo: "
					+ "'"+listaCidades.get(i+1).getNome()+"'},";

			if(i == listaCidades.size()-2){
				nodesHTML += "{name: '"+listaCidades.get(i+1).getNome()+"', "
						+ "row: "+listaCidades.get(i+1).getX()+" , "
						+ "column: "+listaCidades.get(i+1).getY()+", "
						+ "connectsTo: '"+listaCidades.get(0).getNome()+"'}";
				break;
			}
		}

		return nodesHTML;
	}
}
