package com.kireate.task;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kireate.task.response.TaskListResponse;


@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskUnitTest {


    @Mock
    TaskListResponse taskListResponse;

    @Before
    public void runBeforeTestMethod() {
        taskListResponse = mock(TaskListResponse.class)  ;
    }

    @After
    public void runAfterTestMethod() {
        taskListResponse = null  ;
    }


    @Test
    public void testTaskTitleShouldBeAString() {

        when(taskListResponse.getTitle()).thenReturn("This is Title");

        assertEquals(taskListResponse.getTitle(),"This is Title");
    }

    @Test
    public void testTaskIdShouldBeNotAString() {

        when(taskListResponse.getId()).thenReturn(23);

        assertNotSame(taskListResponse.getId(),"This is Title");
    }

    @Test
    public void testTaskShortDescriptionShouldBeAnyString() {
        taskListResponse.setShortDescription("this is short description");

        verify(taskListResponse).setShortDescription(anyString());

    }
}
