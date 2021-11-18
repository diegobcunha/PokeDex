package com.br.diegocunha.pokedex.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


@Immutable
data class PokeDexColors(
    val text: GroupText,
    val context: GroupContext,
    val base: GroupBase,
    val isLight: Boolean,
    val element: GroupElement
) {
    companion object : Mode<PokeDexColors> {
        override val lightMode: PokeDexColors = PokeDexColors(
            text = GroupText.lightMode,
            context = ThemeColors.lightMode,
            base =  GroupBase.lightMode,
            element = GroupElement.lightMode,
            isLight = true
        )

        override val darkMode: PokeDexColors = PokeDexColors(
            text = GroupText.darkMode,
            context = ThemeColors.darkMode,
            base = GroupBase.darkMode,
            element = GroupElement.darkMode,
            isLight = false
        )
    }
}

@Immutable
data class GroupText(
    val primary: Color,
    val secondary: Color,
    val placeholder: Color,
    val disabled: Color,
    val negative: Color,
    val overImage: Color,
    val alpha: Color,
    val onError: Color,
    val onSuccess: Color
) {
    internal companion object : Mode<GroupText> {
        override val lightMode = GroupText(
            primary = Color(0xFF2F2F33),
            secondary = Color(0xFF757680),
            placeholder = Color(0xFF9599A6),
            disabled = Color(0xFFC9CAD4),
            alpha = Color(0x3FFFFFFF),
            negative = Color(0xFFFFFFFF),
            overImage = Color(0xFFFFFFFF),
            onError = Color(0xFFCD1A1A),
            onSuccess = Color(0xFF008061)
        )
        override val darkMode = GroupText(
            primary = Color(0xFFFFFFFF),
            secondary = Color(0xFF757680),
            placeholder = Color(0xFF7E818C),
            disabled = Color(0xFF5C5D66),
            alpha = Color(0xB3FFFFFF),
            negative = Color(0xFF000000),
            overImage = Color(0xFFFFFFFF),
            onError = Color(0xFFF73939),
            onSuccess = Color(0xFF16B690)
        )
    }
}

@Immutable
data class GroupContext(
    val primary: Color,
    val primaryDarken: Color,
    val primaryHover: Color,
    val primaryActive: Color,
    val secondary: Color,
    val secondaryDarken: Color,
    val link: Color,
)

object ThemeColors: Mode<GroupContext> {
    override val lightMode = GroupContext(
        primary = Color(0xFF1BB958),
        primaryDarken = Color(0xFF0C873C),
        primaryHover = Color(0xFF0C873C),
        primaryActive = Color(0xFF076E2F),
        secondary = Color(0xFFEBFFF3),
        secondaryDarken = Color(0xFFC7FADB),
        link = Color(0xFF1BB958),
    )
    override val darkMode = GroupContext(
        primary = Color(0xFF1BB958),
        primaryDarken = Color(0xFF0C873C),
        primaryHover = Color(0xFF0C873C),
        primaryActive = Color(0xFF076E2F),
        secondary = Color(0xFFF2FFF7),
        secondaryDarken = Color(0xFFC7FADB),
        link = Color(0xFF1BB958),
    )
}

@Immutable
data class GroupBase(
    val primary: Color,
    val primaryDarken: Color,
    val select: Color,
    val selectDarken: Color,
    val secondary: Color,
    val secondaryHover: Color,
    val secondaryActive: Color,
    val overSecondary: Color,
    val disabled: Color,
    val overDisabled: Color
) {
    internal companion object : Mode<GroupBase> {
        override val lightMode = GroupBase(
            primary = Color(0xFFFFFFFF),
            primaryDarken = Color(0xFFF5F6FA),
            select = Color(0xFF2F2F33),
            selectDarken = Color(0xFF1F1F1F),
            secondary = Color(0xFF757680),
            secondaryHover = Color(0xFF5C5D66),
            secondaryActive = Color(0xFF4A4B52),
            overSecondary = Color(0xFFFFFFFF),
            disabled = Color(0xFFC9CAD4),
            overDisabled = Color(0xFF9599A6)
        )
        override val darkMode = GroupBase(
            primary = Color(0xFF2F2F33),
            primaryDarken = Color(0xFF1F1F1F),
            select = Color(0xFFFFFFFF),
            selectDarken = Color(0xFFF5F6FA),
            secondary = Color(0xFF474952),
            secondaryHover = Color(0xFF3A3B40),
            secondaryActive = Color(0xFF2F2F33),
            overSecondary = Color(0xFFFFFFFF),
            disabled = Color(0xFF5C5D66),
            overDisabled = Color(0xFF9599A6)
        )
    }
}

@Immutable
data class GroupElement(
    val primary: Color,
    val secondary: Color,
    val placeholder: Color,
    val disabled: Color,
    val negative: Color,
    val overImage: Color,
    val onError: Color,
    val onSuccess: Color
) {
    internal companion object : Mode<GroupElement> {
        override val lightMode = GroupElement(
            primary = Color(0xFF2F2F33),
            secondary = Color(0xFF757680),
            placeholder = Color(0xFF9599A6),
            disabled = Color(0xFFC9CAD4),
            negative = Color(0xFFFFFFFF),
            overImage = Color(0xFFFFFFFF),
            onError = Color(0xFFCD1A1A),
            onSuccess = Color(0xFF008061)
        )
        override val darkMode = GroupElement(
            primary = Color(0xFFFFFFFF),
            secondary = Color(0xFF757680),
            placeholder = Color(0xFF7E818C),
            disabled = Color(0xFF5C5D66),
            negative = Color(0xFF000000),
            overImage = Color(0xFFFFFFFF),
            onError = Color(0xFFF73939),
            onSuccess = Color(0xFF16B690)
        )
    }
}