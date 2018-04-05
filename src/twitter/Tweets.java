package twitter;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Twitter;
import twitter4j.*;
import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;;
import java.io.*;
import java.net.*;


/**
 * Created by luisa on 22/03/18.
 */
public class Tweets {

    public ArrayList<String[]> getTweets(String user) throws IOException {
        // Creating a Mongo client
        ArrayList<String[]> messages = new ArrayList<String[]>();

        //tweet();
        MongoClient mongoClient = new MongoClient( "localhost" );
        MongoDatabase database = mongoClient.getDatabase("Twitter");

        MongoCollection<Document> collection = database.getCollection("tweets");
        //MongoCursor<Document> cursor = (MongoCursor<Document>) collection.find(eq("user", "J_tsar"));
        System.out.println(collection.count());
        for (Document cur : collection.find(eq("user", user))) {
            // System.out.println("holi");
            System.out.println(String.valueOf(cur.get("date"))+" - "+cur.get("mensaje"));
            String[] messa = new String[2];
            messa[0] = String.valueOf(cur.get("date"));
            messa[1] = String.valueOf(cur.get("mensaje"));
            messages.add(messa);

        }

        return messages;
    }



    public void tweet() throws IOException {
        URL location = Tweets.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.toString());
        String urlP = location.getFile().toString();

        urlP = urlP.replace('%',(char)92);
        urlP = urlP.replace("20"," ");
        String[] path= new String[2];
        path[0]="cd";
        path[1] = urlP+"../../../src/twitter";
        System.out.println(path[1]);
        Process p = Runtime.getRuntime().exec(path);
        Process p1 = Runtime.getRuntime().exec("python3 "+"twitter.py "+"BasesDatos18");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String ret = in.readLine();
        System.out.println("value is : "+ret);
/**
        String command = "python3 ./twitter/twitter.py BasesDatos18";
        Process p = Runtime.getRuntime().exec(command);
        System.out.println(p.toString());**/
    }
}
