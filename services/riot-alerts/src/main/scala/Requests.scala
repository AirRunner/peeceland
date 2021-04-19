import scalaj.http.{Http, HttpOptions}

object Requests {
    def post(alert: String) = {
        Http("http://peaceland.tk:80/alerts").postData(alert)
        .header("Content-Type", "application/json")
        .option(HttpOptions.readTimeout(1000))
        .asString
    }
}
