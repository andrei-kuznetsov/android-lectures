package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ArticleListFragmentTest {

    public static abstract class ExContext extends Context implements ArticleListFragment.ArticleListener {

    }


    @Test
    public void onListItemClickInvokesListener() {
        // Given
        ArticleListFragment fragment = new ArticleListFragment();
        ArticleListFragment.ArticleListener listener = Mockito.mock(ArticleListFragment.ArticleListener.class);
        fragment.setListener(listener);

        // Action
        fragment.onListItemClick(null, null, 0, 0);

        // Assertion
        Mockito.verify(listener).onArticle("Item1");
    }

    @Test
    public void listenerSetInOnAttach() {
        // Given
        ArticleListFragment fragment = new ArticleListFragment();
        ExContext listener = Mockito.mock(ExContext.class);

        // Action
        fragment.onAttach(listener);

        // Assertion
        Assert.assertEquals(listener, fragment.getListener());
    }

    @Test
    public void testWait() throws InterruptedException {
        Object o = new Object();
//        synchronized (o) {
//            o.wait(1000);
//        }
        Thread.sleep(1000);
    }
}