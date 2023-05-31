package com.consume.apinew.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.consume.apinew.models.PatronesModels;

@Controller
@RequestMapping("/Patrones")
public class PatronesController {
	@GetMapping("{sw}")
	public String viewfruit(@PathVariable("sw") String sw, Model model) {
		String titulo = "P{agina API con SprinBot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		PatronesModels startPatronesResult = startPatronesResultDTO (sw);
		model.addAttribute("startPatrones", startPatronesResult);
		return "verPatrones";
	}
	
	public PatronesModels startPatronesResultDTO(String sw) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PatronesModels> resp =
				restTemplate.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/patrones/%s"+".json", sw), PatronesModels.class);
		return resp.getBody();
	}
}
