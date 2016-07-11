/*
 * Copyright 2015-2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.android.bignerdranch.com.sample;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;


public final class Fragments {
    private Fragments() {
    }

    public static final class Arguments {
        private Arguments() {
        }

        public static final String ALERT = "alert";
        public static final String ENVIRONMENT = "environment";
        public static final String METRIC = "metric";
        public static final String RESOURCE = "resource";
    }

    public static final class Builder {
        private Builder() {
        }

        @NonNull
        public static Fragment buildAlertsFragment() {
            Fragment fragment = new AlertsFragment();
            return fragment;
        }
    }

    public static final class Operator {
        private final FragmentManager fragmentManager;

        @NonNull
        public static Operator of(@NonNull FragmentActivity activity) {
            return new Operator(activity);
        }

        private Operator(FragmentActivity activity) {
            this.fragmentManager = activity.getSupportFragmentManager();
        }

        public void set(@IdRes int fragmentContainerId, @NonNull Fragment fragment) {
            if (fragmentManager.findFragmentById(fragmentContainerId) != null) {
                return;
            }

            fragmentManager
                    .beginTransaction()
                    .add(fragmentContainerId, fragment)
                    .commit();
        }

        public void reset(@IdRes int fragmentContainerId, @NonNull Fragment fragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(fragmentContainerId, fragment)
                    .commit();
        }
    }


}

