	package com.oop2.tim6.NakitWebTim6.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Nakit;
import com.oop2.tim6.NakitWebTim6.model.Tip;
import com.oop2.tim6.NakitWebTim6.model.Uloga;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepoTim6;
import com.oop2.tim6.NakitWebTim6.repository.INakitCrudRepoTim6;
import com.oop2.tim6.NakitWebTim6.repository.IUlogaRepoTim6;
import com.oop2.tim6.NakitWebTim6.service.ITipServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.UserSecurityServiceTim6;


@Controller
public class RootControllerTim6 {
	private static final String USER_IMAGES_PATH ="databaseImages/userImages/%d.png";
	private static final String NAKIT_IMAGES_PATH ="databaseImages/nakitImages/%d.png";
	
	@Autowired
	ITipServiceTim6 tipService;
	
    @Autowired
    IKorisnikJpaRepoTim6 korisnikJpaRepo;

    @Autowired
    UserSecurityServiceTim6 userSecurityService;

    @Autowired
    INakitCrudRepoTim6 nakitJpa;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IUlogaRepoTim6 ulogaRepo;

    @Autowired
    private Environment env;



    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }
    
    @GetMapping(value = "/register")
    public String getRegister() {
        return "register";
    }
    
    @PostMapping(value = "/register")
    public String saveKorisnik(@Valid @ModelAttribute("korisnik") Korisnik korisnik, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) throws IOException {
    	if(file != null) {
        	korisnik.setSlika(file.getBytes());
    	}

    	if(bindingResult.hasErrors())
    	    return "register";
    	
    	Uloga uloga = ulogaRepo.findByIdUloge(1);
    	korisnik.setUloga(uloga);

    	Korisnik noviKorisnik = userSecurityService.saveNewKorisnik(korisnik);

    	if(noviKorisnik == null){
    	    model.addAttribute("usernameExists",true);
    	    return "register";
        }
        return "login";
    }
    
    @RequestMapping(value = "/testSearch", method = RequestMethod.GET)
    public String testSearch(Model model) {
    	List<Tip> tipovi = tipService.getAllTipoviNakita();
    	Tip svi = new Tip();
    	svi.setIdTipa(0);
    	svi.setNaziv("Svi");
    	tipovi.add(svi);
    	model.addAttribute("tipovi", tipovi);
    	return "korisnik/search";
    }

    @GetMapping(value = "/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("dashboardAdmin")
    public String getdashboardAdmin(){
        return "dashboardAdmin";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        //Ovo cenmo zapakovati u servis, ovo je samo da vidim da li lepo radi
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @GetMapping(value="/fillData")
    public String fillData() throws SQLException, IOException {
    	Connection con = DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
    	byte[] insertEncoded = Files.readAllBytes(Paths.get("InsertScripta.sql"));
      	executeSqlScript(con, insertEncoded);
    	java.util.List<Korisnik> korisnici = korisnikJpaRepo.findAll();
    	for(Korisnik korisnik: korisnici) {
    		korisnik.setSlika(Files.readAllBytes(Paths.get(String.format(USER_IMAGES_PATH, korisnik.getIdKorisnika()))));
    	}
    	
    	Iterable<Nakit> nakiti = nakitJpa.findAll();
    	for(Nakit nakit: nakiti) {
    		nakit.setSlikaNakita(Files.readAllBytes(Paths.get(String.format(NAKIT_IMAGES_PATH, nakit.getIdNakita()))));
    	}

    	nakitJpa.saveAll(nakiti);
    	korisnikJpaRepo.saveAll(korisnici);
    	return "login";
    }

    @ModelAttribute("korisnik")
    public Korisnik getKorisnik(){
        return new Korisnik();
    }

    private void executeSqlScript(Connection connection, byte[] encoded)throws SQLException, IOException{
        try {
            connection.setAutoCommit(false);
            ScriptUtils.executeSqlScript(connection, new ByteArrayResource(encoded));
            connection.commit();
       } catch (SQLException e) {
           connection.rollback();
       }finally{
           connection.close();
       }
    }
}