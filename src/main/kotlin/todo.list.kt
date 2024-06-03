import ru.snapix.library.database.Database

// SnapiLibrary
//abstract class Cache
// SnapiCoin
//class CoinCache : Cache()
class CoinDatabase
// Нужно сделать publish message и subscriber, который будет принимать значение из polymorphic serialized class (link: https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/polymorphism.md#open-polymorphism)
// Cache система как в https://github.com/snapixteam/SnapiClans/tree/main/src/main/kotlin/ru/mcsnapix/snapiclans/caching только за место SQLMessenger нужен RedisMessenger