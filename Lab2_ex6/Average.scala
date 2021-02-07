object Average {
    def average(values: Iterator[Double]): Option[Double] = {
        val sum = values.foldRight((0.0, 0)) {
            (el, acc) => (el + acc._1, acc._2 + 1)
        }
        if (sum._2 == 0) { None }
        else Some(sum._1 / sum._2)
    }
    def average2(values: Iterator[Double]): Option[Double] = {
        if (values.hasNext) {
            val sum = values.map((_, 1.0))
                            .reduce((x, y) => (x._1 + y._1, x._2 + y._2))
            Some(sum._1 / sum._2)
        }
        else None
    }
}
