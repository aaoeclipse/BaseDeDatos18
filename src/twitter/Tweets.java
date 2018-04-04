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
/**
 * Created by luisa on 22/03/18.
 */
public class Tweets {

    public void getTweets() throws IOException {
        // Creating a Mongo client



        MongoClient mongoClient = new MongoClient( "localhost" );
        MongoDatabase database = mongoClient.getDatabase("Twitter");

        MongoCollection<Document> collection = database.getCollection("tweets");
        //MongoCursor<Document> cursor = (MongoCursor<Document>) collection.find(eq("user", "J_tsar"));
        System.out.println(collection.count());
        for (Document cur : collection.find(eq("user", "hectorh30"))) {
           // System.out.println("holi");
            System.out.println(cur.get("mensaje"));
        }


    }
    public void tweet(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("1fYGcrqRwS1oz1v37MhVwLUEj");
        cb.setOAuthConsumerSecret("eFXIkf22mDhQHhIjVdCMo3Y693wgTq0wtcJv8cnrNzo4OtoMxN");
        cb.setOAuthAccessToken("976843852924547074-8JpaQxzIELaBWDAXq3eHeY3eYe17HRf");
        cb.setOAuthAccessTokenSecret("GXmvt7DfjnTE17q2XkN73fWPMKVGICNS2M5BIP296IhMI");

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();

        int pageno = 1;
        String user = "J_tsar";
        List statuses = new ArrayList();

        while (true) {

            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);
                System.out.println(twitter.getUserTimeline(user, page));
                statuses.addAll(twitter.getUserTimeline(user, page));
                if (statuses.size() == size)
                    break;
            }
            catch(TwitterException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total: "+statuses.size());
    }
}
