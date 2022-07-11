package com.example.springappbackend.controllers

import com.example.springappbackend.entities.HotSauce
import com.example.springappbackend.repositories.HotSauceRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/hotsauces")
class HotSauceController(private val hotSauceRepository: HotSauceRepository) {

    @GetMapping("")
    fun getAll(@RequestParam(value="brandname", required = false, defaultValue = "") brandNameFilter: String,
               @RequestParam(value="saucename", required = false, defaultValue = "") sauceNameFilter: String,
               @RequestParam(value="desc", required = false, defaultValue = "") descFilter: String,
               @RequestParam(value="minheat", required = false, defaultValue = "") minHeat: String,
               @RequestParam(value="maxheat", required = false, defaultValue = "") maxHeat: String): ResponseEntity<List<HotSauce>> {

        val minHeatFilter = if (minHeat.isNotBlank()) minHeat.toInt() else 0
        val maxHeatFilter = if (maxHeat.isNotBlank()) maxHeat.toInt() else MAX_SCOVILLE
        return ResponseEntity(hotSauceRepository.findAll()
            .asSequence()
            .filter { it.brandName.contains(brandNameFilter, true) }
            .filter { it.sauceName.contains(sauceNameFilter, true) }
            .filter { it.description.contains(descFilter, true) }
            .filter { it.heat >= minHeatFilter }
            .filter { it.heat <= maxHeatFilter }
            .toList(),
            HttpStatus.OK
        )
    }

    @GetMapping("/count")
    fun getCount(): ResponseEntity<Long> = ResponseEntity(hotSauceRepository.count(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getHotSauce(@PathVariable id: Long): ResponseEntity<Optional<HotSauce>> { //optional может иметь нулевое значение
        return if(hotSauceRepository.existsById(id)) {
            ResponseEntity(hotSauceRepository.findById(id), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun createHotSauce(@RequestBody hotSauce: HotSauce): ResponseEntity<HotSauce> {
        return ResponseEntity(hotSauceRepository.save(hotSauce), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun changeHotSauce(@PathVariable id: Long, @RequestBody hotSauce: HotSauce): ResponseEntity<HotSauce?> {
        return if (hotSauceRepository.existsById(id)) {
            val originalSauce = hotSauceRepository.findById(id).get()
            val updatedSauce = HotSauce(
                brandName = if (hotSauce.brandName != "") hotSauce.brandName else originalSauce.brandName,
                sauceName = if (hotSauce.sauceName != "") hotSauce.sauceName else originalSauce.sauceName,
                description = if (hotSauce.description != "") hotSauce.description else originalSauce.description,
                url = if (hotSauce.url != "") hotSauce.url else originalSauce.url,
                heat = if (hotSauce.heat != 0) hotSauce.heat else originalSauce.heat,
            )
            ResponseEntity(hotSauceRepository.save(updatedSauce), HttpStatus.OK)
        } else {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteHotSauce(@PathVariable id: Long): ResponseEntity<HotSauce?> {
        return if (hotSauceRepository.existsById(id)) {
            hotSauceRepository.deleteById(id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }




    companion object {
        const val MAX_SCOVILLE = 3_000_000
    }
}