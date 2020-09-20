package name.kuznetsov.android.lectures.lab02

class Device {
    val params: Map<AlternativeGroup, AlternativeGroupInst>

    override fun toString(): String {
        return params.values.
                sortedBy { it.group.ordinal }.joinToString(separator = "\n") { "${it.group}: ${it.inst}" }
    }

    constructor() {
        val devConfig = mutableMapOf<AlternativeGroup, AlternativeGroupInst>()
        for (group in AlternativeGroup.values()) {
            do {
                val randomInstance = group.randomInstance()
                devConfig.put(randomInstance.group, randomInstance)
            } while (!randomInstance.canBeUsedInDeviceConfiguration())
        }
        params = devConfig
    }

    constructor(params: List<AlternativeGroupInst>) {
        val devConfig = mutableMapOf<AlternativeGroup, AlternativeGroupInst>()
        params.forEach {
            devConfig.put(it.group, it)
        }
        this.params = devConfig
    }
}