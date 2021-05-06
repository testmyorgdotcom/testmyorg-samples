package org.testmy.screenplay.act;

import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testmy.data.matchers.Matchers.account;
import static org.testmy.data.matchers.Matchers.hasName;
import static org.testmy.data.matchers.Matchers.ofShape;

import java.util.List;
import java.util.function.Function;

import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.testmy.data.TestDataManager;

import net.serenitybdd.screenplay.Actor;

@RunWith(MockitoJUnitRunner.class)
public class CreateDataTest {
    @Mock
    private Function<SObject[], SaveResult[]> storeFunction;
    private TestDataManager testDataManager = new TestDataManager();

    @Before
    public void before() {
        final SaveResult saveResult = new SaveResult();
        saveResult.setId("003XYZ...");
        when(storeFunction.apply(any())).thenReturn(new SaveResult[] {
                saveResult
        });
    }

    @Test
    public void testPerform() {
        final Actor mike = Actor.named("Mike");

        when(mike).attemptsTo(CreateData.record(ofShape(account(), hasName("accountName")))
                .withStoreFunction(storeFunction).withTestDataManager(testDataManager));

        final List<SObject> testData = testDataManager.getData();
        assertThat(testData, hasSize(1));

        verify(storeFunction).apply(any());
    }
}
