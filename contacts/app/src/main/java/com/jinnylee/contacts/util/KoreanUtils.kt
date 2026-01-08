package com.jinnylee.contacts.util

object KoreanUtils {
    private const val HANGUL_BASE = 0xAC00
    private const val HANGUL_END = 0xD7A3
    private const val CHOSUNG_BASE = 588
    private val CHOSUNG_LIST = charArrayOf(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
        'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )

    fun getInitialSound(text: String): String {
        val stringBuilder = StringBuilder()
        for (c in text) {
            if (c.code in HANGUL_BASE..HANGUL_END) {
                val chosungIndex = (c.code - HANGUL_BASE) / CHOSUNG_BASE
                stringBuilder.append(CHOSUNG_LIST[chosungIndex])
            } else {
                stringBuilder.append(c)
            }
        }
        return stringBuilder.toString()
    }
}
