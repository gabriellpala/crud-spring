package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Address;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Cidade;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.User;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Veiculo;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.AddressRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CidadeRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.UserRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.VeiculoRepository;

@Component
public class InitializeDatabase implements CommandLineRunner{
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private AddressRepository aRepo;
	
	@Autowired
	private CidadeRepository cRepo;
	
	@Autowired
	private VeiculoRepository vRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Cidade p = new Cidade();
		p.setName("Poço Fundo");
		p.setState("MG");
		
		Cidade m = new Cidade();
		m.setName("Machado");
		m.setState("MG");
		
		cRepo.save(p);
		cRepo.save(m);
		
		Veiculo voyage = new Veiculo();
		voyage.setCor("Preto");
		voyage.setModelo("Voyage");
		voyage.setPlaca("GAB-1234");
		
		Veiculo jetta = new Veiculo();
		jetta.setCor("Branco");
		jetta.setModelo("Jetta");
		jetta.setPlaca("GTI-0155");
		
		
		vRepo.save(voyage);
		vRepo.save(jetta);
		
		Address a = new Address();
		a.setPlace("Joaquim Teofilo");
		a.setNumber(217);
		a.setZipCode("37750-000");
		a.setCidade(m);
		
		Address b = new Address();
		b.setPlace("Av José Evilásio Assi");
		b.setNumber(482);
		b.setZipCode("37757-000");
		b.setCidade(p);
		
		aRepo.save(a);
		aRepo.save(b);
		aRepo.flush();
		
		User g = new User();
		g.setName("Gabriel");
		g.setEmail("gabriel@gmail.com");
		g.setGender("M");
		g.setAddress(b);
		
		
		User k = new User();
		k.setName("Karina");
		k.setEmail("karina@gmail.com");
		k.setGender("F");
		k.setAddress(a);
		
		
		uRepo.save(g);
		uRepo.save(k);
		
		
	}

}

