package name.kuznetsov.android.lectures.lab02

import name.kuznetsov.android.lectures.lab02.AlternativeGroup.*
import org.junit.Test
import java.nio.file.Paths
import java.util.*
import kotlin.test.assertEquals

class E2ETest {

    @Test
    fun wellKnownSampleTest() {
        val solver = Solver()
        val device = createDeviceForWellKnownSample()
        val resources = createResourcesForWellKnownSample()

        val resolved: Resource = solver.solveTask(device, resources)
        val expected = Resource(listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(ORIENTATION, "port")))

        assertEquals(expected, resolved)
    }

    @Test
    fun wellKnownSampleTest2() {
        val solver = Solver()
        val device = createDeviceForWellKnownSample()
        val resources = createResourcesForWellKnownSample()
        resources.add(Resource(listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(ORIENTATION, "port"),
                AlternativeGroupInst(PIXEL_DENSITY, "ldpi"))))

        val resolved: Resource = solver.solveTask(device, resources)
        val expected = Resource(listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(ORIENTATION, "port"),
                AlternativeGroupInst(PIXEL_DENSITY, "ldpi")))

        assertEquals(expected, resolved)
    }

    @Test
    fun wellKnownSampleTest3() {
        val solver = Solver()
        val device = createDeviceForWellKnownSample()
        val resources = createResourcesForWellKnownSample()
        resources.add(Resource(listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(ORIENTATION, "port"),
                AlternativeGroupInst(PIXEL_DENSITY, "ldpi"))))
        resources.add(Resource(listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(ORIENTATION, "port"),
                AlternativeGroupInst(PIXEL_DENSITY, "mdpi"))))

        val resolved: Resource = solver.solveTask(device, resources)
        val expected = Resource(listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(ORIENTATION, "port"),
                AlternativeGroupInst(PIXEL_DENSITY, "mdpi")))

        assertEquals(expected, resolved)
    }


    @Test
    fun fromVariant() {
        val variant = 6

        val scan = Scanner(Paths.get("../VARIANTS03.txt").toAbsolutePath())

        findMarker(scan, "Вариант $variant:")

        findMarker(scan, "Конфигурация устройства:")
        val device: Device = readDeviceConfig(scan)

        findMarker(scan, "Конфигурация ресурсов:")
        val resources: MutableList<Resource> = readResourceConfig(scan)

        val solver = Solver()
        val solved = solver.solveTask(device, resources)
        println(solved)
    }

    private val resourceInstLookup: Map<String, AlternativeGroupInst>

    init {
        val lookup = mutableMapOf<String, AlternativeGroupInst>()
        AlternativeGroup.values().forEach { group ->
            group.types.forEach { type ->
                lookup.put(type, AlternativeGroupInst(group, type))
            }
        }
        resourceInstLookup = lookup
    }

    private fun readResourceConfig(scan: Scanner): MutableList<Resource> {
        val resources = mutableListOf<Resource>()

        do {
            val ln = scan.nextLine()
            if (ln.isBlank()) {
                break
            } else if (ln == "(default)") {
                resources.add(Resource(mutableListOf()))
            } else {
                val altInst = ln.split('-')
                val resConfig = mutableListOf<AlternativeGroupInst>()
                altInst.forEach {
                    val inst = resourceInstLookup.get(it) ?: error("Missing matching for string $it")
                    resConfig.add(inst)
                }

                resources.add(Resource(resConfig))
            }
        } while (scan.hasNextLine())

        return resources
    }

    private fun readDeviceConfig(scan: Scanner): Device {
        val config = mutableListOf<AlternativeGroupInst>()

        do {
            val ln = scan.nextLine()
            if (ln.isBlank()) {
                break
            } else {
                val altInst = ln.split(':')
                assert(altInst.size == 2)

                if (altInst[0] == "LOCALE") {
                    val langReg = altInst[1].trim().split('-')
                    config.add(AlternativeGroupInst(LOCALE_LANG, langReg[0]))
                    if (langReg.size > 1) {
                        config.add(AlternativeGroupInst(LOCALE_REGION, langReg[1]))
                    }
                } else {
                    val inst = AlternativeGroupInst(valueOf(altInst[0].trim()), altInst[1].trim())
                    config.add(inst)
                }
            }
        } while (scan.hasNextLine())

        return Device(config)
    }

    private fun findMarker(scan: Scanner, marker: String) {
        do {
            val ln = scan.nextLine()
            if (ln.contains(marker)) {
                return
            }
        } while (scan.hasNextLine())
        throw AssertionError("marker not found: $marker")
    }

    fun createResourcesForWellKnownSample(): MutableList<Resource> {
        // sample from https://developer.android.com/guide/topics/resources/providing-resources#BestMatch
        return mutableListOf(
                Resource(listOf()),

                Resource(listOf(
                        AlternativeGroupInst(LOCALE_LANG, "en"))),

                Resource(listOf(
                        AlternativeGroupInst(LOCALE_LANG, "fr"),
                        AlternativeGroupInst(LOCALE_REGION, "CA")
                )),

                Resource(listOf(
                        AlternativeGroupInst(LOCALE_LANG, "en"),
                        AlternativeGroupInst(ORIENTATION, "port"))),

                Resource(listOf(
                        AlternativeGroupInst(LOCALE_LANG, "en"),
                        AlternativeGroupInst(TOUCH, "notouch"),
                        AlternativeGroupInst(PRIMARY_INPUT, "12key"))),

                Resource(listOf(
                        AlternativeGroupInst(ORIENTATION, "port"),
                        AlternativeGroupInst(PIXEL_DENSITY, "ldpi"))),

                Resource(listOf(
                        AlternativeGroupInst(ORIENTATION, "port"),
                        AlternativeGroupInst(TOUCH, "notouch"),
                        AlternativeGroupInst(PRIMARY_INPUT, "12key")))
        )
    }

    fun createDeviceForWellKnownSample(): Device {
        // sample from https://developer.android.com/guide/topics/resources/providing-resources#BestMatch
        val params: List<AlternativeGroupInst> = listOf(
                AlternativeGroupInst(LOCALE_LANG, "en"),
                AlternativeGroupInst(LOCALE_REGION, "rGB"),
                AlternativeGroupInst(ORIENTATION, "port"),
                AlternativeGroupInst(PIXEL_DENSITY, "hdpi"),
                AlternativeGroupInst(TOUCH, "notouch"),
                AlternativeGroupInst(PRIMARY_INPUT, "12key"))

        return Device(params)
    }
}