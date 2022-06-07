package me.da0hn.machine

enum class OrderType {

  TEA {
    override fun type(): Char = 'T'
  },
  COFFEE {
    override fun type(): Char = 'C'
  },
  CHOCOLATE {
    override fun type(): Char = 'H'
  },
  MESSAGE {
    override fun type(): Char = 'M'
  };

  abstract fun type(): Char
}
