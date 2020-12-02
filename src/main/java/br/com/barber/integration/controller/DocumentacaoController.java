package br.com.barber.integration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class DocumentacaoController {
	
	@RequestMapping("/docs")
	public String docs() {
		return "redirect:swagger-ui.html";
	}
}
