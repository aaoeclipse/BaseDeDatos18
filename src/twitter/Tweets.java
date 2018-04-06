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
    private ConfigurationBuilder confBuild = new ConfigurationBuilder();

    public ArrayList<String[]> getTweets(String user) throws IOException {
        // Creating a Mongo client
        ArrayList<String[]> messages = new ArrayList<String[]>();
        //tweet(user);
        //loadingTweets(user);
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



    public void tweet(String user) throws IOException {
        URL location = Tweets.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.toString());
        String urlP = "cd /home/luisa/UVG%205to/DATA%20BASES/Proyecto1/bd/BaseDeDatos18/src/twitter";

        urlP = urlP.replace('%',(char)92);
        urlP = urlP.replace("20"," ");

        //Process p = Runtime.getRuntime().exec(urlP);
        Process p1 = Runtime.getRuntime().exec("python3 "+"twitter.py "+user);
        BufferedReader in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
        String ret = in.readLine();
        System.out.println("value is : "+ret);
/**
        String command = "python3 ./twitter/twitter.py BasesDatos18";
        Process p = Runtime.getRuntime().exec(command);
        System.out.println(p.toString());**/
    }
    public void loadingTweets(String userName){
        confBuild.setDebugEnabled(true);
        confBuild.setOAuthAccessToken("976843852924547074-8JpaQxzIELaBWDAXq3eHeY3eYe17HRf");
        confBuild.setOAuthAccessTokenSecret("GXmvt7DfjnTE17q2XkN73fWPMKVGICNS2M5BIP296IhMI");
        confBuild.setOAuthConsumerKey("yAI42ukafgKvR2e0ZKfc5zNXn");
        confBuild.setOAuthConsumerSecret("rvlmmZLNU44wkcTkXbbDO7csUa3ouJz1wi8SaKcYt3dzCSsKpD");

        TwitterFactory facto = new TwitterFactory(confBuild.build());
        Twitter tw = facto.getInstance();


        if (confBuild != null) {
            try {
                //Query query = new Query(keyword);
                //query.setCount(20);
                //QueryResult result;
                //result = twitter.search(query);
                User user = tw.verifyCredentials();
                List<Status> tweets = tw.getUserTimeline("BasesDatos18");
                System.out.println("Getting Tweets...");
                //List<Status> tweets = result.getTweets();

                for (Status tweet : tweets) {
                    // BasicDBObject basicObj = new BasicDBObject();
                    System.out.println("user_name" + tweet.getUser().getScreenName());
                    //basicObj.put("retweet_count", tweet.getRetweetCount());
                    //basicObj.put("tweet_followers_count", tweet.getUser().getFollowersCount());
                    //basicObj.put("source",tweet.getSource());
                    //basicObj.put("coordinates",tweet.getGeoLocation());


                    //UserMentionEntity[] mentioned = tweet.getUserMentionEntities();
                    //basicObj.put("tweet_mentioned_count", mentioned.length);
                    //basicObj.put("tweet_ID", tweet.getId());
                    System.out.println("tweet_text " + tweet.getText());


                }

                // Printing fetched records from DB

            } catch (TwitterException te) {
                System.out.println("te.getErrorCode() ");
                System.out.println("te.getExceptionCode() " + te.getExceptionCode());
                System.out.println("te.getStatusCode() " + te.getStatusCode());
                if (te.getStatusCode() == 401) {
                    System.out.println("Twitter Error : \nAuthentication credentials (https://dev.twitter.com/pages/auth) were missing or incorrect.\nEnsure that you have set valid consumer key/secret, access token/secret, and the system clock is in sync.");
                } else {
                    System.out.println("Twitter Error : " + te.getMessage());
                }


            }
        } else {
            System.out.println("MongoDB is not Connected! Please check mongoDB intance running..");
        }
    }

}
