package ru.snapix.snapicoin.messenger.actions

import kotlinx.serialization.Serializable

@Serializable
abstract class Action {
    abstract fun executeIncomingMessage()
}