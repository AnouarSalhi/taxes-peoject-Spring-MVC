package com.taxes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxes.dao.EntrepriseRepository;
import com.taxes.dao.TaxeRepository;
import com.taxes.entities.Entreprise;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

import java.util.List;

@Controller
public class TaxeController {
    
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TaxeRepository taxeRepository;
	 
	@GetMapping("/")
	    public String index() {
	 
	    	return "redirect:index";
	    }
	@GetMapping("/login")
	public String viewLoginPage() {
		// custom logic before showing login page...
		
		return "login";
	}
	
    @GetMapping("/index")
    public String index(Model model,
    	@RequestParam(name="page",defaultValue="0")	int page,
    	@RequestParam(name="mc",defaultValue="")	String mc) {

        Page<Entreprise> entreprises=entrepriseRepository.findByNomContains(mc,PageRequest.of(page,5));
    	model.addAttribute("entreprises",entreprises.getContent());
    	model.addAttribute("page",new int [entreprises.getTotalPages()]);
    	model.addAttribute("mc",mc);
    	model.addAttribute("currentPage",page);
    	return "index";
    }
    @GetMapping("/delete")
    public String delete(Long id,String mc,int page) {
    	System.out.println(id);
    	entrepriseRepository.deleteById(id);
    	return "redirect:/index?page="+page+"&mc="+mc;
    }
    
   // @GetMapping("/add")
    @RequestMapping(value="/add")
    public String add(Model model) {
    	model.addAttribute("entreprise",new Entreprise());
    	return "form";
    }
    
    @RequestMapping(value="/edit")
    public String add(Model model, Long id,
        	@RequestParam(name="page",defaultValue="0")	int page,
        	@RequestParam(name="mc",defaultValue="")	String mc) {
    	model.addAttribute("mc",mc);
    	model.addAttribute("currentPage",page);
    	Entreprise e =entrepriseRepository.findById(id).get();
    	model.addAttribute("entreprise",e);
    	
    	return "edit";
    }
    
    @RequestMapping(value="/save")
    public String save(Model model,Entreprise e,BindingResult bindingResult,
    		@RequestParam(name="page",defaultValue="0")	int page,
        	@RequestParam(name="mc",defaultValue="")	String mc ) {
    	
    	entrepriseRepository.save(e);
    	return "redirect:/index?page="+page+"&mc="+mc;
    }
    
    @RequestMapping(value="/taxes")
    public String add(Model model,Long id) {
    	List entreprises=entrepriseRepository.findAll();
    	Entreprise e=new Entreprise();
    	e.setId(id);
    	model.addAttribute("taxes",taxeRepository.findByEntreprise(e));
    	model.addAttribute("entreprises",entreprises);
    	return "taxes";
    }
}
