import org.mongodb.scala.{MongoClient, MongoDatabase}

object Mongo {
    def getMongoDb(): MongoDatabase = {
        val username, password, host, options, database // load from json
        
        val uri: String = "mongodb+srv://" + username + ":" + password + "@" + host + "/?" + options
        System.setProperty("org.mongodb.async.type", "netty")
        val client: MongoClient = MongoClient(uri)
        
        client.getDatabase(database)
    }
}
