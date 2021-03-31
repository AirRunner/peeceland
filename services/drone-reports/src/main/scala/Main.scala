import ReportGenerator.sendReport

object Main {
    def simulateDrone(nb: Int): Unit = nb match {
        case nb if (nb == 0) => None
        case nb if (nb < 0) => simulateDrone(nb)
        case _ => {
            sendReport()
            Thread.sleep(60000) // 1mn
            simulateDrone(nb - 1)
        }
    }
    
    def main(args: Array[String]): Unit = {
        simulateDrone(-1)
    }
}
