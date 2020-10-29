package it.iacotrane.bookkin.exception

import java.lang.RuntimeException

class CompanyNotFoundException(msg: String) : RuntimeException(msg)