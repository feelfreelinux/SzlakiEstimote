package io.feelfree.estimotezabytki.utils

import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.models.pojo.TrailResponse

class TrailManager() {
    lateinit var trail : TrailResponse
    var currentStep = 0
    var beaconFlaggedList = arrayListOf<Boolean>()

    fun startTrail(trail : TrailResponse) {
        this.trail = trail
        currentStep = 0
        trail.beacons.forEach { beaconFlaggedList.add(false) }
    }

    fun getCurrentBeacon(): BeaconResponse {
        return trail.beacons[currentStep]
    }

    fun getBeaconState(): Boolean {
        return beaconFlaggedList[currentStep]
    }

    fun flagCurrentBeacon() { beaconFlaggedList[currentStep] = true }
}