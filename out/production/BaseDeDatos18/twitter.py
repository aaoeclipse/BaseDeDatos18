import tweepy #https://github.com/tweepy/tweepy
import csv
import pymongo
import sys
from pymongo import MongoClient
client = MongoClient()
client = MongoClient('localhost', 27017)
#client.drop_database('Twitter')
db = client.Twitter
db = client['Twitter']
tweets = db.tweets
tweets = db['tweets']
#Twitter API credentials
consumer_key = "R6O6hzbFeBGPU0caXpaQ2WQeS"
consumer_secret = "GkmD3v9uJ66iKa9W2BBUtFV9qZGqUUZ588KFXpCIzmkMZep1PU"
access_key = "976843852924547074-8JpaQxzIELaBWDAXq3eHeY3eYe17HRf"
access_secret = "GXmvt7DfjnTE17q2XkN73fWPMKVGICNS2M5BIP296IhMI"


def get_all_tweets(screen_name):
    
    #Twitter only allows access to a users most recent 3240 tweets with this method

    #authorize twitter, initialize tweepy
    auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_key, access_secret)
    api = tweepy.API(auth)
    
    #initialize a list to hold all the tweepy Tweets
    alltweets = []  

    
    #make initial request for most recent tweets (200 is the maximum allowed count)
    new_tweets = api.user_timeline(screen_name = screen_name,count=200)
    
    #save most recent tweets
    alltweets.extend(new_tweets)
    
    #save the id of the oldest tweet less one
    oldest = alltweets[-1].id - 1
    
    #keep grabbing tweets until there are no tweets left to grab
    while len(new_tweets) > 0:
        print ("getting tweets before %s" % (oldest))
        
        #all subsiquent requests use the max_id param to prevent duplicates
        new_tweets = api.user_timeline(screen_name = screen_name,count=200,max_id=oldest)
        
        #save most recent tweets
        alltweets.extend(new_tweets)
        
        #update the id of the oldest tweet less one
        oldest = alltweets[-1].id - 1
        
        print ("...%s tweets downloaded so far" % (len(alltweets)))
    
    #transform the tweepy tweets into a 2D array that will populate the csv 
    outtweets = [[tweet.id_str, tweet.created_at, tweet.text.encode("utf-8")] for tweet in alltweets]
    return outtweets
#write the csv 

def guardar(TheTweets,user):
    
    for tweet in TheTweets:
        if (db.tweets.find({"user":user, "mensaje": (tweet[2]).decode("utf-8")}).count())==0:
            db.tweets.insert({"user":user,
                    "date": str(tweet[1]),
                    "mensaje": (tweet[2]).decode("utf-8")
            })
t = get_all_tweets(str(sys.argv[1]))
if len(t)>0:
    guardar(t,str(sys.argv[1]))


#for i in db.tweets.find({"user":"J_tsar","mensaje":'Im doing laundry?! at this time? #college'}):
#    print(i)
print(db.tweets.count())
print(">>"+str(sys.argv[1]))
