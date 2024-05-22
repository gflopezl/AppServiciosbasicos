package com.example.appserviciosbasicos.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Registro (
    @PrimaryKey(autoGenerate = true) var id:Long? = null,
    var monto:Long,
    var fecha: LocalDate,
    var categoria: String,
)