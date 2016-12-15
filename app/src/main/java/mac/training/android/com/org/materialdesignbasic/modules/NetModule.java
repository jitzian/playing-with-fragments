package mac.training.android.com.org.materialdesignbasic.modules;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raian on 12/14/16.
 */

@Module
public class NetModule {
    private String baseURL;

    public NetModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseURL)
                .build();

    }

}
