package com.webside.enums;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestEnum {

	@Test
	public void testEmailDescription() {
		assertTrue(EmailDescription.ADD_EMAIL.getSubject() == "新建账户通知");
	}
	
	@Test
	public void testExportType()
	{
		System.out.println(ExportType.EXCEL.name());
	}

}
