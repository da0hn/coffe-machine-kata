package me.da0hn.machine

private const val ERROR_MESSAGE = "Error while trying translate message"
private const val INVALID_VALUE = ""

class DrinkOrderEmitter {

  fun emit(drinkMakerProtocol: Protocol): DrinkMakerMessage {
    val message = emit(drinkMakerProtocol.data)
    return DrinkMakerMessage(message)
  }

  private fun emit(data: String): String {
    val values = data.split(":")

    return when (values[0]) {
      "M" -> translateMessage(values[1])
      "T", "C", "H" -> translateDrinkOrder(values[0], values[1], values[2])
      else -> ERROR_MESSAGE
    }
  }

  private fun translateMessage(message: String): String {
    if (message.isEmpty()) return ERROR_MESSAGE
    return "Sending: $message"
  }

  private fun translateDrinkOrder(drink: String, sugar: String, stick: String): String {
    if (sugar.isNotEmpty() and stick.isEmpty()) {
      return "It's not possible make drink with some sugar and no stick"
    }

    val sugarPartialMessage = when (sugar) {
      "1" -> "1 sugar"
      "2" -> "2 sugar"
      "" -> "no sugar"
      else -> INVALID_VALUE
    }

    val stickPartialMessage = when (stick) {
      "0" -> "and a stick"
      "" -> "and no stick"
      else -> INVALID_VALUE
    }

    if (sugarPartialMessage.isEmpty() or stickPartialMessage.isEmpty())
      return ERROR_MESSAGE

    return when (drink) {
      "T" -> "Drink maker makes 1 tea with $sugarPartialMessage $stickPartialMessage"
      "C" -> "Drink maker makes 1 coffee with $sugarPartialMessage $stickPartialMessage"
      "H" -> "Drink maker makes 1 chocolate with $sugarPartialMessage $stickPartialMessage"
      else -> ERROR_MESSAGE
    }
  }

}
