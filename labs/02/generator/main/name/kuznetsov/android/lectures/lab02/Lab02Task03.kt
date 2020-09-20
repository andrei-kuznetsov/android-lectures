package name.kuznetsov.android.lectures.lab02

data class AlternativeGroupInst(val group: AlternativeGroup, val inst: String) {
    fun canBeUsedInDeviceConfiguration(): Boolean = group.canBeUsedInDeviceConfiguration(this)
}

object Lab02Task03 {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 1..30) {
            val device = Device()
            val resources = Resource.fromDeviceConfig(device)

            println("====================================")
            println("Вариант $i:")
            println("====================================")
            println("Конфигурация устройства:\n$device")
            println()
            println("Конфигурация ресурсов:")
            printRemaining(resources)
            println()
        }
    }
}

fun printRemaining(resources: List<Resource>) {
    val items = resources.map { it.toString() }.toSet()
    items.forEach { println(it) }
}
