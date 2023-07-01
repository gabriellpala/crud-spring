package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.CidadeRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cidade;

@Controller
public class CidadeController {
	
	//recuperando repositorio cidade(interface)
	
    @Autowired
    private CidadeRepository cidadeRepo;
    
    // inicializando a lista de cidades
    
    @GetMapping("/cidades")
    public String cidades(Model model) {
        List<Cidade> cidades = cidadeRepo.findAll();
        
        model.addAttribute("cidades", cidades);
        
        return "listar_cidade";
    }
    
    // listando formulario cidades (add)
    
    @GetMapping("/cidades/form")
    public String cidadeForm(@ModelAttribute("cidade")Cidade cidade) {
        
        return "cidade_form";
    }
    
    // botao salvar add cidades
    
    @PostMapping("cidades/new")
    public String userNew(@Valid @ModelAttribute("cidade")Cidade cidade, BindingResult br) {
        
        if(br.hasErrors()) {
            return "listar_cidade";
        }
        
     // salvando repositorio cidades
        
        cidadeRepo.save(cidade);

        
        return "redirect:/cidades";
    }
    
    
    
    // botão alterar cidade
    
    @GetMapping("/cidades/{id}")
    public String cidadeUpdate(@PathVariable("id") Integer id, Model model) {
        
    	// se estiver vazio = invalido
    	
        Optional<Cidade> cidadeOpt = cidadeRepo.findById(id);
        if(cidadeOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário inválido!");
        }
        Cidade cidade = cidadeOpt.get();
        model.addAttribute("cidade", cidade);
        
        return "cidade_form";
    }
    @GetMapping("/cidades/delete/{id}")
    public String cidadeDelete(@PathVariable("id") Integer id) {
        
    	// deleta usuario
    	
        Optional<Cidade> cidadeOpt = cidadeRepo.findById(id);
        
        if(cidadeOpt.isEmpty())
            throw new IllegalArgumentException("Usuario invalido!");
        
        Cidade cidade = cidadeOpt.get();
        
        
        cidadeRepo.delete(cidade);
        
        return"redirect:/cidades";
    }
}