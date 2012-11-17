package com.albertofaci.dropwizard.handlebars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.albertofaci.dropwizard.handlebars.ContextlessView;
import com.albertofaci.dropwizard.handlebars.HandlebarsView;

public class HandlebarsViewTest {
	
	private static final String ABSOLUTE_VIEW = "/views/admin/UserView.hbs";
	private static final String RELATIVE_VIEW = "blah/BlahBlah";
	
	
	@Test
	public void assumesAbsoluteClasspathIfStartWithSlash() {
		HandlebarsView handlebarsView = new ContextlessView(ABSOLUTE_VIEW);
		assertEquals(ABSOLUTE_VIEW, handlebarsView.getTemplateName());
	}
	
	@Test
	public void assumesRelativePackagePathIfNotStartWithSlash() {
		HandlebarsView handlebarsView = new ContextlessView(RELATIVE_VIEW);
		assertEquals("/com/albertofaci/dropwizard/handlebars/"+RELATIVE_VIEW, handlebarsView.getTemplateName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void  failsIfNullTemplate() {
		new ContextlessView(null);
	}
	
}
