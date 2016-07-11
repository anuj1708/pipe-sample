package sample.android.bignerdranch.com.sample;

import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.android.core.ReadFilter;
import org.jboss.aerogear.android.pipe.http.HttpProvider;
import org.jboss.aerogear.android.pipe.http.HttpProviderFactory;
import org.jboss.aerogear.android.pipe.rest.RestAdapter;
import org.junit.Assert;
import org.junit.Test;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {



    @Test
    public void testCoder() throws Exception {


        URL url = new URL("https://github.com/");
        final CountDownLatch latch = new CountDownLatch(1);

        HttpProviderFactory factory = mock(HttpProviderFactory.class);
        when(factory.get(anyObject())).thenReturn(mock(HttpProvider.class));

        RestAdapter<Data> adapter = new RestAdapter<Data>(Data.class, url);
        Object restRunner = UnitTestUtils.getPrivateField(adapter, "restRunner");
        UnitTestUtils.setPrivateField(restRunner, "httpProviderFactory", factory);

        ReadFilter filter = new ReadFilter();
        filter.setLinkUri(URI.create("rail/trails"));

        adapter.read(filter, new Callback<List<Data>>() {
            @Override
            public void onSuccess(List<Data> data) {
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                latch.countDown();
            }
        });
        latch.await(500, TimeUnit.MILLISECONDS);

    }

}