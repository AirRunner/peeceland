import org.mongodb.scala.{MongoClient, MongoCollection, Document}

object Mongo {
    def getMongoCollection(): MongoCollection[Document] = {
        val uri: String = "mongodb+srv://peaceland:9CmNkjVzR1e1xR0v@devcluster.4jen4.mongodb.net/?retryWrites=true&w=majority"
        System.setProperty("org.mongodb.async.type", "netty")
        
        MongoClient(uri)
        .getDatabase("peaceland")
        .getCollection("drone-reports")
    }
    
    def insertReport(report: String) = {
        getMongoCollection()
        .insertOne(Document(report))
        .head()
    }
}
