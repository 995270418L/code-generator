package com.steve.service.impl;

import com.datamesh.mysql.common.impl.BaseServiceImpl;
import com.steve.entity.ProjectEntity;
import com.steve.mapper.ProjectEntityMapper;
import com.steve.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * create by steve
 * blog: https://www.jianshu.com/u/c8df39415ca7
 */
@Slf4j
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectEntity, ProjectEntityMapper> implements ProjectService {

}