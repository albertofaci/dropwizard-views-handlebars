package com.albertofaci.dropwizard.handlebars;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Timer;

/**
 * Clearly based on Alexander Reelsen's JsView
 */
public abstract class HandlebarsView {
    private final String templateName;
    private final Timer renderingTimer;

    public HandlebarsView(String templateName) {
        this.templateName = resolveName(templateName);
        renderingTimer = Metrics.newTimer(getClass(), "handlebars-rendering");
    }

    private String resolveName(String templateName) {
    	if(templateName == null){
    		throw new IllegalArgumentException("templateName must not be null");
    	}
        if (templateName.startsWith("/")) {
            return templateName;
        }
        final String packagePath = getClass().getPackage().getName().replace('.', '/');
        return String.format("/%s/%s", packagePath, templateName);
    }

    @JsonIgnore
    public String getTemplateName() {
        return templateName;
    }

    @JsonIgnore
    public Timer getRenderingTimer() {
        return renderingTimer;
    }
    
}