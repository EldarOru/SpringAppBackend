package com.example.springappbackend.repositories

import com.example.springappbackend.entities.HotSauce
import org.springframework.data.repository.CrudRepository

interface HotSauceRepository: CrudRepository<HotSauce, Long> {


}