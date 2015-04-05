package bleakfalls.goldenhands.badjokes;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class JokeActivity extends ListActivity {

    public static String SELECTED_JOKE = "com.bleakfalls.goldenhand.badjokes.jokeactivity.joke";
    private static String JOKES_LIST = "com.bleakfalls.goldenhand.badjokes.jokeactivity.listview";
    private Joke[] mJokes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        if (savedInstanceState!=null) {
            Log.d("start","found instance");
            mJokes = (Joke[])savedInstanceState.getSerializable(JOKES_LIST);
        }
        else {
            Resources res = getResources();
            String[] mQuestions = res.getStringArray(R.array.questions);
            String[] mAnswers = res.getStringArray(R.array.answers);
            mJokes = new Joke[mQuestions.length];
            for (int i = 0; i < mQuestions.length; i++) {
                Joke newJoke = new Joke(mQuestions[i], mAnswers[i]);
                mJokes[i] = newJoke;
            }
        }
        JokeAdapter adapter = new JokeAdapter(this, R.layout.list_item_joke, mJokes);
        setListAdapter(adapter);

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Joke mJoke = (Joke)this.getListAdapter().getItem(position);
        String mAnswer = mJoke.getAnswer();
        mJoke.setClicked(true);
        ArrayAdapter<Joke> adapter = (ArrayAdapter<Joke>) getListAdapter();
        adapter.notifyDataSetChanged();
        Intent i = new Intent(JokeActivity.this, JokeDetailActivity.class);
        i.putExtra(SELECTED_JOKE,mJoke);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(JOKES_LIST, mJokes);
    }

    private class JokeAdapter extends ArrayAdapter<Joke> {
        private int mResource;
        private Joke[] mJokes;

        public JokeAdapter(Context context, int resource, Joke[] jokes) {
            super(context, resource, jokes);
            mResource = resource;
            mJokes = jokes;
        }

        public View getView(int position, View row, ViewGroup parent) {
            if (row==null) {
                row = getLayoutInflater().inflate(mResource, parent, false);
            }
            Joke currentJoke = mJokes[position];

            TextView textView = (TextView) row.findViewById(R.id.text);
            textView.setText(currentJoke.getQuestion());

            ImageView imageView = (ImageView) row.findViewById(R.id.image);
            if (currentJoke.getClicked() == true) {
                imageView.setVisibility(View.INVISIBLE);
            }
            else {
                imageView.setVisibility(View.VISIBLE);
            }

            return row;
        }
    }
}
