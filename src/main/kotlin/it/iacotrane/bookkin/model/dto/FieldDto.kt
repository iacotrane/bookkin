package it.iacotrane.bookkin.model.dto

import it.iacotrane.bookkin.model.FieldType

data class FieldDto (
        var id: Long,
        var fieldType: FieldType,
        var name: String,
        var maxPlayer: Int
) {
    constructor() : this(0, FieldType.SOCCER, "", 0)
}