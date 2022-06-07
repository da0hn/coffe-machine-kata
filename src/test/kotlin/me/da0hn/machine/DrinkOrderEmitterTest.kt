package me.da0hn.machine

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


@Tag("unit")
@DisplayName("Test drink order emitter")
internal class DrinkOrderEmitterTest {

  @Nested
  @DisplayName("Receive drink order")
  inner class DrinkOrderTest {
    private val drinkOrderEmitter = DrinkOrderEmitter()

    @Test
    fun `Should make a tea drink with sugar and 1 stick`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("T:1:0")
      )

      val expectedMessage = "Drink maker makes 1 tea with 1 sugar and a stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should make a coffee drink with no sugar and no stick`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("C::")
      )

      val expectedMessage = "Drink maker makes 1 coffee with no sugar and no stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should make a chocolate drink with 2 sugars and 1 stick`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("H:2:0")
      )

      val expectedMessage = "Drink maker makes 1 chocolate with 2 sugar and a stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when have sugar and hasn't stick`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("C:2:")
      )

      val expectedMessage = "It's not possible make drink with some sugar and no stick"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when has unknown drink identifier`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("U:2:0")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when has invalid quantity sugar`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("C:5:0")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not make drink when has invalid drink identifier`() {
      val message: DrinkOrderMessage = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol(":1:0")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }
  }

  @Nested
  @DisplayName("Receive a message")
  inner class MessageOrderTest {

    private val drinkOrderEmitter = DrinkOrderEmitter()

    @Test
    fun `Should delivery message`() {
      val message = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("M:a simple message")
      )

      val expectedMessage = "Sending: a simple message"

      assertEquals(expectedMessage, message.data)
    }

    @Test
    fun `Should not delivery empty message`() {
      val message = this.drinkOrderEmitter.emit(
        DrinkOrderProtocol("M:")
      )

      val expectedMessage = "Error while trying translate message"

      assertEquals(expectedMessage, message.data)
    }
  }

}
