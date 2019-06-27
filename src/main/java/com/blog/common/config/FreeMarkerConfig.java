package com.blog.common.config;

import javax.annotation.PostConstruct;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import cn.org.rapid_framework.freemarker.directive.SuperDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;


/**
 * Freemarket配置
 * 主要实现模板继承以及重载
 *
 * @author zx
 * @date 2019/1/29
 */
@Configuration
public class FreeMarkerConfig {
	
	@Autowired
	protected freemarker.template.Configuration configuration;

	@PostConstruct
	public void setSharedVariable() {
		configuration.setDateFormat("yyyy-MM-dd");
		configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		configuration.setSharedVariable("block", new BlockDirective());
		configuration.setSharedVariable("override", new OverrideDirective());
		configuration.setSharedVariable("extends", new ExtendsDirective());
		configuration.setSharedVariable("super", new SuperDirective());
	}

}
