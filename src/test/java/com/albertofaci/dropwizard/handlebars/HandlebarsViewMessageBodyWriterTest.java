package com.albertofaci.dropwizard.handlebars;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.ws.rs.WebApplicationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HandlebarsViewMessageBodyWriterTest {
	
	private static final String VIEW_PATH = "/views/ExampleView.hbs";
	private static final String PROPERTY_VALUE = "foobar";
	
	private ByteArrayOutputStream outputStream;
	
	@Before
	public void setup() {
		outputStream = new ByteArrayOutputStream();
	}
	
	
	@Test
	public void canWriteTemplate() throws WebApplicationException, IOException {
		ExampleView exampleView = new ExampleView(PROPERTY_VALUE);
		HandlebarsViewMessageBodyWriter writer = new HandlebarsViewMessageBodyWriter();
		
		writer.writeTo(exampleView, ExampleView.class, ExampleView.class, new Annotation[]{}, null, null, outputStream);
		
		String actualResponse = new String(outputStream.toByteArray(), "UTF-8");
		Assert.assertEquals("Hello foobar. Bye.", actualResponse);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private class ExampleView extends HandlebarsView {

		private String property;
		
		public ExampleView(String property) {
			super(VIEW_PATH);
			setProperty(property);
		}
		
		public String getProperty() {
			return property;
		}
		
		public void setProperty(String property) {
			this.property = property;
		}
		
	}

}
