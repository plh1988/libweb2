package com.pradeep.libraray.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.libraray.model.Card;
import com.pradeep.libraray.model.Document;
import com.pradeep.libraray.model.Page;
import com.pradeep.libraray.model.Paragraph;
import com.pradeep.libraray.model.Publisher;
import com.pradeep.libraray.model.Section;
import com.pradeep.libraray.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	DocumentRepository documentRepository;

//	@Override
//	public Document save(Document document) {
//		for(Card c:document.getCards()) {
//			c.setDocument(document);
//			if(c.getPages()!= null) {
//				for(Page p:c.getPages()) {
//					p.setCard(c);
//					if(p.getSections()!= null) {
//						for(Section s:p.getSections()) {
//							s.setPage(p);
//							if(s.getParagraphs()!=null) {
//								for(Paragraph pa:s.getParagraphs()) {
//									pa.setSection(s);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return documentRepository.save(document);
//	}

	@Override
	public List<Document> fetch() {
		return documentRepository.findAll() ;
	}

	@Override
	public Document fetch(Integer id) {
		Optional<Document> document = documentRepository.findById(id);
		if(document.isPresent()) {
			return document.get();
		}else {
			return null;
		}
	}

	@Override
	public Document save(Document document) throws DocumentException {
		if(fetch(document.getId())!=null) {
			throw new DocumentException("Document id already exist");
		}else {
			return documentRepository.save(document);
		}
	}

	@Override
	public Document saveCard(Integer id, List<Card> cards) {
		if(fetch(id)==null) {
			throw new RecordException("Non existing record");
		}else {
			Document doc = fetch(id);
			for(Card c:cards) {
				c.setDocument(doc);
				if(c.getPages()!= null) {
					for(Page p:c.getPages()) {
						p.setCard(c);
						if(p.getSections()!= null) {
							for(Section s:p.getSections()) {
								s.setPage(p);
								if(s.getParagraphs()!=null) {
									for(Paragraph pa:s.getParagraphs()) {
										pa.setSection(s);
									}
								}
							}
						}
					}
				}
			}
			return doc;
		}
	}
	
	
}
