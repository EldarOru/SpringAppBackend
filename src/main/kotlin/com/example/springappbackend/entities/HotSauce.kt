package com.example.springappbackend.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob

@Entity
data class HotSauce(
    @Id @GeneratedValue
    val id: Long = 0,

    var brandName: String = "",
    var sauceName: String = "",

    @Lob
    var description: String = "",
    @Lob
    var url: String = "",

    var heat: Int = 0
)