package cn.hutool.core.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EscapeUtilTest {

	@Test
	public void escapeHtml4Test() {
		String escapeHtml4 = EscapeUtil.escapeHtml4("<a>你好</a>");
		assertEquals("&lt;a&gt;你好&lt;/a&gt;", escapeHtml4);

		String result = EscapeUtil.unescapeHtml4("&#25391;&#33633;&#22120;&#31867;&#22411;");
		assertEquals("振荡器类型", result);

		String escape = EscapeUtil.escapeHtml4("*@-_+./(123你好)");
		assertEquals("*@-_+./(123你好)", escape);
	}

	@Test
	public void escapeTest(){
		String str = "*@-_+./(123你好)ABCabc";
		String escape = EscapeUtil.escape(str);
		assertEquals("*@-_+./%28123%u4f60%u597d%29ABCabc", escape);

		String unescape = EscapeUtil.unescape(escape);
		assertEquals(str, unescape);
	}

	@Test
	public void escapeAllTest(){
		String str = "*@-_+./(123你好)ABCabc";

		String escape = EscapeUtil.escapeAll(str);
		assertEquals("%2a%40%2d%5f%2b%2e%2f%28%31%32%33%u4f60%u597d%29%41%42%43%61%62%63", escape);

		String unescape = EscapeUtil.unescape(escape);
		assertEquals(str, unescape);
	}

	/**
	 * https://gitee.com/dromara/hutool/issues/I49JU8
	 */
	@Test
	public void escapeAllTest2(){
		String str = "٩";

		String escape = EscapeUtil.escapeAll(str);
		assertEquals("%u0669", escape);

		String unescape = EscapeUtil.unescape(escape);
		assertEquals(str, unescape);
	}

	@Test
	public void escapeSingleQuotesTest(){
		// 单引号不做转义
		String str = "'some text with single quotes'";
		final String s = EscapeUtil.escapeHtml4(str);
		assertEquals("'some text with single quotes'", s);
	}

	@Test
	public void unescapeSingleQuotesTest(){
		String str = "&apos;some text with single quotes&apos;";
		final String s = EscapeUtil.unescapeHtml4(str);
		assertEquals("'some text with single quotes'", s);
	}
}
