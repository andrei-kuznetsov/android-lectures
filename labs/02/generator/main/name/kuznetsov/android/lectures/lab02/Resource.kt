package name.kuznetsov.android.lectures.lab02

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