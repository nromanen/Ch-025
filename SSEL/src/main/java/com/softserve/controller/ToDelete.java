package com.softserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softserve.entity.Log;

public class ToDelete {
	
	public static List<Log> getFakeList() {
		Log log1 = new Log();
		Log log2 = new Log();
		Log log3 = new Log();
		Log log4 = new Log();
		Log log5 = new Log();
		Log log6 = new Log();
		log1.setEventDate(new Date());
		log1.setLevel("WARN");
		log1.setLogger("org.springframework.security.config.http.FilterInvocationSecurityMetadataSourceParser");
		log1.setMessage("No supported matching language for locale \"ua\". Using file:/F:/Java/Workspace2/.metadata/.plugins/org.eclipse.wst.server.core/"
				+ "tmp0/wtpwebapps/SSEL/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571");
		log1.setException("java.lang.IllegalStateException: LifecycleProcessor not initialized - call 'refresh' before invoking lifecycle methods via the "
				+ "context: Root WebApplicationContext: startup date [Mon Oct 27 16:40:46 EET 2014]; root of context hierarchy"
				+ "	at org.springframework.context.support.AbstractApplicationContext.getLifecycleProcessor(AbstractApplicationContext.java:359)at org.springfamework."
				+ "context.support.AbstractApplicationContext.doClose(AbstractApplicationContext.java:888)"
				+ "at org.springframework.context.support.AbstractApplicationContext.close(AbstractApplicationContext.java:841)"
				+ "at org.springframework.web.context.ContextLoader.closeWebApplicationContext(ContextLoader.java:579)"
				+ "at org.springframework.web.context.ContextLoaderListener.contextDestroyed(ContextLoaderListener.java:115)"
				+ "at org.apache.catalina.core.StandardContext.listenerStop(StandardContext.java:4792)"
				+ "at org.apache.catalina.core.StandardContext.stopInternal(StandardContext.java:5392)"
				+ "at org.apache.catalina.util.LifecycleBase.stop(LifecycleBase.java:232)"
				+ "at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:160)"
				+ "at org.apache.catalina.core.StandardContext.reload(StandardContext.java:3775)"
				+ "at org.apache.catalina.loader.WebappLoader.backgroundProcess(WebappLoader.java:293)"
				+ "at org.apache.catalina.core.StandardContext.backgroundProcess(StandardContext.java:5530)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.processChildren(ContainerBase.java:1377)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.processChildren(ContainerBase.java:1381)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.processChildren(ContainerBase.java:1381)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.run(ContainerBase.java:1349)"
				+ "at java.lang.Thread.run(Thread.java:745)");
		log2.setEventDate(new Date());
		log2.setLevel("WARN");
		log2.setLogger("org.springframework.web.context.ContextLoader");
		log2.setMessage("Context initialization failed");
		log2.setException("java.lang.IllegalStateException: LifecycleProcessor not initialized - call 'refresh' before invoking lifecycle methods via the "
				+ "context: Root WebApplicationContext: startup date [Mon Oct 27 16:40:46 EET 2014]; root of context hierarchy"
				+ "	at org.springframework.context.support.AbstractApplicationContext.getLifecycleProcessor(AbstractApplicationContext.java:359)at org.springfamework."
				+ "context.support.AbstractApplicationContext.doClose(AbstractApplicationContext.java:888)"
				+ "at org.springframework.context.support.AbstractApplicationContext.close(AbstractApplicationContext.java:841)"
				+ "at org.springframework.web.context.ContextLoader.closeWebApplicationContext(ContextLoader.java:579)"
				+ "at org.springframework.web.context.ContextLoaderListener.contextDestroyed(ContextLoaderListener.java:115)"
				+ "at org.apache.catalina.core.StandardContext.listenerStop(StandardContext.java:4792)"
				+ "at org.apache.catalina.core.StandardContext.stopInternal(StandardContext.java:5392)"
				+ "at org.apache.catalina.util.LifecycleBase.stop(LifecycleBase.java:232)"
				+ "at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:160)"
				+ "at org.apache.catalina.core.StandardContext.reload(StandardContext.java:3775)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.run(ContainerBase.java:1349)"
				+ "at java.lang.Thread.run(Thread.java:745)");
		log3.setEventDate(new Date());
		log3.setLevel("ERROR");
		log3.setLogger("org.springframework.web.servlet.PageNotFound");
		log3.setMessage("No mapping found for HTTP request with URI [/SSEL/deleteCategories] in DispatcherServlet with name 'appServlet'");
		log3.setException("");
		log4.setEventDate(new Date());
		log4.setLevel("WARN");
		log4.setLogger("org.springframework.web.context.support.XmlWebApplicationContext");
		log4.setMessage("Exception thrown from LifecycleProcessor on context close");
		log5.setEventDate(new Date());
		log5.setLevel("WARN");
		log5.setLogger("org.springframework.security.config.http.FilterInvocationSecurityMetadataSourceParser");
		log5.setMessage("No supported matching language for locale \"ua\". Using file:/F:/Java/Workspace2/.metadata/.plugins/org.eclipse.wst.server.core/"
				+ "tmp0/wtpwebapps/SSEL/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571");
		log5.setException("java.lang.IllegalStateException: LifecycleProcessor not initialized - call 'refresh' before invoking lifecycle methods via the "
				+ "context: Root WebApplicationContext: startup date [Mon Oct 27 16:40:46 EET 2014]; root of context hierarchy"
				+ "	at org.springframework.context.support.AbstractApplicationContext.getLifecycleProcessor(AbstractApplicationContext.java:359)at org.springfamework."
				+ "context.support.AbstractApplicationContext.doClose(AbstractApplicationContext.java:888)"
				+ "at org.springframework.context.support.AbstractApplicationContext.close(AbstractApplicationContext.java:841)"
				+ "at org.springframework.web.context.ContextLoader.closeWebApplicationContext(ContextLoader.java:579)"
				+ "at org.apache.catalina.core.StandardContext.reload(StandardContext.java:3775)"
				+ "at org.apache.catalina.loader.WebappLoader.backgroundProcess(WebappLoader.java:293)"
				+ "at org.apache.catalina.core.StandardContext.backgroundProcess(StandardContext.java:5530)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.processChildren(ContainerBase.java:1377)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.processChildren(ContainerBase.java:1381)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.processChildren(ContainerBase.java:1381)"
				+ "at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.run(ContainerBase.java:1349)"
				+ "at java.lang.Thread.run(Thread.java:745)");
		log6.setEventDate(new Date());
		log6.setLevel("ERROR");
		log6.setLogger("org.springframework.web.context.ContextLoader");
		log6.setMessage("Context initialization failed");
		log6.setException("java.lang.IllegalStateException: LifecycleProcessor...");
		
		List<Log> logList = new ArrayList<Log>();
		logList.add(log1);
		logList.add(log2);
		logList.add(log3);
		logList.add(log4);
		logList.add(log5);
		logList.add(log6);
		return logList;
	}

}
