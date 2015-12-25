package net.sleek.CDStore.webService.test;

import java.util.ArrayList;
import java.util.List;

import com.sleek.CDStore.model.CDBean;

public class Test {




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{

			List<UserInfo> list = new ArrayList<UserInfo>();
			UserInfo user1 = new UserInfo();
			user1.setId(0);
			user1.setName("aaa");
			UserInfo user2 = new UserInfo();
			user2.setId(1);
			user2.setName("bbb");
			list.add(user1);
			list.add(user2);
			
	

	
			
			CDBean CD1 = new CDBean();
			CD1.setArtist("aaa");
			
			CDBean CD2 = new CDBean();
			CD2.setArtist("bbb");
			
			List<CDBean> CDList = new ArrayList<CDBean>();
			CDList.add(CD1);
			CDList.add(CD2);
			

		    }
		 catch(Exception e){
		     System.err.println(e.toString());
		 }
	}

}
