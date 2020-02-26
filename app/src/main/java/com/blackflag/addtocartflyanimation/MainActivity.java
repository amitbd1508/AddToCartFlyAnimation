package com.blackflag.addtocartflyanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blackflag.addtocartflyanimation.adapters.ProductAdapter;
import com.blackflag.addtocartflyanimation.models.Product;
import com.blackflag.addtocartflyanimation.util.CircleAnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private Toolbar mToolbar;
  private RecyclerView mRecyclerView;
  private TextView titleTV;


  private int itemCounter = 0;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = findViewById(R.id.toolbar);
    titleTV=mToolbar.findViewById(R.id.toolbar_center_title);

    titleTV.setText("FlyToCartAnimation");

    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    List<Product> list = makeProductsList();

    ProductAdapter adapter = new ProductAdapter(this, list);
    mRecyclerView.setAdapter(adapter);
    adapter.setActionListener(new ProductAdapter.ProductItemActionListener() {
      @Override
      public void onItemTap(ImageView imageView) {
        if (imageView != null)
          makeFlyAnimation(imageView);
      }
    });
  }

  private void addItemToCart() {
    TextView textView = (TextView) findViewById(R.id.textNotify);
    textView.setText(String.valueOf(++itemCounter));
  }

  private void makeFlyAnimation(ImageView targetView) {

    RelativeLayout destView = (RelativeLayout) findViewById(R.id.cartRelativeLayout);

    new CircleAnimationUtil().attachActivity(this).setTargetView(targetView).setMoveDuration(1000).setDestView(destView).setAnimationListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {

      }

      @Override
      public void onAnimationEnd(Animator animation) {
        addItemToCart();
        Toast.makeText(MainActivity.this, "Continue Shopping...", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onAnimationCancel(Animator animation) {

      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    }).startAnimation();


  }


  private List<Product> makeProductsList() {

    List<Product> list = new ArrayList<>();

    for (int i = 1, c = 1; i <= 50; i++, c++) {

      if (c > 4)
        c = 1;

      switch (c) {
        case 1:
          list.add(new Product(i, "product_" + i, R.drawable.one));
          break;

        case 2:
          list.add(new Product(i, "product_" + i, R.drawable.two));
          break;

        case 3:
          list.add(new Product(i, "product_" + i, R.drawable.three));
          break;

        case 4:
          list.add(new Product(i, "product_" + i, R.drawable.four));
          break;
      }

    }

    return list;
  }
}
