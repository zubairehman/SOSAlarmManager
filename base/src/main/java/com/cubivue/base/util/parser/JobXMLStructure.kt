package com.cubivue.base.util.parser


class JobXMLStructure {

    //XML Attributes
    var job_Attrs: ArrayList<String>? = null //Parent TAG
    val info_info_unit_Attrs: ArrayList<String>? = null //Summary TAG
    val main_task_list_Attrs1: ArrayList<String>? = null //Main TAG List Level 1
    val main_task_list_Attrs2: ArrayList<String>? = null //Main TAG List Level 2
    val task_list_info_Attrs: ArrayList<String>? = null  //Task List Info
    val main_task_list_Attrs3: ArrayList<String>? = null //Main TAG List Level 3
    val main_task_list_Attrs4: ArrayList<String>? = null //Main TAG List Level 4
    val task_list_task_Attrs: ArrayList<String>? = null //Task
    val task_address_Attrs: ArrayList<String>? = null //Address
    val task_item_Attrs: ArrayList<String>? = null //Item
    val task_Action_Attrs: ArrayList<String>? = null //Action


    //XML TAGS
    val task_address_TAGS: ArrayList<String>? = null //Address TAGS
    val task_item_TAGS: ArrayList<String>? = null //Item TAGS
    val task_action_TAGS: ArrayList<String>? = null //Action TAGS
}