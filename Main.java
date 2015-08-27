package trabalho04;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

public class Main {

	public static void main(String[] args) throws IOException {
		Compressao c = new Compressao();
		/*
		File f = new File("D:\\FURB\\Alg\\17499.in");
		FileInputStream fi = new FileInputStream(f);
		DataInputStream di = new DataInputStream(fi);
		String s = "";
		char b = 0;
		try {
			while(true){
				b = (char) di.readByte();
				s+=b;
			}
		} catch(Exception e){}
		di.close();
		*/
		String s = "aaaal";
		String original = s;
		Long t = Calendar.getInstance().getTimeInMillis();
		s = c.comprime(s);
		Long tempoCompressao = Calendar.getInstance().getTimeInMillis()-t;
		String comprimido = s;
		String arvore = c.getLetras()[0].toString(); 
		//System.out.println(s);
		t = Calendar.getInstance().getTimeInMillis();
		//s = c.descomprime(s);
		Long tempoDescompressao = Calendar.getInstance().getTimeInMillis()-t;
		//System.out.println(s);
		
		System.out.println();
		
		System.out.println("Tempo compressao: "+tempoCompressao);
		System.out.println("Tempo descompressao: "+tempoDescompressao);
		System.out.println("Tamanho original: "+original.length());
		System.out.println("Tamanho arvore: "+arvore.length());
		System.out.println("Tamanho comprimido: "+comprimido.length());
		
		
		
		
	}

}
