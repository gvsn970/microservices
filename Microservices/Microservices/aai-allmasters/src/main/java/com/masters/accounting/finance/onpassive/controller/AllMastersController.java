package com.masters.accounting.finance.onpassive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masters.accounting.finance.onpassive.entity.AddColumnResoponse;
import com.masters.accounting.finance.onpassive.exception.ResourceNotFoundException;
import com.masters.accounting.finance.onpassive.service.AllMastersService;

@RestController
@RequestMapping("/allMasters")
public class AllMastersController {

	@Autowired
	AllMastersService allMastersService;

	@PostMapping("/addcolumn")
	public ResponseEntity<AddColumnResoponse> addColumn(@RequestParam String columnName)
			throws ResourceNotFoundException {
		AddColumnResoponse addColumnMasters = allMastersService.createColumnMasters(columnName);
		return new ResponseEntity<AddColumnResoponse>(addColumnMasters, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/updatecolumn")
	public ResponseEntity<AddColumnResoponse> updateColumn(@RequestParam String oldColumName,
			@RequestParam String newColumName) throws ResourceNotFoundException {
		AddColumnResoponse updateColumnMasters = allMastersService.updateColumnMasters(oldColumName, newColumName);
		return new ResponseEntity<AddColumnResoponse>(updateColumnMasters, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteColumn")
	public ResponseEntity<AddColumnResoponse> deleteColumn(@RequestParam String columnName)
			throws ResourceNotFoundException {
		AddColumnResoponse updateColumnMasters = allMastersService.deleteColumnMasters(columnName);
		return new ResponseEntity<AddColumnResoponse>(updateColumnMasters, new HttpHeaders(), HttpStatus.OK);
	}
	@GetMapping("/welcome")
	public String get() {
		return "welcome";
	}

}
