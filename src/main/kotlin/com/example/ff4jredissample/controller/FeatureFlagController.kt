package com.example.ff4jredissample.controller

import org.ff4j.FF4j
import org.ff4j.core.Feature
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ff4j")
class FeatureFlagController(
    private val ff4j: FF4j,
) {
    @GetMapping("/toggle-status/{name}")
    fun getToggleStatus(@PathVariable name: String): String {
        return ff4j.check(name).let {
            val status = if (it) "enabled" else "disabled"
            "Feature flag=${name} is $status"
        }
    }

    @GetMapping("/available-toggles")
    fun getAvailableToggles(): Map<String, Feature> {
        return ff4j.features
    }

    @PostMapping("/create-toggle/{name}")
    fun createToggle(@PathVariable name: String) {
        ff4j.createFeature(name)
    }

    @DeleteMapping("/delete-toggle/{name}")
    fun deleteFeatureFlag(@PathVariable name: String) {
        ff4j.delete(name)
    }

    @PutMapping("/turn-toggle/{name}")
    fun turnToggle(@PathVariable name: String) {
        if (ff4j.check(name)) ff4j.disable(name) else ff4j.enable(name)
    }
}