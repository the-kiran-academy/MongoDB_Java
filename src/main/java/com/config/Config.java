package com.config;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Config {

	public static MongoCollection<Document> getDBCollection() {
		String uri = "mongodb://localhost:27017";
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = mongoDatabase.getCollection("student2");
        return  collection;
	}

}
