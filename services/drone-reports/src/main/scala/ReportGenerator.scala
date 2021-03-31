import scala.io.Source
import scala.collection.Seq
import java.time.LocalDateTime
import org.scalacheck.Gen
import Producer.produceReport

object ReportGenerator {
    
    case class Citizen (
        name: String,
        peaceScore: Int
    )
    case class Report (
        dateTime: LocalDateTime = LocalDateTime.now,
        watcherId: Int = 0,
        latitude: String = "0",
        longitude: String = "0",
        citizens: Seq[Citizen] = Nil,
        words: Seq[String] = Nil
    )

    def loadData(path: String): List[String] = {
        Source.fromFile(path).getLines.toList
    }

    def generateReport(): Gen[Report] = {
        val LAT = ("29.", 4737, 6059)
        val LONG = ("-95.", 1048, 3862)
        val NAMES = loadData("data/names.txt")
        val WORDS = loadData("data/words.txt")

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
                    // Generate a peascore (0 to 10) by frequencies
                    Gen.frequency((1, 0), (1, 1), (2, 2), (2, 3), (3, 4),
                        (4, 5), (4, 6), (5, 7), (5, 8), (4, 9), (3, 10))
                        .sample.getOrElse(2)
                    )
                )
            )
            
            nb_wds <- Gen.choose(nb_cit, nb_cit * 5)
            
            wds <- Gen.pick(nb_wds, WORDS)
            
        } yield Report(LocalDateTime.now, id, lat, long, cit, wds)
    }

    def sendReport() = {
        val report: Report = generateReport().sample.getOrElse(Report())
        produceReport(report)
    }
}
