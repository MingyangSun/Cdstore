package net.sleek.CDStore.webService.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.database.DatabaseAgent;
import com.sleek.CDStore.function.ModelApplier;
import com.sleek.CDStore.model.TrackBean;

/**
 * Servlet implementation class testServlet
 */
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DatabaseAgent db =new DatabaseAgent();
		//List <TrackBean> trackList= db.getTrackList(1);
		//System.out.println(trackList.toString());
		//JSONArray returnJson = JSONArray.fromObject( trackList ); 
		//System.out.println(returnJson.toString());
		ModelApplier testModelApplier = new ModelApplier();
		testModelApplier.getTrackById(1);
		/*
		List<TrackBean> TrackList =new ArrayList<TrackBean>();
		TrackList = new ArrayList<TrackBean>();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL( URLMapper.CATEGORY_SERIVCE_URL ));
			call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getTrackList") );
			String ret = (String) call.invoke( new Object[] {0} );
			
			System.out.println(ret);
			
			JSONArray cdArray = JSONArray.fromObject(ret);
			for(int i=0 ; i<cdArray.size() ; i++ ){
				TrackBean track = (TrackBean) JSONObject.toBean( cdArray.getJSONObject(i),TrackBean.class);
				TrackList.add(track);
			}
		}catch(Exception e){
			System.err.println(e.toString());
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}