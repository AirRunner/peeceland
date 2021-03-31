import org.mongodb.scala.{MongoClient, MongoDatabase}

object MongoDB {
    
    def getMongoDb(): MongoDatabase = {
        val uri: String = "mongodb+srv://peaceland:<password>@devcluster.4jen4.mongodb.net/peaceland?retryWrites=true&w=majority"
        System.setProperty("org.mongodb.async.type", "netty")
        val client: MongoClient = MongoClient(uri)
        
        client.getDatabase("peaceland")
    }
}