package tw.com.flag.a1031_first_demo;

import android.app.Activity;
import android.app.UiAutomation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2_mid.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2_mid#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2_mid extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;




    // How to import other class from same package
    /*public void main(String[] args){
        mainPage main = new mainPage();


    }*/


    public void readEdit(View v){
        TextView textView3 = (TextView) view.findViewById(R.id.item1);

    }


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab2_mid() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2_mid.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2_mid newInstance(String param1, String param2) {
        Tab2_mid fragment = new Tab2_mid();
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
    // Main activity
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab2_mid, container, false);



        Button button = (Button) view.findViewById(R.id.read);
        Button cb = (Button) view.findViewById(R.id.clear);


        // Reset DB
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPage.c=mainPage.db.rawQuery("SELECT * FROM "+mainPage.tb_name,null);
                if(mainPage.c.moveToFirst()){
                    do {
                        // Get all item virtue
                        String id = mainPage.c.getString(0);
                        String itemID = mainPage.c.getString(1).toString();
                        String itemText = mainPage.c.getString(2).toString();
                        String itemState = mainPage.c.getString(3).toString();

                        // Reset DB
                        ContentValues cv = new ContentValues();
                        cv.put("item",itemID.toString());
                        cv.put("itemName",itemText.toString());
                        cv.put("state","invisible");
                        itemState = "invisible";
                        mainPage.db.update(mainPage.tb_name,cv,"_id="+id,null);


                        // Clear TextView
                        if(itemState.equals("invisible")) {
                            int resID = getResources().getIdentifier(itemID, "id", "tw.com.flag.a1031_first_demo");
                            TextView tar = (TextView) view.findViewById(resID);
                            tar.setVisibility(View.INVISIBLE);
                            tar.setText("123");
                        }
                        /*
                        int resID = getResources().getIdentifier(itemID, "id", "tw.com.flag.a1031_first_demo");
                        TextView tar = (TextView) view.findViewById(resID);
                        tar.setVisibility(View.INVISIBLE);*/

                    }while (mainPage.c.moveToNext());
                }
            }
        });


        // Search the DB
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txvgg = (TextView) view.findViewById(R.id.item5);
                txvgg.setVisibility(View.INVISIBLE);
                EditText edit = (EditText) view.findViewById(R.id.edit);
                String inp = edit.getText().toString();
                edit.setText("");
                if(inp.equals("志剛小雞雞")){
                    txvgg.setVisibility(View.VISIBLE);
                }
                mainPage.c=mainPage.db.rawQuery("SELECT * FROM "+mainPage.tb_name,null);
                if(mainPage.c.moveToFirst()){
                    do{
                        // Read editText and if find than change the state
                        String id = mainPage.c.getString(0);
                        String itemID = mainPage.c.getString(1).toString();
                        String itemText = mainPage.c.getString(2).toString();
                        String itemState = mainPage.c.getString(3).toString();
                        if(itemID.equals(inp)){
                            ContentValues cv = new ContentValues(3);
                            cv.put("item",itemID.toString());
                            cv.put("itemName",itemText.toString());
                            cv.put("state","visible");
                            itemState = "visible";
                            mainPage.db.update(mainPage.tb_name,cv,"_id="+id,null );
                        }

                        // Set the tar ViewText to its state
                        if(itemState.equals("visible")) {
                            int resID = getResources().getIdentifier(itemID, "id", "tw.com.flag.a1031_first_demo");
                            TextView tar = (TextView) view.findViewById(resID);
                            tar.setVisibility(View.VISIBLE);
                            tar.setText(itemText);
                        }
                    }while (mainPage.c.moveToNext());
                }
            }
        });





        return view;
    }

    /*

         one type of update DB
    EditText edit = (EditText) view.findViewById(R.id.edit);
                String inp = edit.getText().toString();
                edit.setText("");
                mainPage.c=mainPage.db.rawQuery("SELECT * FROM "+mainPage.tb_name,null);
                if(mainPage.c.moveToFirst()){
                    do{
                        String viewID = mainPage.c.getString(1).toString();
                        if(inp.equals("item1")){
                               ContentValues cv = new ContentValues(3);
                               cv.put("item",mainPage.c.getString(1).toString());
                               cv.put("itemName",mainPage.c.getString(2).toString());
                               cv.put("state","visible");
                                mainPage.db.update(mainPage.tb_name,cv,"_id=1",null );
                        }

                        String viewText = mainPage.c.getString(2).toString();
                        int resID = getResources().getIdentifier(viewID, "id", "tw.com.flag.a1031_first_demo");
                        TextView tar = (TextView) view.findViewById(resID);

                        if(mainPage.c.getString(3).toString().equals("visible")){
                            tar.setVisibility(View.VISIBLE);
                            tar.setText(mainPage.c.getString(2).toString());
                        }
}while (mainPage.c.moveToNext());
        }

        */


    // Input



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
