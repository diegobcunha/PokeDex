package com.br.diegocunha.pokedex.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Immutable
data class PokeDexTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val p1: TextStyle,
    val p2: TextStyle,
    val p3: TextStyle,
    val p4: TextStyle,
    val p5: TextStyle,
    val p6: TextStyle,
    val u1: TextStyle,
    val u2: TextStyle
) {

    companion object {
        val default = PokeDexTypography(
            h1 = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp,
            ),

            h2 = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
            ),

            h3 = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
            ),

            p1 = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                lineHeight = 40.sp,
            ),

            p2 = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                lineHeight = 32.sp
            ),

            p3 = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                lineHeight = 28.sp
            ),

            p4 = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                lineHeight = 26.sp
            ),

            p5 = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),

            p6 = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp
            ),

            u1 = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            ),

            u2 = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
            )
        )
    }

}

val TextStyle.bold: TextStyle get() = copy(fontWeight = FontWeight.Bold)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)