package com.steve.entity;

import java.util.Date;
import com.datamesh.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by steve
 * blog: https://www.jianshu.com/u/c8df39415ca7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity extends BaseEntity<ProjectEntity> {

    private Integer id;
    private Integer pmId;
    private String name;
    private String description;
    private String pkgName;
    private String serviceName;
    private String version;
    private Integer sysProject;
    private String cpuArch;
    private Integer packageType;
    private String system;
    private String licensefile;
    private String filename;
    private String filesize;
    private Integer port;
    private String dependency;
    private Date insertTime;
    private Date updateTime;

}
