package com.cubivue.base.models.job.xml

data class Info(var info_unit: List<InfoUnit>) {

    override fun toString(): String {
        return "Info(info_unit=$info_unit)"
    }
}