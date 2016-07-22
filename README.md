# NightModel
a night model lib for easy change app's night theme.

## 在代码中需要通过LayoutInflater.from(MainActivity.this)来走代理的LayoutInflaterFactory,以保存View信息
final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, linearLayout, false);
                ((TextView)view.findViewById(R.id.text_view)).setText("123");

                linearLayout.addView(view);
            }
        });
