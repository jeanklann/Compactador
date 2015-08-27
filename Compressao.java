package trabalho04;

public class Compressao {
	private Letra[] letras = new Letra[0];
	private static int count = 0;
	
	public String comprime(String texto){
		conta(texto);
		montaArvore();
		String res = letras[0].toString();
		res += montaBinario(texto);
		return res;
	}
	public String descomprime(String texto){
		montaArvore(texto);
		String str = montaLetras(texto);
		return str; 
	}
	
	private String montaLetras(String texto){
		Letra l = letras[0];
		String str = "";
		String quant = "";
		while(texto.charAt(count)!=':'){
			quant+=texto.charAt(count);
			count++;
		}
		int q = Integer.parseInt(quant);
		int qfoi = 0;
		count++;
		String textoTemp = "";
		for (int i = count; i < texto.length(); i++) {
			String tmp = Integer.toBinaryString(texto.charAt(i));
			while(tmp.length()<8){
				tmp = '0'+tmp;
			}
			if(tmp.length()>8)
				tmp = tmp.substring(8);
			textoTemp+=tmp;
		}
		//System.out.println(texto);
		//System.out.println(textoTemp);
		//System.out.println("texto:"+texto.substring(count));
		for (int i = 0; i < textoTemp.length(); i++) {
			if(textoTemp.charAt(i)=='0'){
				l = l.getEsquerda();
			} else if(textoTemp.charAt(i)=='1'){
				l = l.getDireito();
			}
			if(l.getLetra()!=0){
				str+=l.getLetra();
				l = letras[0];
				qfoi++;
				if(qfoi>=q) break;
			}
		}
		return str;
	}
	private String montaBinario(String tmp){;
		String str = "";
		String str8bits = "";
		for (int i = 0; i < tmp.length(); i++) {
			str8bits += letras[0].find(tmp.charAt(i));
			if(str8bits.length()>8){
				String strTemp = str8bits.substring(0, 8);
				//System.out.print(strTemp);
				//System.out.println("asd:"+strTemp+".");
				char c = (char) Integer.valueOf(strTemp, 2).byteValue();
				str += c;
				if(str8bits.length()>8)
					str8bits = str8bits.substring(8);
				else
					str8bits="";
			}
		}
		while(str8bits.length()<8){
			str8bits = str8bits+"0";
		}
		//System.out.print(str8bits);
		
		char c = (char) Integer.valueOf(str8bits, 2).byteValue();
		str += c;
		str = tmp.length()+":"+str;
		return str;
		
	}
	private void montaArvore(String texto){
		letras = new Letra[1];
		letras[0] = new Letra();
		count = 0;
		montaArvoreR(letras[0], texto);
	}
	
	private void montaArvoreR(Letra l, String str){
		if(str.charAt(count)!='<' && str.charAt(count)!='>'){
			if(str.charAt(count)=='\\')
				count++;
			l.setLetra(str.charAt(count));
			count++;
		}
		if(str.charAt(count)=='<'){
			l.setEsquerda(new Letra());
			count++;
			montaArvoreR(l.getEsquerda(), str);
		}
		if(str.charAt(count)=='<'){
			l.setDireito(new Letra());
			count++;
			montaArvoreR(l.getDireito(), str);
		}
		if(str.charAt(count)=='>'){
			count++;
			return;
		}

		
		return;
	}
	
	private void montaArvore(){
		while(letras.length > 1){
			ordena();
			Letra h = new Letra();
			h.setDireito(letras[0]);
			h.setEsquerda(letras[1]);
			h.setFrequencia(letras[0].getFrequencia()+letras[1].getFrequencia());
			letras[1] = h;
			Letra[] tmp = new Letra[letras.length-1];
			for (int i = 1; i < letras.length; i++) {
				tmp[i-1] = letras[i];
			}
			letras = tmp;
		}
	}
	
	public void conta(String tmp){
		letras = new Letra[0];
		for (int i = 0; i < tmp.length(); i++) {
			int e = encontra(tmp.charAt(i));
			if(e==-1){
				Letra[] tmp2 = new Letra[letras.length+1];
				for (int j = 0; j < letras.length; j++) {
					tmp2[j] = letras[j];
				}
				letras = tmp2;
				letras[letras.length-1] = new Letra();
				letras[letras.length-1].setFrequencia(1);
				letras[letras.length-1].setLetra(tmp.charAt(i));
			} else {
				letras[e].setFrequencia(letras[e].getFrequencia()+1);
			}
		}
		
	}
	public void ordena(){
		quickSort(letras, 0, letras.length-1);
	}
	
	private int encontra(char letra){
		for (int i = 0; i < letras.length; i++) {
			if(letras[i].getLetra()==letra)
				return i;
		}
		return -1;
	}
	
	public void quickSort(Letra[] vetor,int inicio, int fim) {
		   int meio;
		 
		   if (inicio < fim) {
		     meio = quickSortR(vetor, inicio, fim);
		     quickSort(vetor, inicio, meio);
		     quickSort(vetor, meio + 1, fim);
		   }
		 }
		 
		 public int quickSortR(Letra[] vetor, int inicio, int fim) {
		   int topo;
		   Letra n;
		   n = vetor[inicio];
		   topo = inicio;
		 
		   for (int i = inicio + 1; i <= fim; i++) {
		     if (vetor[i].getFrequencia() < n.getFrequencia()) {
		    	 vetor[topo] = vetor[i];
		    	 vetor[i] = vetor[topo + 1];
		    	 topo++;
		     }
		   }
		   vetor[topo] = n;
		   return topo;
		 }
		public Letra[] getLetras() {
			return letras;
		}
		 
}
