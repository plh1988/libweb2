package com.pradeep.libraray.service;

import java.util.List;

import com.pradeep.libraray.model.Card;
import com.pradeep.libraray.model.Document;

public interface DocumentService {
	Document save(Document documnet) throws DocumentException;
	Document fetch(Integer id);
	List<Document> fetch();
	Document saveCard(Integer id, List<Card> card);
}
