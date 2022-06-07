package me.da0hn.machine

sealed class OrderRequest {

  data class DrinkOrderRequest(private val drink: OrderType, private val sugar: Sugar) : OrderRequest() {

    fun type() = drink.type()

    fun sugarQuantity() = sugar.value()

    fun stickQuantity() = sugar.stick()
  }

  data class MessageOrderRequest(private val type: OrderType, private val data: String) : OrderRequest() {
    fun type() = type.type()

    fun message() = data
  }
}
