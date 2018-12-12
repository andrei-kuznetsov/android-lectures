package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.FragmentManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ArticleRobolectricListFragmentTest {

    @Test
    public void listenerSetInOnAttach() {
        ArticleListActivity activity = Robolectric.buildActivity(ArticleListActivity.class)
                .create()
//                .start()
//                .resume()
                .get();
        FragmentManager fm = activity.getFragmentManager();
//        ArticleListFragment listFragment = (ArticleListFragment) fm.findFragmentById(R.id.list_fragment);
//        Assert.assertEquals(activity, listFragment.getListener());
//        Assert.assertTrue(activity == listFragment.getListener());
//        assertThat(listFragment.getListener(), sameInstance(activity));
//        ArticleListActivity spy = Mockito.spy(activity);
//        listFragment.onListItemClick(null, null, 0, 0);
//        Mockito.verify(spy).onArticle("Item1");
    }
}