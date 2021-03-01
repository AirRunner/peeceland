import scala.io.Source
// import java.io.PrintWriter
// import java.time.LocalDateTime
import org.scalacheck.Gen
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
// import org.apache.spark.SparkContext
// import org.apache.spark.SparkConf
// import org.apache.spark.rdd.RDD

object ReportGenerator {
    
    case class Citizen (
        name: String,
        peaceScore: Int
    )
    case class Report (
        // time: LocalDateTime,
        watcherId: Int,
        latitude: String,
        longitude: String,
        citizens: Seq[Citizen],
        words: Seq[String]
    )

    def loadData(path: String): List[String] = {
        Source.fromFile(path).getLines.toList
    }

    def generateReport(): Gen[Report] = {
        val LAT = ("29.", 4737, 6059)
        val LONG = ("-95.", 1048, 3862)
        val NAMES = loadData("resources/names.txt")
        val WORDS = loadData("resources/words.txt")

        for {
            id <- Gen.choose(1, 100)
            
            lat <- Gen.choose(LAT._2, LAT._3)
                    .map(lat => LAT._1 + lat.toString)
            
            long <- Gen.choose(LONG._2, LONG._3)
                    .map(long => LONG._1 + long.toString)
            
            nb_cit <- Gen.choose(0, 20)

            cit <- Gen.listOfN(
                nb_cit,
                Gen.oneOf(NAMES)
                .map(name => new Citizen(
                    name,
                    Gen.choose(0, 10).sample.get)
                )
            )

            nb_wds <- Gen.choose(nb_cit, nb_cit * 5)

            wds <- Gen.pick(nb_wds, WORDS)
            
        } yield Report(id, lat, long, cit, wds)
    }

    def jsonReport(): String = {
        val report = generateReport().sample.get
        implicit val formats = DefaultFormats
        write(report)
    }
}
