package me.da0hn.machine

import me.da0hn.machine.OrderRequest.DrinkOrderRequest
import me.da0hn.machine.OrderRequest.MessageOrderRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@Tag("unit")
@DisplayName("Test protocol order translator")
internal class ProtocolOrderTranslatorTest {

  private val translator = ProtocolOrderTranslator()

  @Test
  fun `Should return an formatted message of drink order with one sugar and stick`() {
    val message = translator.translate(DrinkOrderRequest(OrderType.CHOCOLATE, Sugar.ONE))
    assertEquals(message.data, "H:1:0")
  }

  @Test
  fun `Should return an formatted message of drink order with two sugar and stick`() {
    val message = translator.translate(DrinkOrderRequest(OrderType.TEA, Sugar.TWO))
    assertEquals(message.data, "T:2:0")
  }

  @Test
  fun `Should return an formatted message of drink order without sugar and stick`() {
    val message = translator.translate(DrinkOrderRequest(OrderType.COFFEE, Sugar.ZERO))
    assertEquals(message.data, "C::")
  }

  @Test
  fun `Should return an formatted message when order is message type`() {
    val message = translator.translate(MessageOrderRequest(OrderType.MESSAGE, "an simple message"))
    assertEquals(message.data, "M:an simple message")
  }

}
