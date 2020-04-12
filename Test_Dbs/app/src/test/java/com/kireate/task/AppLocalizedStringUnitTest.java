package com.kireate.task;



import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AppLocalizedStringUnitTest {

    private static final String FAKE_STRING = "Test_Dbs";

    @Mock
    Context mockContext;

    @Test
    public void readAppNameFromContext_LocalizedString() {
        when(mockContext.getString(R.string.app_name))
                .thenReturn(FAKE_STRING);
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mockContext);

        String result = myObjectUnderTest.getAppAsString();

        assertEquals(result,FAKE_STRING);
    }




}
