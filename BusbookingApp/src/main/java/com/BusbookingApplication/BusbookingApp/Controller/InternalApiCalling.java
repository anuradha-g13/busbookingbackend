package com.BusbookingApplication.BusbookingApp.Controller;
//import com.yks.ets.api.client;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.BusbookingApplication.BusbookingApp.BusbookingAppApplication;
import com.BusbookingApplication.BusbookingApp.Exception.ResourceNotFoundException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;
import com.sun.jersey.core.impl.provider.entity.SourceProvider;
import com.BusbookingApplication.BusbookingApp.Model.APIBlockTicketRequest;
import com.BusbookingApplication.BusbookingApp.Model.User;
import com.BusbookingApplication.BusbookingApp.Model.Details.OrderDetails;
import com.BusbookingApplication.BusbookingApp.Model.Details.TicketDetails;
import com.BusbookingApplication.BusbookingApp.Model.Details.TravellerDetails;
import com.BusbookingApplication.BusbookingApp.Repository.CountQueryRepo;
import com.BusbookingApplication.BusbookingApp.Repository.OrderDetailsRepo;
import com.BusbookingApplication.BusbookingApp.Repository.TicketDetailsRepo;
import com.BusbookingApplication.BusbookingApp.Repository.TravellerDetailsRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;


@RestController
@RequestMapping("/internal")
public class InternalApiCalling {
	
	@Autowired
	TravellerDetailsRepo travellerDetailsRepo;
	
	@Autowired
	TicketDetailsRepo ticketDetailsRepo;
	
	@Autowired
	OrderDetailsRepo orderDetailsRepo;
	
	@Autowired
	CountQueryRepo queryRepo;
	
	private static final Logger log = LoggerFactory.getLogger(BusbookingAppApplication.class);

	public String temp_status = "CONFIRMED";
	
	public static Client client = null;
	String url="http://test.etravelsmart.com/etsAPI/api/";

	public static Client getClient()
	{
		if(client==null)
		{
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
			client = Client.create(clientConfig);  
			client.addFilter(new HTTPDigestAuthFilter("TixdoAPI1", "123456"));
		}
		return client;
	}
	
	//to display all the buses from source to destination

	@RequestMapping(value = "/link", method = RequestMethod.GET, produces={"application/json"})
	
	public String getBusInfo(@RequestParam("from") String source,@RequestParam("to") String destination,
			@RequestParam("Doj") String dateOfJour) throws JSONException {
		
		String response= null;
		
		try {
			WebResource webResource = getClient().resource(url+"getAvailableBuses?sourceCity="+source+
					"&destinationCity="+destination+"&doj="+dateOfJour);
			response = webResource.get(String.class);
			System.out.println("getAvailableBuses::"+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;	
    }
	
	
	
	//get information of specific bus by id
	@RequestMapping(value = "/getBusById", method = RequestMethod.GET, produces={"application/json"})
		
	public String getBusById(@RequestParam("from") String source,@RequestParam("to") String destination,@RequestParam("Doj")
	String dateOfJour,@RequestParam("routeSchedule") int id) throws JSONException {	
	
	String response= null;
		
		try {
			WebResource webResource = getClient().resource(url+"getAvailableBuses?sourceCity="+
		source+"&destinationCity="+destination+"&doj="+dateOfJour);
			response = webResource.get(String.class);
			System.out.println("BusById::"+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			JSONObject obj = new JSONObject(response);
			JSONArray jArray = obj.getJSONArray("apiAvailableBuses");
			JSONArray internalResponse = new JSONArray();
			
			  for(int i = 0; i < jArray.length(); i++)
			  {
				  
				 org.json.JSONObject base = jArray.getJSONObject(i);
				 int b_id = base.getInt("routeScheduleId");
			
					if (b_id==id)
					{
						internalResponse.put(base);
						
					}
			  }
			  if(internalResponse==null || internalResponse.length()==0) {
				  return "No Bus Found with the id provided";
			  }
			  
			  else {
				  return internalResponse.toString(); 
			  }
			
	    }
		
		
	//to display all the buses on particular date
	/*@RequestMapping(value = "/getAllBuses", method = RequestMethod.GET, produces={"application/json"})
		
		public String getAllBusInfo(@RequestParam("Doj") String dateOfJour) throws JSONException {
			
	
			String url="http://demo0725894.mockable.io/";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> response = restTemplate.exchange(url,
	                HttpMethod.GET, entity, String.class);
			String result = response.getBody();
			
			JSONArray jArray = new JSONArray(result);
			JSONArray internalResponse = new JSONArray();
			
			  for(int i = 0; i < jArray.length(); i++)
			  {
				  
				 org.json.JSONObject base = jArray.getJSONObject(i);
				 String doj = base.getString("Doj");
	
					if (doj.equalsIgnoreCase(dateOfJour))
					{
						internalResponse.put(base);
						
					}
			  }
			  if(internalResponse==null || internalResponse.length()==0) {
				  return "No Bus available on this Date";
			  }
			  
			  else {
				  return internalResponse.toString(); 
			  }
		
			
	    }
	
	*/
	
	//get seat info of the selected bus
	@RequestMapping(value = "/getSeatInfo", method = RequestMethod.GET, produces={"application/json"})
	
	public String getBusSeatInfo(@RequestParam("sourceCity") String source,@RequestParam("destinationCity") 
	String destination,@RequestParam("doj") String dateOfJour, @RequestParam("inventoryType") int inventory, 
	@RequestParam("routeScheduleId") String routeScheduledId) throws JSONException {
		
		String response= null;
		try {
			WebResource webResource = getClient().resource(url+"getBusLayout?sourceCity="+source+"&destinationCity="+destination+"&doj="
		+dateOfJour+"&inventoryType="+inventory+"&routeScheduleId="+routeScheduledId);
			response = webResource.get(String.class);
			System.out.println("getBusLayout ::"+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  		return response;
		
	}
	



		//get updated fare for the bus
		@RequestMapping(value = "/getUpdatedFare", method = RequestMethod.GET, produces={"application/json"})
		
		public String getRTCUpdatedFare(@RequestParam("blockTicketKey") String blockticketKey) throws JSONException {
			
			String response= null;
			try {
				WebResource webResource = getClient().resource(url+"getRtcUpdatedFare?blockTicketKey="+blockticketKey);
				response = webResource.get(String.class);
				System.out.println("output::"+response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return response;
				
		}
				
	
		
//		@RequestMapping(value = "/journeydetails", method = RequestMethod.POST, produces={"application/json"})
//		public String journeydetails(@Valid @RequestBody String blockticketKey) throws JSONException, ParseException {
//	 System.out.println("test");   
//	 		
//	 		//Fetching Data from body
//	 		JSONObject object = new JSONObject(blockticketKey);
//	 		
//	 		String routeId = object.optString("routeScheduleId");
//	 		int inventory = object.optInt("inventoryType");
//	 		String source = object.optString("sourceCity");
//			String destin = object.optString("destinationCity");
//			String date = object.optString("doj");
//			
//			//Boarding Point
//			JSONObject baseBoarding = object.getJSONObject("boardingPoint");
//			String boardId = baseBoarding.optString("id");
//			String boardtime = baseBoarding.optString("time");
//			
//			//Dropping Point
//			JSONObject baseDropping = object.getJSONObject("droppingPoint");
//            
//            String dropId = null;
//            String dropTime = null;
//            
//            if(baseDropping!=null)
//            {
//			dropId = baseDropping.optString("id");
//			dropTime = baseDropping.optString("time");
//            }
//            
//            //Calculate Duration
//            String start = boardtime;
//            String end = dropTime;
//
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 
//
//            Date bp = format.parse(start);
//
//            Date dp = format.parse(end);
//
//            long difference = bp.getTime() - dp.getTime();
//            
//            long duration = difference / (60 * 60 * 1000) % 24;
//            
//            
//			//Storing data into database
//            
//            JourneyDetails journeydetails = new JourneyDetails();
//            
//            journeydetails.setInventoryType(inventory);
//			journeydetails.setBoardId(boardId);
//			journeydetails.setDestination(destin);
//			journeydetails.setDoj(date);
//			journeydetails.setDropId(dropId);
//			journeydetails.setRouteScheduleId(routeId);
//			journeydetails.setSource(source);
//			journeydetails.setBoardTime(boardtime);
//			journeydetails.setDropTime(dropTime);
//			journeydetails.setDuration(duration);
//			
//			JourneyDetails journeyDetails = jourDetailsRepo.save(journeydetails);
//
//            
//			return "Successfully stored";
//		}
//		
		
		
		
		//block the bus seat
		@RequestMapping(value = "/blockTicket", method = RequestMethod.POST, produces={"application/json"})
		public String blockBusSeat(@Valid @RequestBody String blockticketKey) throws JSONException{
	    
			//extracting json from request body
			
			JSONObject object = new JSONObject(blockticketKey);
			
		
			String source = object.optString("sourceCity");
			String destin = object.optString("destinationCity");
			String date = object.optString("doj");
			String custName = object.optString("customerName");
			String custLastName = object.optString("customerLastName");
			String custEmail = object.optString("customerEmail");
			String custPhone = object.optString("customerPhone");
			String emergencyPhNum = object.optString("emergencyPhNumber");
			String custAddrss = object.optString("customerAddress");
			int inventory = object.optInt("inventoryType");
            String routeId = object.optString("routeScheduleId");
            
            
            //boarding point details
            JSONObject baseBoarding = object.getJSONObject("boardingPoint");
			String boardId = baseBoarding.optString("id");
			String location = baseBoarding.optString("location");
			String time = baseBoarding.optString("time");
			
			//dropping point details
            JSONObject baseDropping = object.getJSONObject("droppingPoint");
            
            String dropId = null;
            String dropLocation = null;
            String dropTime = null;
            
            if(baseDropping!=null)
            {
			dropId = baseDropping.optString("id");
			dropLocation = baseDropping.optString("location");
			dropTime = baseDropping.optString("time");
            }

			
			APIBlockTicketRequest apiBlockTicketRequest=new APIBlockTicketRequest();
			List<BlockSeatPaxDetails> blockSeatPaxDetailsList=new ArrayList<BlockSeatPaxDetails>();
			BoardingPoint boardingPoint= new BoardingPoint();
			BoardingPoint droppingPoint= new BoardingPoint();
			 
			//BusSeatPaxDetails
			JSONArray busSeatPaxdetails = object.getJSONArray("blockSeatPaxDetails");
			
			for ( int i=0;i<busSeatPaxdetails.length();i++)
				
			{
				
				JSONObject ob = busSeatPaxdetails.getJSONObject(i);
				BlockSeatPaxDetails blockSeatPaxDetails=new BlockSeatPaxDetails();
			
				boolean ac = ob.optBoolean("ac");
				String age = ob.optString("age");
				String email = ob.optString("email");
				BigDecimal fare = new BigDecimal(ob.getString("fare"));
				String idNum = ob.optString("idNumber");
				String idType = ob.optString("idType");
				boolean ladiesSeat = ob.optBoolean("ladiesSeat");
				String lastName = ob.optString("lastName");
				String mobile = ob.optString("mobile");
				String name = ob.optString("name");
				String nameOnId = ob.optString("nameOnId");
				boolean primary = ob.optBoolean("primary");
				String seatnbr = ob.optString("seatNbr");
				String sex = ob.optString("sex");
				boolean sleeper = ob.optBoolean("sleeper");
				String title = ob.optString("title");
				BigDecimal serviceTaxAmount= new BigDecimal(ob.optString("serviceTaxAmount"));
	            double operatorServiceChargeAbsolute = ob.optDouble("operatorServiceChargeAbsolute");
	            BigDecimal totalFareWithTaxes = new BigDecimal(ob.optString("totalFareWithTaxes"));
	            int row = ob.optInt("row");
	            int column = ob.optInt("column");
	            int zIndex = ob.optInt("zIndex");
	            int width = ob.optInt("width");
	            int length = ob.optInt("length");
	           
				
				
				
			try {

				apiBlockTicketRequest.setSourceCity(source);
				apiBlockTicketRequest.setDestinationCity(destin);
				apiBlockTicketRequest.setInventoryType(inventory);
				apiBlockTicketRequest.setRouteScheduleId(routeId);
				apiBlockTicketRequest.setCustomerName(custName);
				apiBlockTicketRequest.setCustomerLastName(custLastName);
				apiBlockTicketRequest.setCustomerEmail(custEmail);
				apiBlockTicketRequest.setDoj(date);
				
				boardingPoint.setId(boardId);
				boardingPoint.setLocation(location);
				boardingPoint.setTime(time);
				
				apiBlockTicketRequest.setBoardingPoint(boardingPoint);
				
				
				droppingPoint.setId(dropId);
				droppingPoint.setLocation(dropLocation);
				droppingPoint.setTime(dropTime);
				
				apiBlockTicketRequest.setDroppingPoint(droppingPoint);
				
				apiBlockTicketRequest.setCustomerPhone(custPhone);
				apiBlockTicketRequest.setEmergencyPhNumber(emergencyPhNum);
				apiBlockTicketRequest.setCustomerAddress(custAddrss);
				
				
				
				blockSeatPaxDetails.setAge(age);
				blockSeatPaxDetails.setName(name);
				blockSeatPaxDetails.setSeatNbr(seatnbr);
				blockSeatPaxDetails.setRow(row);
				blockSeatPaxDetails.setColumn(column);
				blockSeatPaxDetails.setZIndex(zIndex);
				blockSeatPaxDetails.setLength(length);
				blockSeatPaxDetails.setWidth(width);
				blockSeatPaxDetails.setSex(sex);
				blockSeatPaxDetails.setFare(fare);
				blockSeatPaxDetails.setServiceTax(serviceTaxAmount);
				blockSeatPaxDetails.setOperatorServiceCharge(operatorServiceChargeAbsolute);
				blockSeatPaxDetails.setTotalFare(totalFareWithTaxes);
				blockSeatPaxDetails.setLadiesSeat(ladiesSeat);
				blockSeatPaxDetails.setLastName(lastName);
				blockSeatPaxDetails.setMobile(mobile);
				blockSeatPaxDetails.setTitle(title);
				blockSeatPaxDetails.setEmail(email);
				blockSeatPaxDetails.setIdType(idType);
				blockSeatPaxDetails.setIdNumber(idNum);
				blockSeatPaxDetails.setNameOnId(nameOnId);
				blockSeatPaxDetails.setPrimary(primary);
				blockSeatPaxDetails.setAc(ac);
				blockSeatPaxDetails.setSleeper(sleeper);
				
			    blockSeatPaxDetailsList.add(blockSeatPaxDetails);
				apiBlockTicketRequest.setBlockSeatPaxDetails(blockSeatPaxDetailsList);
				
			   //seatBooking(apiBlockTicketResponse.getBlockTicketKey());

					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			
			WebResource webResource = getClient().resource(url+"blockTicket");
			System.out.println(new Gson().toJson(apiBlockTicketRequest));
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Gson().toJson(apiBlockTicketRequest));
			String output = response.getEntity(String.class);
			System.out.println("blockResponse :"+output);
			Type collectionType = new TypeToken<APIBlockTicketResponse>()
					{
					}.getType();
					APIBlockTicketResponse apiBlockTicketResponse = new Gson().fromJson(output,collectionType);
					apiBlockTicketResponse.setBlockTicketKey(output);
					System.out.println("block ticket key ::: "+ apiBlockTicketResponse.getBlockTicketKey());

			
			return apiBlockTicketResponse.getBlockTicketKey();		
	
		}
	
		
		//seat booking with block ticket key
		@RequestMapping(value = "/seatBooking", method = RequestMethod.GET, produces={"application/json"})
		public String seatBooking(@RequestParam("blockTicketKey") String blockticketKey) throws JSONException {
		// TODO Auto-generated method stub
			String output= null;
			try {
				WebResource service = getClient().resource(url+"seatBooking?blockTicketKey="+blockticketKey);
				ClientResponse response = service.get(ClientResponse.class);
				output = response.getEntity(String.class);
				System.out.println("output;;;;"+output);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return output;
		}
		
		
		//get ticket by ETSTNumber
		@RequestMapping(value = "/getTicket", method = RequestMethod.GET, produces={"application/json"})
		public String getTicketByETSTNumber(@RequestParam("ETSTNumber") String ETSTNumber) throws JSONException {
		
			String response= null;
			try {
				WebResource webResource = getClient().resource(url+"getTicketByETSTNumber?ETSTNumber="+ETSTNumber);
				response = webResource.get(String.class);
				System.out.println("getTicketByETSTNumber response::"+response);
				
				JSONObject object = new JSONObject(response);
		 		
	            String etstNum = object.optString("etstnumber");
	            
	            JSONArray ob = object.optJSONArray("travelerDetails");
	            
	            for (int i=0;i<ob.length();i++)
	            {
	            JSONObject jObject = ob.optJSONObject(i);
	           //System.out.println(jObject.get("name"));
	            
	           	String name = jObject.optString("name");
	            String lastname = jObject.optString("lastName");
	            String seatnum = jObject.optString("seatNo");
	            int age = jObject.optInt("age");
	            String gender = jObject.optString("gender");
	            long fare = jObject.optLong("fare");
	            String ticketStatus = object.optString("ticketStatus");
	            
	            //TravellerDetails Database IMplementation
	            TravellerDetails travellerDetails = new TravellerDetails();
	            travellerDetails.setName(name);
	            travellerDetails.setLastname(lastname);
	            travellerDetails.setseatNumber(seatnum);
	            travellerDetails.setAge(age);
	            travellerDetails.setGender(gender);
	            travellerDetails.setFare(fare);
	            travellerDetails.setEtstNumber(etstNum);
	            
	            
	            if(ticketStatus!="CONFIRMED") {
	            	travellerDetails.setseatStatus(ticketStatus);
	            }
	          
	            
	            travellerDetailsRepo.save(travellerDetails);
	            
	            
	            
	            //-----------------------------------------------------------------------------
	            
	            //TicketDetails Database Implementation
	            
	            String pnr = object.optString("opPNR");
	            
	            TicketDetails ticketdetails = new TicketDetails();
	            ticketdetails.setEtstnum(etstNum);
	            ticketdetails.setPnrnum(pnr);
	            ticketdetails.setTicketdump(response);
	            ticketdetails.setTicketStatus(ticketStatus);
	            ticketDetailsRepo.save(ticketdetails);


	            }
				} catch (Exception e) {
				e.printStackTrace();
			}
			
			return response;
			
		}

		
		

		//cancel Ticket Confirmation
		@RequestMapping(value = "/cancelTicketConfirmation", method = RequestMethod.POST, produces={"application/json"})
		private String cancelTicketConfirmation(@Valid @RequestBody String Json)throws JSONException
		{
			
	            JSONObject object = new JSONObject(Json);
				String etsTicketNo = object.optString("etsTicketNo");
				JSONArray seatArray = object.optJSONArray("seatNbrsToCancel");
				
			
				APICancelRequest apiCancelRequest = new APICancelRequest();
				apiCancelRequest.setEtsTicketNo(etsTicketNo);
				List<String> seatNbrs = new ArrayList<String>();
				for(int i=0;i<seatArray.length();i++)
				{
					String element = seatArray.getString(i);
					seatNbrs.add(element);
					
				}
				
				apiCancelRequest.setSeatNbrsToCancel(seatNbrs);
				WebResource webResource = getClient().resource(url+"cancelTicketConfirmation");
				ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Gson().toJson(apiCancelRequest));
				System.out.println(response);
				String output = response.getEntity(String.class);
				System.out.println("cancel Response :"+output);
				return output;
		}

		
		
		//cancle Ticket
				@RequestMapping(value = "/cancelTicket", method = RequestMethod.POST, produces={"application/json"})
				private String cancelTicket(@Valid @RequestBody String Json)throws JSONException
				{
					
			            JSONObject object = new JSONObject(Json);
						String etsTicketNo = object.optString("etsTicketNo");
						JSONArray seatArray = object.optJSONArray("seatNbrsToCancel");
						
					
						APICancelRequest apiCancelRequest = new APICancelRequest();
						apiCancelRequest.setEtsTicketNo(etsTicketNo);
						List<String> seatNbrs = new ArrayList<String>();
						for(int i=0;i<seatArray.length();i++)
						{
							String element = seatArray.getString(i);
							seatNbrs.add(element);
							
						}
						
						apiCancelRequest.setSeatNbrsToCancel(seatNbrs);
						WebResource webResource = getClient().resource(url+"cancelTicketConfirmation");
						ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Gson().toJson(apiCancelRequest));
						System.out.println(response);
						String output = response.getEntity(String.class);
						
						
						//to check the seat status
						JSONObject parse = new JSONObject(output);
						JSONObject apiStatus = parse.getJSONObject("apiStatus");
						Boolean status = apiStatus.getBoolean("success");
						if (status) //status = true;
						{
							System.out.println(status);
				    	    TravellerDetails traveller = travellerDetailsRepo.findByEtstNumber(etsTicketNo);
				    	    System.out.println(traveller.getEtstNumber());
				    	    System.out.println(traveller.getseatStatus());
				    	    traveller.setseatStatus("Cancelled");
				    	    temp_status = traveller.getseatStatus();
				    	    int count = queryRepo.countByEtstNumber(etsTicketNo);
				    	    System.out.println(count);
				    	    travellerDetailsRepo.save(traveller);
				    	    
						}
				
						return output;
				}
				
				@RequestMapping(value = "/setTransactionDetails", method = RequestMethod.POST, produces={"application/json"})
				private String getTxnDetails(@Valid @RequestBody String json)throws JSONException
				{
					      JSONObject object = new JSONObject(json);
					      String etstnum = object.optString("etstnum");
					      String status = object.optString("status");
					      String transaction_id = object.optString("transaction_id");
					      
					      OrderDetails details = new OrderDetails();
					      details.setEtstnum(etstnum);
					      details.setStatus(status);
					      details.setTransactionid(transaction_id);
					      
					      
					      //orderDetailsRepo.save(details); 
					      System.out.println(orderDetailsRepo.save(details));
							
					      JSONObject response = new JSONObject();
					      response.put("message", "Transaction details stored succesfully!");
					      response.put("status", "success");
							
					return response.toString();
					
				}
				
				@RequestMapping(value = "/getMyPlanAndBalance", method = RequestMethod.GET, produces={"application/json"})
				private String getMyPlanAndBalance()
				{
					WebResource webResource = getClient().resource(url+"getMyPlanAndBalance");
					String response = webResource.get(String.class);
					
					return response;
				}

}


