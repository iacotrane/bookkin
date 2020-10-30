package it.iacotrane.bookkin.exception

import java.lang.RuntimeException

class ReservationAlreadyExistsException(msg: String): RuntimeException(msg)