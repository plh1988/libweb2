package com.pradeep.libraray.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.libraray.model.Card;
import com.pradeep.libraray.model.Document;
import com.pradeep.libraray.service.DocumentException;
import com.pradeep.libraray.service.DocumentService;

@RestController
@RequestMapping(value = "/library")
public class DocumentController {
	
	@Autowired
	DocumentService documentService;
	
//	@RequestMapping(value = "/document",method = RequestMethod.POST)
//	public Document save(@RequestBody Document document) {
//		return documentService.save(document);
//	}
	
	@RequestMapping(value = "/document",method = RequestMethod.GET)
	public List<Document> fetch(){
		return documentService.fetch();
	}
	
	@RequestMapping(value = "/document/{id}", method = RequestMethod.GET)
	public ResponseEntity<Document> fetch(@PathVariable Integer id){
		if(id<=0) {
			return ResponseEntity.badRequest().build();
		}else{
			Document document = documentService.fetch(id);
			if(document == null) {
				return ResponseEntity.notFound().build();
			}
			else {
				return ResponseEntity.ok(document);
			}
		}
	}
	
	@RequestMapping(value = "/document",method = RequestMethod.POST)
	public ResponseEntity <Document> save(@RequestBody Document document) {
		try {
			Document documentn = documentService.save(document);
			return ResponseEntity.ok(documentn);
		} catch (DocumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/document/{id}/card", method = RequestMethod.POST)
	public Document saveCard(@PathVariable Integer id,@RequestBody List<Card> card) {
		return documentService.saveCard(id,card);
	}
	
	
}
