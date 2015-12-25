package net.sleek.CDStore.webService.category;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sleek.CDStore.database.DatabaseAgent;
import com.sleek.CDStore.model.CDBean;
import com.sleek.CDStore.model.CategoryBean;
import com.sleek.CDStore.model.TrackBean;



public class CategoryService {
	
	private DatabaseAgent db =new DatabaseAgent();
	
	/**
	 * get the information of a category with the ID
	 * @param the ID of the desired category
	 * @return a string stream of the information of a category
	 */
	public String getCategory(int id){
		CategoryBean cateList= db.getCategory(id);
		JSONObject returnJson = JSONObject.fromObject( cateList );  
		return returnJson.toString();
	}
	/**
	 * get a list of all categories
	 * @return a string stream of the list including all categories
	 */
	public String getCategoryList(){
		List<CategoryBean> cateList= db.getCategoryList();
		JSONArray returnJson = JSONArray.fromObject( cateList ); 
		return returnJson.toString();
	}
	/**
	 * get CD list of a certain category
	 * @param the ID of the category. If the id is 0, all CDs will be returned
	 * @return a string stream of the list of CDs belong to a certain category
	 */
	public String getProductList(int id){
		List<CDBean> CDList= db.getCDList(id);
		
		if(CDList == null) {
			return null;
		}
		
		JSONArray returnJson = JSONArray.fromObject( CDList ); 
		return returnJson.toString();
		
	}
	/**
	 * get the information of a CD
	 * @param ID of the CD
	 * @return a string stream of the information of a CD
	 */
	public String getProductInfo(int id){
		CDBean CD= db.getCDinfo(id);
		JSONObject returnJson = JSONObject.fromObject( CD ); 
		return returnJson.toString();
	}
	/**
	 * get the list of the tracks belong to a certain CD
	 * @param the ID of the CD
	 * @return a string stream of the list of tracks
	 */
	public String getTrackList(int id){
		List <TrackBean> trackList= db.getTrackList(id);
		JSONArray returnJson = JSONArray.fromObject( trackList ); 
		return returnJson.toString();
	}
	
}
