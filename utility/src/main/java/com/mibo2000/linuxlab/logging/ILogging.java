package com.mibo2000.linuxlab.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : MibO2000
 */
public interface ILogging {
    void logRequest(HttpServletRequest httpServletRequest, Object body);

    void logResponse(HttpServletRequest httpServletRequest,
                     HttpServletResponse httpServletResponse,
                     Object body);
}
