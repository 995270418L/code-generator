package com.steve;

import com.steve.bootstrap.MakerInsertSelect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DMMasterInsertSelectConfigMaker {

    public static void main(String[] args) {
        Map<String, List<String>> groupMap = new HashMap<String, List<String>>();
        List<String> groupList;
        String tablePrefix;

        groupList = new ArrayList<>();
//        groupList.add("dm_user");
//        groupList.add("dm_agent_info");
//        groupList.add("dm_machine");
//        groupList.add("dm_sys");
//        groupList.add("dm_package_manager");
//        groupList.add("dm_project");
//        groupList.add("dm_config");
//        groupList.add("dm_config_warn");
        groupList.add("dm_project");
//        groupList.add("dm_props_project_r");
//        groupList.add("dm_props_hv");
//        groupList.add("dm_props");
        groupMap.put(null, groupList);

        tablePrefix = "dm_";

        MakerInsertSelect.make(
                "com.steve",
                "testPackageName",
                groupMap, tablePrefix);

    }
}
