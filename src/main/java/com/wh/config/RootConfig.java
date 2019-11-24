package com.wh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//Spring��ɨ��Controller��������
@ComponentScan(value="com.wh",excludeFilters = {
		@Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {

}
