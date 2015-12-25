function initAjax()
{
	var xmlHttp;
	try
    {
   		// Firefox, Opera 8.0+, Safari
    	xmlHttp=new XMLHttpRequest();
    }
    catch(e)
    {
  		// Internet Explorer
  		try
  		{
      		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
      	}
     	catch(e)
     	{
     		try
     		{
     			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
     		}
     		catch(e)
     		{
     			alert("your browser does not surppot AJAX");
     			return false;
     		}
     	}
    }
    return xmlHttp;
}
function addToCart(id)
{
	var xmlHttp=initAjax();
	if(id!=0)
    {
    	xmlHttp.onreadystatechange=function()
    	{
      		if(xmlHttp.readyState==4)
        	{
				
				switch(xmlHttp.responseText){
					case "1": $('#shopping-cart').tooltip("show");break;//$('#shopping-cart').tooltip("hide");break;
					case "0": alert("please login first!");break;
					case "2": alert("error");
				}
         		
        	}
    	}
    	var url="./AddToCart?cdId="+id;
   	 	xmlHttp.open("GET",url,true);
    	xmlHttp.send(null);
	}
}
function updateCart(id)
{
	var xmlHttp=initAjax();
	var q=document.getElementById('cartItem'+id).value;
	if(q<=0)
	{
		alert("please input a vald number !");
		return;
	}
	if(id!=0)
    {
    	xmlHttp.onreadystatechange=function()
    	{
      		if(xmlHttp.readyState==4)
        	{
				var priceGroup=xmlHttp.responseText.split("|");
				document.getElementById('cartTotalPrice').innerText=priceGroup[0];
				document.getElementById('cartTaxPrice').innerText=priceGroup[1];
				switch(xmlHttp.responseText){
					case "0": alert("please login first!");break;
					case "2": alert("error");
				}
         		
        	}
    	}
    	var url="./UpdateCart?cdId="+id+"&q="+q;
   	 	xmlHttp.open("GET",url,true);
    	xmlHttp.send(null);
	}
}
function removeCart(id)
{
	var xmlHttp=initAjax();
	if(id!=0)
    {
    	xmlHttp.onreadystatechange=function()
    	{
      		if(xmlHttp.readyState==4)
        	{
				//update price
				var priceGroup=xmlHttp.responseText.split("|");
				document.getElementById('cartTotalPrice').innerText=priceGroup[0];
				document.getElementById('cartTaxPrice').innerText=priceGroup[1];
				//remove the view
				var remove=document.getElementById('cartItemTr'+id);
				var cartTable=document.getElementById('cartTable');
				cartTable.deleteRow(remove.rowIndex);
				switch(xmlHttp.responseText){
					case "0": alert("please login first!");break;
					case "2": alert("error");
				}
         		
        	}
    	}
    	var url="./RemoveCart?cdId="+id;
   	 	xmlHttp.open("GET",url,true);
    	xmlHttp.send(null);
	}
}
//$('#shopping-cart').tooltip("show");