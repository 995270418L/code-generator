package com.steve.param;

import com.datamesh.mysql.common.QueryParam;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by steve
 * blog: https://www.jianshu.com/u/c8df39415ca7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectQueryParam implements QueryParam {

    private Integer id;
    private Collection<Integer> idCollection;
    @Override
    public boolean hasData() {
        return false;
    }

}