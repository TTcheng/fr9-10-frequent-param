package com.fr.startup;


//import com.fr.third.springframework.web.WebApplicationInitializer;
import com.fr.workspace.WorkContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * <p>
 * 覆盖帆软启动器，防止启动帆软
 * </p>
 *
 * @author Jesse
 * @see WorkContext
 * @see com.fr.startup.web.ServerWorkspace
 * @see com.fr.workspace.engine.FineWorkspace
 */
public class FineWebApplicationInitializer  {

    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}
