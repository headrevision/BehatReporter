package headrevision.BehatReporter.report;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.google.common.base.CaseFormat;

public class ItemsAdapterFactoryTest extends TestCase {

	public void testFormattingItemTypeToLowerUnderscore() {
		ItemsAdapterFactory sut = new ItemsAdapterFactoryImpl();

		Assert.assertEquals("foo_bar", sut.getItemType(CaseFormat.LOWER_UNDERSCORE));
	}

}