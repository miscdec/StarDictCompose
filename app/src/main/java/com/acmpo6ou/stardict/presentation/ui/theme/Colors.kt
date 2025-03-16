package com.acmpo6ou.stardict.presentation.ui.theme


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import com.acmpo6ou.stardict.AppContext
import com.sanmer.mrepo.ui.theme.color.BlueDarkColorScheme
import com.sanmer.mrepo.ui.theme.color.BlueLightColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.CyanDarkColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.CyanLightColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.DeepPurpleDarkColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.DeepPurpleLightColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.OrangeDarkColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.OrangeLightColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.SakuraDarkColorScheme
import com.acmpo6ou.stardict.presentation.ui.theme.color.SakuraLightColorScheme


sealed class Colors {
    abstract val id: Int
    abstract val lightColorScheme: ColorScheme
    abstract val darkColorScheme: ColorScheme
}

val mColors = arrayListOf(
    Sakura,
    DeepPurple,
    Blue,
    Cyan,
    Orange
)

fun getColorIds(): List<Int> {
    //TODO desugar support
    return mColors.map { it.id }
}


@RequiresApi(Build.VERSION_CODES.S)
data object Dynamic : Colors() {
    override val id: Int
        get() = -1

    override val lightColorScheme: ColorScheme
        get() = dynamicLightColorScheme(AppContext)

    override val darkColorScheme: ColorScheme
        get() = dynamicDarkColorScheme(AppContext)
}

data object Sakura : Colors() {
    override val id: Int
        get() = 0

    override val lightColorScheme: ColorScheme
        get() = SakuraLightColorScheme

    override val darkColorScheme: ColorScheme
        get() = SakuraDarkColorScheme
}

data object DeepPurple : Colors() {
    override val id: Int
        get() = 1
    override val lightColorScheme: ColorScheme
        get() = DeepPurpleLightColorScheme
    override val darkColorScheme: ColorScheme
        get() = DeepPurpleDarkColorScheme
}

data object Blue : Colors() {
    override val id: Int
        get() = 2
    override val lightColorScheme: ColorScheme
        get() = BlueLightColorScheme
    override val darkColorScheme: ColorScheme
        get() = BlueDarkColorScheme

}

data object Cyan : Colors() {
    override val id: Int
        get() = 3
    override val lightColorScheme: ColorScheme
        get() = CyanLightColorScheme
    override val darkColorScheme: ColorScheme
        get() = CyanDarkColorScheme
}

data object Orange : Colors() {
    override val id: Int
        get() = 4
    override val lightColorScheme: ColorScheme
        get() = OrangeLightColorScheme
    override val darkColorScheme: ColorScheme
        get() = OrangeDarkColorScheme
}
