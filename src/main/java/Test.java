import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.config.Config;
import com.model.Student;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.utility.DocumentObject;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MongoCollection<Document> dbCollection = Config.getDBCollection();
		char c;
		int choice;
		do {

			System.out.println("press 1 for save");
			System.out.println("press 2 for get all");
			System.out.println("peress 3 for single record by name");
			System.out.println("Enter 4 for delete record");
			System.out.println("Enter 5 for update record");

			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter name ");
				String name = scanner.next();
				System.out.println("Enter Age");
				int age = scanner.nextInt();

				Student student = new Student(name, age);

				Document document = DocumentObject.getDocument(student);
				dbCollection.insertOne(document);
				System.out.println("Inserted");
				break;
			}

			case 2: {

				FindIterable<Document> documents = dbCollection.find();
				MongoCursor<Document> iterator = documents.iterator();
				while (iterator.hasNext()) {

					System.out.println(iterator.next());
				}
				break;
			}

			case 3: {
				System.out.println("Enter name");
				String name = scanner.next();
				Bson filter = Filters.eq("name", name);
				FindIterable<Document> documents = dbCollection.find().filter(filter);
				MongoCursor<Document> iterator = documents.iterator();
				while (iterator.hasNext()) {

					System.out.println(iterator.next());
				}
				break;
			}

			case 4: {
				System.out.println("Enter name");
				String name = scanner.next();
				Bson filter = Filters.eq("name", name);
				dbCollection.findOneAndDelete(filter);
				System.out.println("Deleted ");
				break;
			}

			case 5: {
				System.out.println("Enter name");
				String name = scanner.next();
				System.out.println("Enter age");
				int age = scanner.nextInt();
				Document query = new Document().append("name", name);

				Bson updates = Updates.combine(Updates.set("age", age));

				dbCollection.updateOne(query, updates);
				System.out.println("updated");

				break;
			}
			default:
				break;
			}

			System.out.println("Do you want to continue y/n");
			c = scanner.next().charAt(0);

		} while (c == 'y' || c == 'Y');
		System.out.println("terminated !!");

	}

}
