package com.examplevinhphutvp.retrofit25022020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Danhsach {

@SerializedName("khoahoc")
@Expose
private String khoahoc;

public String getKhoahoc() {
return khoahoc;
}

public void setKhoahoc(String khoahoc) {
this.khoahoc = khoahoc;
}

};

class Demo2 {

@SerializedName("danhsach")
@Expose
private List<Danhsach> danhsach = null;

public List<Danhsach> getDanhsach() {
return danhsach;
}

public void setDanhsach(List<Danhsach> danhsach) {
this.danhsach = danhsach;
}

}