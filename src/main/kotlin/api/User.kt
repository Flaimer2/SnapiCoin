package ru.snapix.snapicoin.api

import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, var coin: Int) {
    fun hasCoin(amount: Int): Boolean {
        return coin >= amount
    }
}
