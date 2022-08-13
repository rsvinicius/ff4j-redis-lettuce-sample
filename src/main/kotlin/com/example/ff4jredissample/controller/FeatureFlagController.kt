package com.example.ff4jredissample.controller

import org.ff4j.FF4j
import org.ff4j.core.Feature
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ff4j")
class FeatureFlagController(
    private val ff4j: FF4j,
) {
    @GetMapping("/ff-status/{name}")
    fun getFeatureFlagStatus(@PathVariable name: String): String {
        return ff4j.check(name).let {
            val status = if (it) "enabled" else "disabled"
            "Feature flag=${name} is $status"
        }
    }

    @GetMapping("/available-ffs")
    fun getAvailableFeatureFlags(): Map<String, Feature> {
        return ff4j.features
    }

    @PutMapping("/create-ff/{name}")
    fun createNewFeatureFlag(@PathVariable name: String) {
        ff4j.createFeature(name)
    }

    @PutMapping("/delete-ff/{name}")
    fun deleteFeatureFlag(@PathVariable name: String) {
        ff4j.delete(name)
    }

    @PutMapping("/toogle-ff/{name}")
    fun toggleFeature(@PathVariable name: String) : String {
        return if (ff4j.check(name)) {
            ff4j.disable(name)
            "Feature flag=${name} disabled!"
        } else {
            ff4j.enable(name)
            "Feature flag=${name} enabled!"
        }
    }
}