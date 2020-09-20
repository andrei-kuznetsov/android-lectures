package name.kuznetsov.android.lectures.lab02

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

        override fun canBeUsedInDeviceConfiguration(param: AlternativeGroupInst): Boolean {
            return param.inst != "nodpi"
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

    open fun canBeUsedInDeviceConfiguration(param: AlternativeGroupInst): Boolean = true
}