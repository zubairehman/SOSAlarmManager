package com.embrace.soslibrary.constants

class Constants {

    //Headers
    object Headers {
        const val HEADER_AUTHORIZATION = "Authorization"
        val HEADER_DEVICE_ID = "DeviceId"
        val HEADER_ACCEPT = "Accept"
        val HEADER_CONTENT_TYPE = "Content-Type"
        val HEADER_APP_VERSION = "App-version"
        val HEADER_APP_ID = "App-id"

        //Session Token
        val token: String
            get() = "Bearer "
    }

    //Font Types
    object Fonts {
        val FONT_ROBOTO_BLACK = 0
        val FONT_ROBOTO_BLACK_ITALIC = 1
        val FONT_ROBOTO_BOLD = 2
        val FONT_ROBOTO_BOLD_ITALIC = 3
        val FONT_ROBOTO_ITALIC = 4
        val FONT_ROBOTO_LIGHT = 5
        val FONT_ROBOTO_LIGHT_ITALIC = 6
        val FONT_ROBOTO_MEDIUM = 7
        val FONT_ROBOTO_MEDIUM_ITALIC = 8
        val FONT_ROBOTO_REGULAR = 9
        val FONT_ROBOTO_THIN = 10
        val FONT_ROBOTO_THIN_ITALIC = 11
        val FONT_ROBOTO_CONDENSED_BOLD = 12
        val FONT_ROBOTO_CONDENSED_BOLD_ITALIC = 13
        val FONT_ROBOTO_CONDENSED_ITALIC = 14
        val FONT_ROBOTO_CONDENSED_LIGHT = 15
        val FONT_ROBOTO_CONDENSED_LIGHT_ITALIC = 16
        val FONT_ROBOTO_CONDENSED_REGULAR = 17
    }
}
