package com.quantrix.interview.server.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * This is utility code and not relevant to the assessment.
 * Candidates can ignore it.
 */
public class CORSFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig)
    {
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        chain.doFilter(request, response);

        /*
         * For this simple demo app, we will disregard CORS to reduce complexity.
         */
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
    }
}
