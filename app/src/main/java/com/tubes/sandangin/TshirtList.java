package com.tubes.sandangin;

import java.util.ArrayList;

public class TshirtList {
    public ArrayList<Tshirt> tshirts;

    public TshirtList() {
        tshirts = new ArrayList<Tshirt>();
        tshirts.add(tshirt1);
        tshirts.add(tshirt2);
        tshirts.add(tshirt3);
        tshirts.add(tshirt4);
    }

    Tshirt tshirt1 = new Tshirt("T-SHIRT HITAM 1", "Kaos hitam pria", "L", 135000, R.drawable.tshirt1);
    Tshirt tshirt2 = new Tshirt("T-SHIRT HITAM 2", "Kaos hitam wanita", "M", 145000, R.drawable.tshirt2);
    Tshirt tshirt3 = new Tshirt("T-SHIRT PUTIH 1", "Kaos putih pria", "S", 135000, R.drawable.tshirt3);
    Tshirt tshirt4 = new Tshirt("T-SHIRT PUTIH 2", "Kaos putih wanita", "L",155000, R.drawable.tshirt4);
}
