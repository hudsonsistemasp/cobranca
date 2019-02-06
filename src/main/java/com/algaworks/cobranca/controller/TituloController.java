package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Controller
public class TituloController {
	
	/*2° passo: Uso do objeto que está na camada Repository. Mas para apontar o objeto e esse seja injetado aqui nessa classe
	 é necessário colocar a notação @Autowired e assim o Spring localiza a implementação desse objeto e posso usar os metodos.
	 Se eu não fizer essa notação, dará nullPoint, pois o spring não sabe de onde vem esse objeto.*/
	@Autowired
	private Titulos titulos;
	
	
	//É a URL mapeada que apontará para a CadastroTitulo.html ou outra extensão
	@RequestMapping("/titulos/novo")
	//public String novo() {
	public ModelAndView novo() {
		/* return "CadastroTitulo"; >> nome da página view sem extensão, pois quando eu quiser mudar a extensão,  
		  caso de alguma migração do front end, tipo para angular, JSP..., chamará sem problema*/
		//return "CadastroTitulo";//Retorno para essa página, mas poderia enviar para uma página personalizada "SalvoComSucesso"
		
		//Nessa URL vou disponiblizar a lista de OPTION dinamicamente, ao invés de dados digitados no HTML. Vqi que preciso fazer isso em 10 páginas ou mais... Para isso, esse método não retornará mais tipo String e sim ModelAndView
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		//Essa linha debaixo foi substituída pelo método que contém a notação @ModelAttriibute("todosStatusTitulo")
		//mv.addObject("todosStatusTitulo", StatusTitulo.values());//Parâm método:"nomeDaVariável","Método da classe que contém o Enum"
		return mv;
	}
	
	//SALVANDO OS DADOS NO BANCO
	/*Esse método vai levar os objetos da página para salvar no banco e por isso passo como parâmetro
	 o objeto "Titulo", que representa a página web, e não preciso passar campo a campo como parâmetro.
	 Basta os campos do FRONT(NO CASO SÃO OS INPUTS) terem o mesmo nome(PROPRIEDADE NAME) dos atributos da camada MODEL.
	 (Esse é o link da view com a model, HTML propriedade NAME com o mesmo nome que as propriedades que estão na MODEL JPA)
	 Agora preciso mapear a URL que vai ser entregue nesse método com esses dados: @RequestMapping() */
	@RequestMapping(value = "/titulos", method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		System.out.println("\n>>>" + titulo.getDescricao());
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Dado salvo com sucesso!");//Parâmetros desse método:"nomeDaVariável","Mensagem que será exibida"
		return mv;
	}
	
	//Assim que enviei a lista com os Enum, a URL acima não chama de novo esses ENUM. Para isso crio um @ModelAttribute e não preciso duplicar código que preenche os OPTION no método: ModelAndView novo()
	@ModelAttribute("todosStatusTitulo")//Esse é o nome que estou usando dentro do for each no HTML
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	//
	@RequestMapping("/titulos/Pesquisa-Titulos")
	public String pesquisar() {
		return "PesquisaTitulos";
	}
	
	
	/*	public String salvar(Titulo titulo) {
		//TODO: Salvar no banco de dados
		
		System.out.println(">>> " + titulo.getDescricao());
		System.out.println(">>> " + titulo.getStatus());
		System.out.println(">>> " + titulo.getDataVencimento());
		titulos.save(titulo);
		return "CadastroTitulo";//Vou retornar para a própria pag web apenas para teste de retorno dos dados.
		
		
	}*/
}
