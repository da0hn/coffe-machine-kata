package me.da0hn.machine

enum class Sugar {
  ZERO {
    override fun stick(): Int? = null

    override fun value(): Int = 0
  },
  ONE {
    override fun stick(): Int = 0

    override fun value(): Int = 1
  },
  TWO {
    override fun stick(): Int = 0

    override fun value(): Int = 2
  };

  abstract fun stick(): Int?

  abstract fun value(): Int
}
