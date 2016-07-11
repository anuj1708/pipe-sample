package sample.android.bignerdranch.com.sample;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.android.core.ReadFilter;
import org.jboss.aerogear.android.pipe.LoaderPipe;
import org.jboss.aerogear.android.pipe.PipeConfiguration;
import org.jboss.aerogear.android.pipe.PipeManager;
import org.jboss.aerogear.android.pipe.callback.AbstractFragmentCallback;
import org.jboss.aerogear.android.pipe.callback.AbstractSupportFragmentCallback;
import org.jboss.aerogear.android.pipe.rest.RestfulPipeConfiguration;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Alerts fragment.
 * <p/>
 * Displays alerts as a list with menus allowing some alert-related actions, such as acknowledgement and resolving.
 */
public final class AlertsFragment extends Fragment {


    URL mURL;
    @BindView(R.id.button_QR)
    Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        try {
            mURL =new URL("http://jsonplaceholder.typicode.com/posts");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        setUpBindings();

        setUpMenu();

        configurePipe("hello", mURL, Persona.class);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    readPipe("hello", new URI(""), new PersonasCallback());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });



    }


    private void setUpBindings() {
        ButterKnife.bind(this, getView());
    }


    private void setUpMenu() {
        setHasOptionsMenu(true);
    }


    @SuppressWarnings("unchecked")
    private <T> void readPipe(String pipeName, URI uri, Callback<List<T>> callback) {
        getPipe(pipeName).read(getFilter(uri), callback);
    }

    @SuppressWarnings("unchecked")
    private <T> void savePipe(String pipeName, Object object, Callback<List<T>> callback) {
        getPipe(pipeName).save(object, callback);
    }

    private LoaderPipe getPipe(String pipeName) {

            return PipeManager.getPipe(pipeName, this, this.getActivity().getApplicationContext());

    }

    private ReadFilter getFilter(URI uri) {
        ReadFilter filter = new ReadFilter();

        filter.setLinkUri(uri);

        return filter;
    }

    private <T> void configurePipe(String pipeName, URL pipeUrl, Class<T> pipeClass) {
        PipeConfiguration pipeConfiguration = PipeManager.config(pipeName, RestfulPipeConfiguration.class)
                .withUrl(pipeUrl);
        pipeConfiguration.forClass(pipeClass);
    }


    private static final class PersonasCallback extends AbstractSupportFragmentCallback<List<Persona>> {
        @Override
        public void onSuccess(List<Persona> personas) {
            if (personas.isEmpty()) {
                onFailure(new RuntimeException("Personas list is empty, this should not happen."));
                return;
            }

            Toast.makeText(getSupportFragment().getActivity(), personas.size() + "items received", Toast
                    .LENGTH_SHORT)
                    .show();

        }

        @Override
        public void onFailure(Exception e) {
            Log.d("gotta error", "error");
        }
    }




}

