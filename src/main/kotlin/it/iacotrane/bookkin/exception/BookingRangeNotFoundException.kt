package it.iacotrane.bookkin.exception

import java.lang.RuntimeException

class BookingRangeNotFoundException(msg: String): RuntimeException(msg)