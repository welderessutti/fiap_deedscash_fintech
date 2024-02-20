package br.com.deedscash.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CriptografiaUtils {
	
	public static String criptografar(String senha) throws Exception {
		//Obtém a instância de um algoritmo
		MessageDigest md = MessageDigest.getInstance("MD5");
		//Passa os dados a serem criptografados e estipula enconding padrão
		md.update(senha.getBytes("ISO-8859-1"));
		//Gera a chave criptografada em array de Bytes para posterior hashing
		BigInteger hash = new BigInteger(1, md.digest());
		//Retorna a senha criptografada
		return hash.toString(16);
	}
}
