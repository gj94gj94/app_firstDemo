package tw.com.flag.a1031_first_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by User on 2017/10/28.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public PageAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1_left tab1_left = new Tab1_left();
                return tab1_left;
            case 1:
                Tab2_mid tab2_mid = new Tab2_mid();
                return tab2_mid;
            case 2:
                Tab3_right tab3_right = new Tab3_right();
                return tab3_right;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
