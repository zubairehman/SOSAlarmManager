package com.cubivue.base.util.parser


class JobXMLStructure {

    //XML Attributes
    var job_Attrs: ArrayList<String>? = null //Parent TAG
    var address_address_Attrs: ArrayList<String>? = null //Address TAG
    var info_info_unit_Attrs_Types: ArrayList<String>? = null //Summary Attributes Types
    var main_task_list_Attrs1: ArrayList<String>? = null //Main TAG List Level 1
    var main_task_list_Attrs2: ArrayList<String>? = null //Main TAG List Level 2
    var task_list_info_Attrs: ArrayList<String>? = null  //Task List Info
    var main_task_list_Attrs3: ArrayList<String>? = null //Main TAG List Level 3
    var main_task_list_Attrs4: ArrayList<String>? = null //Main TAG List Level 4
    var task_list_task_Attrs: ArrayList<String>? = null //Task
    var task_address_Attrs: ArrayList<String>? = null //Address
    var task_item_Attrs: ArrayList<String>? = null //Item
    var task_Action_Attrs: ArrayList<String>? = null //Action


    //XML TAGS
    var task_address_TAGS: ArrayList<String>? = null //Address TAGS
    var task_item_TAGS: ArrayList<String>? = null //Item TAGS
    var task_action_TAGS: ArrayList<String>? = null //Action TAGS

    //Configuration
    var max_summary_info_tags = 0
}