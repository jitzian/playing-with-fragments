package mac.training.android.com.org.materialdesignbasic.rest;

import mac.training.android.com.org.materialdesignbasic.model.ResultPixabay;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by raian on 12/15/16.
 */

public interface RestService {
    //https://pixabay.com/api/?key=
    // 4015571-2ff16680f01f66ea8bb97c14b
    // &q=yellow+flowers
    // &image_type=photo
    @GET("api/?")
    Observable<ResultPixabay>getImages(@Query("key") String key,
                                       @Query("q") String q,
                                       @Query("image_type") String image_type);
}
