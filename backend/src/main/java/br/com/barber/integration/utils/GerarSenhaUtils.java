package br.com.barber.integration.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenhaUtils {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123"));
		
	}
	
}
