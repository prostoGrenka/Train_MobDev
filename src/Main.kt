import kotlin.random.Random

data class Train(val direction: String, val wagons: List<Wagon>)

data class Wagon(val capacity: Int, val passengers: Int)

val cities = listOf(
    "Барнаул", "Бийск", "Новосибирск", "Омск", "Томск",
    "Красноярск", "Иркутск", "Новокузнецк", "Кемерово",
    "Тюмень", "Екатеринбург", "Челябинск", "Оренбург", "Сургут", "Уфа"
)

fun main() {
    var exitRequested = false

    while (!exitRequested) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("Введите 'EXIT', чтобы завершить программу.")

        when (readLine()?.toUpperCase()) {
            "1" -> createDirection()
            "2" -> sellTickets()
            "3" -> createTrain()
            "4" -> sendTrain()
            "EXIT" -> exitRequested = true
            else -> println("Некорректный ввод. Повторите попытку.")
        }
    }
}

fun createDirection() {
    val startCity = cities.random()
    var endCity = startCity
    while (endCity == startCity) {
        endCity = cities.random()
    }
    println("Создано направление: $startCity - $endCity")
}

fun sellTickets() {
    val numPassengers = Random.nextInt(5, 202)
    println("Продано билетов: $numPassengers")
}

fun createTrain() {
    val passengers = Random.nextInt(5, 202)
    val wagons = mutableListOf<Wagon>()
    var remainingPassengers = passengers

    while (remainingPassengers > 0) {
        val capacity = Random.nextInt(5, 26)
        val passengersInWagon = minOf(remainingPassengers, capacity)
        wagons.add(Wagon(capacity, passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    val train = Train("${cities.random()} - ${cities.random()}", wagons)
    println("Поезд создан для направления: ${train.direction}")
}

fun sendTrain() {
    val direction = "${cities.random()} - ${cities.random()}"
    val wagons = mutableListOf<Wagon>()

    for (i in 1..Random.nextInt(1, 6)) {
        wagons.add(Wagon(Random.nextInt(5, 26), Random.nextInt(0, 26)))
    }

    val train = Train(direction, wagons)

    println("Поезд отправлен: ${train.direction}, состоящий из ${train.wagons.size} вагонов.")
    for ((index, wagon) in train.wagons.withIndex()) {
        println("Вагон $index: вместимость ${wagon.capacity}, пассажиров ${wagon.passengers}")
    }
}
