package name.kuznetsov.android.lectures.lab02

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AlternativeGroupTest {
    @Test
    fun apiVersionTest() {
        assertFalse(AlternativeGroup.PLATFORM_VER.compatible(AlternativeGroupInst(AlternativeGroup.PLATFORM_VER, "v25"), AlternativeGroupInst(AlternativeGroup.PLATFORM_VER, "v24")))
        assertTrue(AlternativeGroup.PLATFORM_VER.compatible(AlternativeGroupInst(AlternativeGroup.PLATFORM_VER, "v25"), AlternativeGroupInst(AlternativeGroup.PLATFORM_VER, "v25")))
        assertTrue(AlternativeGroup.PLATFORM_VER.compatible(AlternativeGroupInst(AlternativeGroup.PLATFORM_VER, "v25"), AlternativeGroupInst(AlternativeGroup.PLATFORM_VER, "v26")))
    }

    @Test
    fun screenSizeTest() {
        assertFalse(AlternativeGroup.SCREEN_SIZE.compatible(AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "large"), AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "normal")))
        assertFalse(AlternativeGroup.SCREEN_SIZE.compatible(AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "normal"), AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "small")))
        assertTrue(AlternativeGroup.SCREEN_SIZE.compatible(AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "normal"), AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "normal")))
        assertTrue(AlternativeGroup.SCREEN_SIZE.compatible(AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "normal"), AlternativeGroupInst(AlternativeGroup.SCREEN_SIZE, "large")))
    }

    @Test
    fun noDpiNotAllowedInDevicesTest() {
        assertFalse(AlternativeGroup.PIXEL_DENSITY.canBeUsedInDeviceConfiguration(AlternativeGroupInst(AlternativeGroup.PIXEL_DENSITY, "nodpi")))
    }
}