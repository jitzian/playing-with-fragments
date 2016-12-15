package mac.training.android.com.org.materialdesignbasic.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import mac.training.android.com.org.materialdesignbasic.R;
import mac.training.android.com.org.materialdesignbasic.adapter.RVPixabayAdapter;
import mac.training.android.com.org.materialdesignbasic.components.DaggerNetComponent;
import mac.training.android.com.org.materialdesignbasic.model.ResultPixabay;
import mac.training.android.com.org.materialdesignbasic.modules.NetModule;
import mac.training.android.com.org.materialdesignbasic.rest.RestService;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BodyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 *
 * This fragment will display photos from
 * https://pixabay.com/api/docs/
 *
 *
 */
public class BodyFragment extends Fragment {
    private static final String TAG = BodyFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    Retrofit retrofit;

    //RecyclerView
    private RecyclerView mPhotoRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RVPixabayAdapter adapter;


    public BodyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BodyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BodyFragment newInstance(String param1, String param2) {
        BodyFragment fragment = new BodyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_body, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        DaggerNetComponent.builder()
                .netModule(new NetModule("https://pixabay.com/"))
                .build()
                .inject(this);

        RestService restService = retrofit.create(RestService.class);

        mPhotoRecyclerView = (RecyclerView) view.findViewById(R.id.mPhotoRecyclerView);
        mPhotoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        mPhotoRecyclerView.setLayoutManager(layoutManager);

        Observable<ResultPixabay> observablePixabay = restService.getImages("4015571-2ff16680f01f66ea8bb97c14b", "yellow+flowers", "image_type");

        observablePixabay.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultPixabay>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError::" + e.getMessage());
                    }

                    @Override
                    public void onNext(ResultPixabay resultPixabay) {
                        Log.d(TAG, "onNext::" + resultPixabay.getHits().size());
                        adapter = new RVPixabayAdapter(getContext(), resultPixabay.getHits());
                        mPhotoRecyclerView.setAdapter(adapter);
                    }
                });

        Log.d(TAG, " -- onViewCreated::" + retrofit);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
