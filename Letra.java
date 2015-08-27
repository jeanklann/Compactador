package trabalho04;

public class Letra {
	private char letra = 0;
	private int frequencia = 0;
	private Letra direito;
	private Letra esquerda;
	
	public Letra getDireito() {
		return direito;
	}
	public void setDireito(Letra direito) {
		this.direito = direito;
	}
	public Letra getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(Letra esquerda) {
		this.esquerda = esquerda;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public String toString(){
		String str = "";
		if(letra != 0){
			if(letra=='<' || letra=='>')
				str+='\\';
			str+=letra;
		}
		if(esquerda!=null)
			str+="<"+esquerda+">";
		if(direito!=null)
			str+="<"+direito+">";
		return str;
	}
	public String find(char c){
		if(direito==null)
			return null;
		if(direito.letra==c)
			return "1";
		else if(esquerda.letra==c)
			return "0";
		String tmp = direito.find(c);
		if(tmp==null){
			tmp = esquerda.find(c);
			if(tmp==null)
				return null;
			tmp = "0"+tmp;		
		} else {
			tmp = "1"+tmp;
		}
		return tmp;
	}
	
}
