package com.cubivue.app.models.job;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.log.RealmLog;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(minSdk = 28)
@PowerMockIgnore({"org.robolectric.*", "android.*"})
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest({Realm.class, RealmQuery.class, RealmLog.class, RealmResults.class, JobRepositoryImplTest.class})
public class JobDBTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    //Mocked Objects
    private Realm mockRealm;
    private JobRepositoryImplTest repository;

    @Before
    public void setup() {

        mockStatic(RealmLog.class);
        mockStatic(Realm.class);
        mockStatic(JobRepositoryImplTest.class);

        Realm mockRealm = mock(Realm.class);

        when(Realm.getDefaultInstance()).thenReturn(mockRealm);

        this.mockRealm = mockRealm;

        repository = mock(JobRepositoryImplTest.class);
        repository.setDb(mockRealm);
    }

    @Test
    public void shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), is(mockRealm));
    }

    @Test
    public void repositoryIsInitialized() {
        assertNotNull(repository);
    }

    @Test
    public void shouldBeAbleToMockRealmMethods() {
        when(mockRealm.isAutoRefresh()).thenReturn(true);
        assertThat(mockRealm.isAutoRefresh(), is(true));

        when(mockRealm.isAutoRefresh()).thenReturn(false);
        assertThat(mockRealm.isAutoRefresh(), is(false));
    }

    @Test
    public void shouldBeAbleToCreateARealmObject() {
        JobTest job = new JobTest();
        job.setJobId("1");

        when(mockRealm.createObject(JobTest.class)).thenReturn(job);

        JobTest output = mockRealm.createObject(JobTest.class);

        assertThat(output, is(job));
    }


    @Test
    public void shouldVerifyThatJobWasCreated() {

        doCallRealMethod().when(mockRealm).executeTransaction(Mockito.any(Realm.Transaction.class));

        JobTest job = new JobTest();
        job.setJobId("134");

        when(mockRealm.createObject(JobTest.class)).thenReturn(job);

        JobTest result = repository.findJobById("134");

        assertNotNull(result);

        assertEquals(result.getJobId(), "134");

        // Verify that Realm#createObject was called only once
        //verify(mockRealm, times(1)).createObject(JobTest.class); // Verify that a JobDBTest was in fact created.

        // Verify that JobDBTest#setName() is called only once
        verify(repository,times(1)).findJobById("134");

        verify(repository.findJobById("134"),times(1)).getJobId();


        // Verify that the Realm was closed only once.
        //verify(mockRealm, times(1)).close();
    }

    /**
     * Have to verify the [Realm.executeTransaction] call in a different
     * test because of a problem with Powermock: https://github.com/jayway/powermock/issues/649
     */
    @Test
    public void shouldVerifyThatTransactionWasExecuted() {

        JobTest job = new JobTest();
        job.setJobId("1");

        repository.createJob(job);

        // Verify that the begin transaction was called only once
        verify(mockRealm, times(1)).executeTransaction(Mockito.any(Realm.Transaction.class));

        // Verify that the Realm was closed only once.
        verify(mockRealm, times(1)).close();
    }

    @Test
    public void findAll() {
        JobTest job1 = mock(JobTest.class);
        job1.setJobId("1");

        JobTest job2 = mock(JobTest.class);
        job2.setJobId("2");

        ArrayList<JobTest> list = new ArrayList<JobTest>();
        list.add(job1);
        list.add(job2);

        RealmResults<JobTest> results = mockRealmResults();

        RealmQuery<JobTest> query = mockRealmQuery();

        when(mockRealm.where(JobTest.class)).thenReturn(query);
        when(query.findAll()).thenReturn(results);
        when(results.iterator()).thenReturn(list.iterator());
        when(results.size()).thenReturn(list.size());

        List<JobTest> result = mockRealm.where(JobTest.class).findAll();

        assertThat(Realm.getDefaultInstance(), is(mockRealm));
        verify(mockRealm.where(JobTest.class), times(1)).findAll();
        assertEquals("invalid size", 2, result.size());
    }

    @After
    public void closeRealm() {
        validateMockitoUsage();
        mockRealm.close();
    }

    @SuppressWarnings("unchecked")
    private <T extends RealmObject> RealmQuery<T> mockRealmQuery() {
        return mock(RealmQuery.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends RealmObject> RealmResults<T> mockRealmResults() {
        return mock(RealmResults.class);
    }
}