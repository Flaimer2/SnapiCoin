package ru.snapix.snapicoin.settings

import ru.snapix.library.libs.dazzleconf.annote.ConfDefault.DefaultString
import ru.snapix.library.libs.dazzleconf.annote.ConfKey
import ru.snapix.library.libs.dazzleconf.annote.SubSection

interface MainConfig {
    @SubSection
    fun alias(): Alias
    interface Alias {
        @ConfKey("main-command")
        @DefaultString("snapers|coin|coins")
        fun mainCommand(): String

        @ConfKey("deposit-command")
        @DefaultString("admin deposit")
        fun depositCommand(): String

        @ConfKey("withdraw-command")
        @DefaultString("admin withdraw")
        fun withdrawCommand(): String

        @ConfKey("set-command")
        @DefaultString("admin set")
        fun setCommand(): String
    }

    @ConfKey("console-name-for-sender")
    @DefaultString("console")
    fun consoleNameForSender(): String
}