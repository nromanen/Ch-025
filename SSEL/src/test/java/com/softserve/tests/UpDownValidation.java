/**
 * 
 */
package com.softserve.tests;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.softserve.entity.Topic;
import com.softserve.entity.User;
import com.softserve.service.TopicService;

/**
 * @author hash
 *
 */
@RunWith(Parameterized.class)
public class UpDownValidation {
	
	private Topic topic;
	private TopicService topicService;
	
	@Before
	public void setUp() {
		topic = mock(Topic.class);
		topicService = mock(TopicService.class);
	}
	
	@Parameters
    public static Collection<Object[]> data() {
    	Object[][] data = new Object[][] {
    			{
    				"Fasdsa545&", "Fasdsa545&"
    			},
    			{
    				"fdfdsf54Fdfsdf**", "fdfdsf54Fdfsdf**"
    			},
    			{
    				"FFDFDfdfd11*FDfd", "FFDFDfdfd11*FDfd"
    			}
    	};
    	return Arrays.asList(data);
    }
    
    @Test
	public void testHasErrors() {
		
		when(topic.getOrder()).thenReturn(1);
		doThrow(new Exception()).doNothing().when(topicService).updateTopic(new Topic());
		
		

       // assertFalse(errors.hasErrors());
	}

}
