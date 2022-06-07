package me.da0hn.machine

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


@Tag("unit")
@DisplayName("Test order emitter")
internal class OrderEmitterTest {

  @Nested
  @DisplayName("Receive drink order")
  inner class DrinkOrderTest {
    private val orderEmitter = OrderEmitter()

    @Test
    fun `Should make a tea drink with sugar and 1 stick`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("T:1:0")
      )

      val expectedMessage = "Drink maker makes 1 tea with 1 sugar and a stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should make a coffee drink with no sugar and no stick`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("C::")
      )

      val expectedMessage = "Drink maker makes 1 coffee with no sugar and no stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should make a chocolate drink with 2 sugars and 1 stick`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("H:2:0")
      )

      val expectedMessage = "Drink maker makes 1 chocolate with 2 sugar and a stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when have sugar and hasn't stick`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("C:2:")
      )

      val expectedMessage = "It's not possible make drink with some sugar and no stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when has unknown drink identifier`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("U:2:0")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when has invalid quantity sugar`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("C:5:0")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when has invalid drink identifier`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol(":1:0")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }
  }

  @Nested
  @DisplayName("Receive a message")
  inner class MessageOrderTest {

    private val orderEmitter = OrderEmitter()

    @Test
    fun `Should delivery message`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("M:a simple message")
      )

      val expectedMessage = "Sending: a simple message"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not delivery empty message`() {
      val message = this.orderEmitter.emit(
        OrderMessageProtocol("M:")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }
  }

}
