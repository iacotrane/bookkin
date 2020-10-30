package it.iacotrane.bookkin.exception

import java.lang.RuntimeException

class UserNotFoundException(msg: String): RuntimeException(msg)