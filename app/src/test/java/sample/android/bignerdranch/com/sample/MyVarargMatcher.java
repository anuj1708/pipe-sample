package sample.android.bignerdranch.com.sample;

import java.util.Arrays;

import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.VarargMatcher;

/**
 * Created by anuj on 24/6/16.
 */
public class MyVarargMatcher extends ArgumentMatcher<Object[]> implements VarargMatcher{
    private final Object[] matchedObjects;
    public MyVarargMatcher(Object[] matchedObjects) {
        this.matchedObjects = matchedObjects;
    }

    /**
     * Returns {@code true} if the matcher accepts the given argument.
     *
     * @return true if the argument matches the initialized constructor argument
     */
    @Override
    public boolean matches(Object argument) {
        // TODO(krishnanand): Generic types.
        @SuppressWarnings("unchecked")
        Object [] objects = (Object []) argument;
        return Arrays.deepEquals(this.matchedObjects, objects);
    }
}