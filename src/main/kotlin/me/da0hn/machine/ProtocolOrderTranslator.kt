package me.da0hn.machine


class ProtocolOrderTranslator {

  fun translate(request: OrderRequest): Protocol {
    val message = when (request) {
      is OrderRequest.DrinkOrderRequest -> {
        val sugarQuantityTranslated = if (request.sugarQuantity() == 0) "" else request.sugarQuantity().toString()
        val stickQuantityTranslated = request.stickQuantity() ?: ""
        "${request.type()}:$sugarQuantityTranslated:$stickQuantityTranslated"
      }
      is OrderRequest.MessageOrderRequest -> "${request.type()}:${request.message()}"
    }

    return OrderMessageProtocol(message)
  }

}
