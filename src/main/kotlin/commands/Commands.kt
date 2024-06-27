package ru.snapix.snapicoin.commands

import ru.snapix.library.libs.commands.PaperCommandManager
import ru.snapix.library.players
import ru.snapix.library.addReplacements
import ru.snapix.snapicoin.settings.Settings
import ru.snapix.snapicoin.snapiCoin

object Commands {
    private val manager = PaperCommandManager(snapiCoin)

    fun enable() {
        registerCommandCompletions()
        registerCommandReplacements()
        manager.registerCommand(CoinCommand())
    }

    private fun registerCommandCompletions() {
        val commandCompletions = manager.commandCompletions

        commandCompletions.registerAsyncCompletion("players") { context ->
            players()
        }
        commandCompletions.registerAsyncCompletion("nothing") { _ ->
            emptyList<String>()
        }
    }

    private fun registerCommandReplacements() {
        val config = Settings.config.alias()

        manager.commandReplacements.addReplacements("coin_command_",
            "main" to config.mainCommand(),
            "deposit_coin" to config.depositCommand(),
            "withdraw_coin" to config.withdrawCommand(),
            "set_coin" to config.setCommand()
        )
    }
}