package hu.supercluster.overpassapiquery.app.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

@EBean
public abstract class AbstractFragmentHandler {
    private FragmentActivity fragmentActivity;

    @AfterInject
    protected void setFragmentActivity() {
        fragmentActivity = getFragmentActivity();
    }

    protected abstract FragmentActivity getFragmentActivity();

    public <T extends Fragment> T getFragment(Class<? extends T> typeToken, T defaultValue) {
        T fragment = (T) fragmentActivity.getSupportFragmentManager().findFragmentByTag(typeToken.toString());

        return (fragment != null) ? fragment : defaultValue;
    }

    public void showFragment(Fragment fragment, int containerResourceId) {
        showFragment(fragment, containerResourceId, false);
    }

    public void showFragment(Fragment fragment, int containerResourceId, boolean addToBackStack) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerResourceId, fragment, fragment.getClass().toString());

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }
}
