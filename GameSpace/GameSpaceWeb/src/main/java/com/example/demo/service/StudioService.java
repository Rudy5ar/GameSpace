package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.StudioRepository;

import model.Studio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class StudioService {

	@Autowired
	StudioRepository sr;
	
	public List<Studio> allStudios(){
		return sr.findAll();
	}

	public JasperPrint kreirajIzvestaj(Integer idStudio) throws JRException, IOException {
		System.out.println("1");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(sr.findById(idStudio).get().getGames());
		System.out.println(sr.findById(idStudio).get().getGames().toString());
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/studioReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imeStudia", sr.findById(idStudio).get().getName());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		return jasperPrint;
	}
	
	
	
}
