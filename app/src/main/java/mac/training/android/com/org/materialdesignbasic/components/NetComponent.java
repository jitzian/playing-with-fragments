package mac.training.android.com.org.materialdesignbasic.components;

import dagger.Component;
import mac.training.android.com.org.materialdesignbasic.fragments.BodyFragment;
import mac.training.android.com.org.materialdesignbasic.modules.NetModule;

/**
 * Created by raian on 12/14/16.
 */

@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(BodyFragment bodyFragment);

}
