package ufc.ia.cvts.entity;

import java.util.Random;

public class Cidade {
	
	private float x;
	private float y;
	private String nome;
	
	public Cidade(String nome, float x2, float y2) {
		this.nome = nome;
		this.x = x2;
		this.y = y2;
	}
	
	public float getX() {	return x; }
	public float getY() {	return y; }
	public String getNome() { return nome; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getDistancia(Cidade cidade){
		double x = this.getX()-cidade.getX();
		double y = this.getY()-cidade.getY();
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	/***/
	public static double getDistanciaDuasCidades(Cidade a, Cidade b){
		double x = a.getX()-b.getX();
		double y = a.getY()-b.getY();
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public static double calculaProbabilidade(double distanciaAtual, double distanciaVizinhanca, double tempera){
		if(distanciaVizinhanca < distanciaAtual){
			return 1;
		}
		
		return Math.exp((distanciaAtual - distanciaVizinhanca) / tempera);
	}
	
	
	public static int RandomIntFaixa(int min, int max){
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int)d;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String toString(){
		return this.nome;
	}
	
}
