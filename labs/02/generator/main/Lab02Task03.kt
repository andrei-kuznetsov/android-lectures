import AlternativeGroup.*

enum class AlternativeGroup(vararg types: String) {
    LOCALE_LANG("en", "fr"),
    LOCALE_REGION("rUS", "rFR", "rCA"),
    SCREEN_SIZE("small", "normal", "large", "xlarge"){
        override fun compatible(resParam: AlternativeGroupInst, devParam: AlternativeGroupInst): Boolean {
            return types.indexOf(resParam.inst) <= types.indexOf(devParam.inst)
        }
    },
    SCREEN_ASPECT("long", "notlong"),
    ROUND_SCREEN("round", "notround"),
    ORIENTATION("port", "land"),
    UI_MODE("car", "desk", "television", "appliance", "watch", "vrheadset"),
    NIGHT_MODE("night", "notnight"),
    PIXEL_DENSITY("ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi", "nodpi", "tvdpi") {
        override fun compatible(resParam: AlternativeGroupInst, devParam: AlternativeGroupInst): Boolean {
            return true
        }
    },
    TOUCH("notouch", "finger"),
    PRIMARY_INPUT("nokeys", "qwerty", "12key"),
    NAV_KEYS("nonav", "dpad", "trackball", "wheel"),
    PLATFORM_VER("v25", "v26", "v27") {
        override fun compatible(resParam: AlternativeGroupInst, devParam: AlternativeGroupInst): Boolean {
            return resParam.inst <= devParam.inst
        }
    };

    val types: List<String> = listOf(*types)
    fun randomInstance(): AlternativeGroupInst = AlternativeGroupInst(this, randomType())

    private fun randomType() = types[nextInt(types.size)]

    private fun nextInt(max: Int) = (Math.random() * max).toInt()

    open fun compatible(resParam: AlternativeGroupInst, devParam: AlternativeGroupInst): Boolean {
        return resParam.inst == devParam.inst
    }

    fun notCompatible(resParam: AlternativeGroupInst, devParam: AlternativeGroupInst) = !compatible(resParam, devParam)
}

data class AlternativeGroupInst(val group: AlternativeGroup, val inst: String)

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

class Device {
    val params: Map<AlternativeGroup, AlternativeGroupInst>

    override fun toString(): String {
        return params.values.
                sortedBy { it.group.ordinal }.joinToString(separator = "\n") { "${it.group}: ${it.inst}" }
    }

    constructor() {
        val devConfig = mutableMapOf<AlternativeGroup, AlternativeGroupInst>()
        for (group in values()) {
            val randomInstance = group.randomInstance()
            devConfig.put(randomInstance.group, randomInstance)
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

class Resource(private val alts: List<AlternativeGroupInst>) {

    fun contradicts(device: Device, explanation: (AlternativeGroupInst, AlternativeGroupInst) -> Unit): Boolean {
        alts.forEach { q ->
            val devParam = device.params[q.group]
            if (devParam == null) {
                System.err.println("Device configuration was not provided for qualifier ${q.group}")
            } else {
                if (q.group.notCompatible(q, devParam)) {
                    print("$this: ")
                    explanation.invoke(q, devParam)
                    return true
                }
            }
        }
        return false
    }

    fun contains(group: AlternativeGroup) = alts.any { alt -> alt.group == group }
    fun contains(inst: AlternativeGroupInst) = alts.contains(inst)
    fun notContain(group: AlternativeGroup) = !contains(group)
    fun notContain(inst: AlternativeGroupInst) = !contains(inst)


    fun get(group: AlternativeGroup): AlternativeGroupInst? = alts.find { it.group == group }

    override fun toString(): String {
        if (alts.isEmpty()) return "(default)"
        else return alts.joinToString(separator = "-") { it.inst }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource

        if (alts != other.alts) return false

        return true
    }

    override fun hashCode(): Int {
        return alts.hashCode()
    }

    companion object {
        fun fromDeviceConfig(device: Device): MutableList<Resource> {
            val res = mutableListOf(Resource(listOf())) // default resource
            for (i in 1..10) {
                val alts = mutableListOf<AlternativeGroupInst>()
                for (param in device.params.keys) {
                    if (Math.random() < 0.3) {
                        alts.add(param.randomInstance())
                    }
                }
                res.add(Resource(alts))
            }
            return res
        }
    }
}

private fun printRemaining(resources: List<Resource>) {
    val items = resources.map { it.toString() }.toSet()
    items.forEach { println(it) }
}

class Solver {
    fun solveTask(device: Device, resources: MutableList<Resource>): Resource {
        printRemaining(resources)
        println("---")

        println("Removing contradictory configurations => (left)")
        resources.removeIf { r -> r.contradicts(device, { res, dev -> println("$res != $dev") }) }
        printRemaining(resources)
        println("---")

        for (param in device.params.keys.sortedBy { it.ordinal }) {
            if (hasResource(param, resources)) {
                println("Removing all the resources not specifying $param => (left)")
                resources.removeIf { r -> r.notContain(param) }
                printRemaining(resources)
                if (param == PIXEL_DENSITY) {
                    // best match
                    var bestResource: AlternativeGroupInst? = null
                    var bestScore: Int? = null
                    val scoreOrder = listOf("ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi", "nodpi", "tvdpi") // FIXME: nodpi, tvdpi
                    // FIXME: prefer scale down, not scale up

                    assert(device.params[param] != null)
                    val devOrder = scoreOrder.indexOf(device.params[param]!!.inst)

                    resources.forEach { r ->
                        val inst: AlternativeGroupInst? = r.get(PIXEL_DENSITY)
                        val resOrder = scoreOrder.indexOf(inst!!.inst)
                        val score = Math.abs(resOrder - devOrder)
                        if (bestScore == null || score < bestScore!!) {
                            bestResource = inst
                            bestScore = score
                        }
                    }

                    if (bestResource != null) {
                        println("  Additionally remove all the resources not specifying $param = $bestResource => (left)")
                        resources.removeIf { r -> r.notContain(bestResource!!) }
                    }
                }
            } else {
                println("no resources having $param qualifier")
            }
            println("---")
        }

        println(" ==== ")
        printRemaining(resources)
        assert(resources.size == 1)
        return resources[0]
    }

    private fun hasResource(param: AlternativeGroup, resources: MutableList<Resource>) = resources.any { r -> r.contains(param) }
}