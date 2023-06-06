package com.utility;

import java.text.SimpleDateFormat;

import org.bson.Document;

import com.model.Student;

public class DocumentObject {

	public static Document getDocument(Student std) {
		Document document = new Document();
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		document.append("_id", id);

		document.append("name", std.getName());
		document.append("age", std.getAge());

		return document;
	}

}
