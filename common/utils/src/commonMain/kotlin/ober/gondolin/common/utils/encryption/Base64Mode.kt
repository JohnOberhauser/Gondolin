package ober.gondolin.common.utils.encryption

sealed class Base64Mode(
    val value: Int
) {
    object Default: Base64Mode(0)
    object NoPadding: Base64Mode(1)
    object NoWrap: Base64Mode(2)
    object CRLF: Base64Mode(3)
    object UrlSafe: Base64Mode(8)
}
