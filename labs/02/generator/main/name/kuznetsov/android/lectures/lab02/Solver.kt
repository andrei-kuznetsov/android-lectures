package name.kuznetsov.android.lectures.lab02

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
                if (param == AlternativeGroup.PIXEL_DENSITY) {
                    // best match
                    var bestResource: AlternativeGroupInst? = null
                    var bestScore: Int? = null
                    val scoreOrder = listOf("ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi", "nodpi", "tvdpi")
                    // FIXME: prefer scale down, not scale up

                    assert(device.params[param] != null)
                    val devOrder = scoreOrder.indexOf(device.params[param]!!.inst)

                    resources.forEach { r ->
                        val inst: AlternativeGroupInst? = r.get(AlternativeGroup.PIXEL_DENSITY)
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