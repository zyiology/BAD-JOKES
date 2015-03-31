package bleakfalls.goldenhands.badjokes;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
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
import android.widget.Toast;

import java.util.List;


public class JokeActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.questions);
        String[] ans = res.getStringArray(R.array.answers);
        Joke[] mJokes = new Joke[qs.length];
        for (int i=0; i<qs.length; i++) {
            Joke newJoke = new Joke(qs[i], ans[i]);
            mJokes[i] = newJoke;
        }
        JokeAdapter adapter = new JokeAdapter(this, R.layout.list_item_joke, mJokes);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Joke joke = (Joke)this.getListAdapter().getItem(position);
        String answer = joke.getAnswer();
        Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        joke.setClicked(true);
        ArrayAdapter<Joke> adapter = (ArrayAdapter<Joke>) getListAdapter();
        adapter.notifyDataSetChanged();
        Intent i = new Intent(JokeActivity.this, JokeDetailActivity.class);
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
