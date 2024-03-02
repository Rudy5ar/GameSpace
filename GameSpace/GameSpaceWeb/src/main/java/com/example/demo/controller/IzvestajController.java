package com.example.demo.controller;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.StudioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/izvestaj")
public class IzvestajController {

	@Autowired
	StudioService ss;
	
	@GetMapping("/getStudios")
	public String getStudios(HttpServletRequest request) {
		request.getSession().setAttribute("studios", ss.allStudios());
		return "pages/izvestaj";
	}
	
	
	@PostMapping("/createReport")
	public String createReport(@RequestParam("studio") Integer idStudio, HttpServletResponse response) {
		try {
			JasperPrint jasperReport = ss.kreirajIzvestaj(idStudio);
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=studioIgrice.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperReport, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/izvestaj";
	}

}
