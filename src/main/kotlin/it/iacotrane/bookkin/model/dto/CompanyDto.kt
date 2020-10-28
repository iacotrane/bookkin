package it.iacotrane.bookkin.model.dto

data class CompanyDto (
        var id: Long,
        var name: String,
        var description: String,
        var city: String,
        var address: String
)