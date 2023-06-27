package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Veiculo;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.UserRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.VeiculoRepository;

@Controller
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepo;
	
	@GetMapping("/veiculo")
	public String users(Model model) {
		List<Veiculo> veiculo = veiculoRepo.findAll();
		
		model.addAttribute("veiculo", veiculo);
		
		return "listar_veiculo";
	}
	
	@GetMapping("/veiculo/form")
	public String userForm(@ModelAttribute("veiculo") 
	Veiculo veiculo) {
		return "veiculo_form";
	}
	
	@PostMapping("/veiculo/new")
	public String veiculoNew(@ModelAttribute("veiculo") 
	Veiculo veiculo) {
		
		veiculoRepo.save(veiculo);
		
		return "redirect:/veiculo";
	}
	
	@GetMapping("/veiculo/{id}")
	public String veiculoUpdate(@PathVariable("id") 
	                         Integer id, 
	                         Model model) {
		
		Optional<Veiculo> veiculoOpt = veiculoRepo.findById(id);
		
		if (veiculoOpt.isEmpty())
			throw new IllegalArgumentException("Veiculo inválido!");
		
		Veiculo veiculo = veiculoOpt.get();
		model.addAttribute("veiculo", veiculo);
		
		return "veiculo_form";
	}
	
	@GetMapping("/veiculo/delete/{id}")
	public String veiculoDelete(@PathVariable("id") 
							 Integer id) {
		
		Optional<Veiculo> veiculoOpt = veiculoRepo.findById(id);
		
		if (veiculoOpt.isEmpty())
			throw new IllegalArgumentException("veiculo inválido!");
		
		Veiculo veiculo = veiculoOpt.get();
		
		veiculoRepo.delete(veiculo);
		
		return "redirect:/veiculo";
	}
	
}